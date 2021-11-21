package com.example.petto.service;

import com.example.petto.entity.VolBoardRelated.Comment;
import com.example.petto.entity.VolunteerBoard;
import com.example.petto.repository.CommentRepository;
import com.example.petto.repository.VolunteerBoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class VolunteerBoardServiceImpl implements VolunteerBoardService{

    @Autowired
    private VolunteerBoardRepository volunteerBoardRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public void write(String volTitle, String closingDate, MultipartFile fileList,MultipartFile contentFileList) throws Exception {
        VolunteerBoard title = new VolunteerBoard();

        title.setVolTitle(volTitle);
        title.setClosingDate(closingDate);

        String titlePath = "d:/petto_test/petto_all/test_frontend/src/assets/vol/title/";
        String contentPath = "d:/petto_test/petto_all/test_frontend/src/assets/vol/content/";
        File fileTitle = new File(titlePath);
        File fileContent = new File(contentPath);

        UUID uuid = UUID.randomUUID();

        try {
            if(!fileTitle.exists()) {
                fileTitle.mkdirs();
                fileContent.mkdirs();
            }

            if(fileList != null) {
                String fileName = uuid + "_" + fileList.getOriginalFilename();
                FileOutputStream writer = new FileOutputStream("d:/proj/petto/petto-frontend/src/assets/vol/title/" + fileName);
                writer.write(fileList.getBytes());
                writer.close();
                title.setFileName(fileName);
            }

            if(contentFileList != null) {
                String contentFileName = uuid + "_" + contentFileList.getOriginalFilename();
                FileOutputStream writer = new FileOutputStream("d:/proj/petto/petto-frontend/src/assets/vol/content/" + contentFileName);
                writer.write(contentFileList.getBytes());
                writer.close();
                title.setContentFileName(contentFileName);

            }

        } catch (Exception e) {
            log.info("Error");
        }
        volunteerBoardRepository.save(title);
    }

    @Override
    public List<VolunteerBoard> list() throws Exception {
        List<VolunteerBoard> lists = volunteerBoardRepository.findAll();
        Collections.reverse(lists);

        return lists;
    }

    @Override
    public VolunteerBoard read(Long volunteerNo) throws Exception {
        Optional<VolunteerBoard> optional = volunteerBoardRepository.findById(volunteerNo);
        if(optional.isPresent()) {
            VolunteerBoard volunteerBoard = optional.get();
            volunteerBoardRepository.save(volunteerBoard);
            return volunteerBoard;
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public VolunteerBoard modify(Long volunteerNo, String volTitle, String closingDate,
                                 MultipartFile fileList, MultipartFile contentFileList, VolunteerBoard volunteerBoard) throws Exception {

        Optional<VolunteerBoard> modifyBoard = volunteerBoardRepository.findById(volunteerNo);
        VolunteerBoard modifyVol = modifyBoard.get();
        UUID uuid = UUID.randomUUID();

        modifyBoard.ifPresent( changeBoard -> {
            changeBoard.setVolTitle(volTitle);
            volunteerBoard.setVolunteerNo(volunteerNo);
            if(closingDate.isEmpty()) {
                volunteerBoard.setClosingDate(modifyVol.getClosingDate());
            } else {
                changeBoard.setClosingDate(closingDate);
            }

            try {
                String newFileName = modifyVol.getFileName();
                String newContentFileName = modifyVol.getContentFileName();
                Path fileTitle = Paths.get("d:/proj/petto/petto-frontend/src/assets/vol/title/" + newFileName);
                Path fileContent = Paths.get("d:/proj/petto/petto-frontend/src/assets/vol/content/" + newContentFileName);
                if( fileList == null){
                    volunteerBoard.setFileName(modifyVol.getFileName());
                } else {
                    String fileName = uuid + "_" + fileList.getOriginalFilename();
                    FileOutputStream writer = new FileOutputStream("d:/proj/petto/petto-frontend/src/assets/vol/title/" + fileName);
                    writer.write(fileList.getBytes());
                    writer.close();
                    changeBoard.setFileName(fileName);
                    Files.delete(fileTitle);
                }

                if( contentFileList == null){
                    volunteerBoard.setContentFileName(modifyVol.getContentFileName());
                } else {
                    String contentFileName = uuid + "_" + contentFileList.getOriginalFilename();
                    FileOutputStream writer = new FileOutputStream("d:/proj/petto/petto-frontend/src/assets/vol/content/" + contentFileName);
                    writer.write(contentFileList.getBytes());
                    writer.close();
                    changeBoard.setContentFileName(contentFileName);
                    Files.delete(fileContent);

                }
                volunteerBoardRepository.save(changeBoard);
            }  catch (IOException e) {
                log.info("Error");
            }
        });
        return volunteerBoard;
    }



    @Override
    public void remove(Long volunteerNo) throws Exception {
        Optional<VolunteerBoard> delImg = volunteerBoardRepository.findById(volunteerNo);
        List<Comment> boardComments = commentRepository.findByVolunteerNo(volunteerNo);

        for( Comment comment : boardComments) {
            commentRepository.deleteById(comment.getCommentNo());
        }

        if(delImg.isPresent()) {
            VolunteerBoard delAll = delImg.get();
            String fileName = delAll.getFileName();
            String contentFileName = delAll.getContentFileName();

            Path fileTitle = Paths.get("d:/proj/petto/petto-frontend/src/assets/vol/title/" + fileName);
            Path fileContent = Paths.get("d:/proj/petto/petto-frontend/src/assets/vol/content/" + contentFileName);

            try {
                Files.delete(fileTitle);
                Files.delete(fileContent);
                volunteerBoardRepository.deleteById(volunteerNo);
            } catch (DirectoryNotEmptyException e) {
                log.info("The file doesn't exist.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
