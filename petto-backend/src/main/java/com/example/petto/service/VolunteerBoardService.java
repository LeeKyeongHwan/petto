package com.example.petto.service;

import com.example.petto.entity.VolunteerBoard;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VolunteerBoardService {

    public void write(String volTitle, String closingDate,
                      MultipartFile fileList, MultipartFile contentFileList) throws Exception;

    public List<VolunteerBoard> list() throws Exception;

    public VolunteerBoard read(Long volunteerNo) throws Exception;

    public VolunteerBoard modify(Long volunteerNo, String volTitle, String closingDate,
                                 MultipartFile fileList, MultipartFile contentFileList,VolunteerBoard volunteerBoard) throws Exception;

    public void remove(Long volunteerNo) throws Exception;

}