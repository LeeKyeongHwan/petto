package com.example.petto.service;

import com.example.petto.entity.QnA;

import java.util.List;

public interface QnAService {

    public List<QnA> list() throws Exception;

    public void register(QnA qna) throws Exception;

    public QnA read(long qnaNo) throws Exception;

    public void modify(QnA qna) throws Exception;

    public void delete(long qnaNo) throws Exception;

    public List<QnA> myQnaList(String id) throws Exception;

    //
    public List<QnA> qnaList() throws Exception;

    public QnA qnaRead(Long qnaNo) throws Exception;

    public QnA qnaAnswers(Long qnaNo, QnA qnA) throws Exception;


}
