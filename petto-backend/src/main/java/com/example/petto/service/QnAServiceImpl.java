package com.example.petto.service;


import com.example.petto.entity.QnA;
import com.example.petto.entity.VolunteerBoard;
import com.example.petto.repository.QnARepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class QnAServiceImpl implements QnAService {

    @Autowired
    private QnARepository repository;

    @Override
    public List<QnA> list() throws Exception {

        List<QnA> lists = repository.findAll();
        Collections.reverse(lists);

        return lists;

    }

    @Override
    public void register(QnA qna) throws Exception {
        repository.save(qna);
    }

    @Override
    public QnA read(long qnaNo) throws Exception {
        Optional<QnA> maybeBoard = repository.findById(qnaNo);
        return maybeBoard.get();
    }

    @Override
    public void modify(QnA qna) throws Exception {
        repository.save(qna);
    }

    @Override
    public void delete(long qnaNo) throws Exception {
        repository.deleteById(qnaNo);
    }

    @Override
    public List<QnA> myQnaList(String id) throws Exception {

        List<QnA> mylists = repository.myQnaList(id);
        Collections.reverse(mylists);

        return mylists;

    }


}