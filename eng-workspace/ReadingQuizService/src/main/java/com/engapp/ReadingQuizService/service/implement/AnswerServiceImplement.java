package com.engapp.ReadingQuizService.service.implement;

import com.engapp.ReadingQuizService.dto.request.AnswerRequest;
import com.engapp.ReadingQuizService.dto.request.update.AnswerUpdateRequest;
import com.engapp.ReadingQuizService.exception.ApplicationException;
import com.engapp.ReadingQuizService.exception.ErrorCode;
import com.engapp.ReadingQuizService.mapper.AnswerMapper;
import com.engapp.ReadingQuizService.pojo.Answer;
import com.engapp.ReadingQuizService.pojo.Question;
import com.engapp.ReadingQuizService.repository.AnswerRepository;
import com.engapp.ReadingQuizService.service.AnswerService;
import com.engapp.ReadingQuizService.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AnswerServiceImplement implements AnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private AnswerMapper answerMapper;

    @Autowired
    private QuestionService questionService;

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public Answer createAnswer(AnswerRequest answerRequest) {
        Answer answer = this.answerMapper.answerRequestToAnswer(answerRequest);
        Question question = this.questionService.getQuestionById(answerRequest.getQuestionIdRequest());
        answer.setCreatedDate(Instant.now());
        answer.setQuestion(question);

        Answer savedAnswer = answerRepository.save(answer);
        question.getAnswers().add(savedAnswer);
        this.questionService.saveQuestion(question);
        return savedAnswer;
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Answer> createMultipleAnswers(List<AnswerRequest> answerRequests) {
        List<Answer> answerList = new ArrayList<>();
        answerRequests.forEach(requestItem ->
                        answerList.add(this.createAnswer(requestItem))
        );
        return answerList;
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public Answer updateAnswer(AnswerUpdateRequest answerUpdateRequest) {
        Answer answer = this.getAnswerById(answerUpdateRequest.getId());

        answer.setContent(answerUpdateRequest.getContentUpdate());
        answer.setIsResult(answerUpdateRequest.getIsResult());

        return this.answerRepository.save(answer);
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public Answer getAnswerById(int id) {
        return this.answerRepository.findById(id).orElseThrow(() -> new ApplicationException(ErrorCode.NOT_EXIST));
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Answer> updateMultipleAnswers(List<AnswerUpdateRequest> answerUpdateRequests) {
        List<Answer> answerList = new ArrayList<>();
        for(AnswerUpdateRequest answerUpdateRequest : answerUpdateRequests) {
            Answer answer = this.updateAnswer(answerUpdateRequest);
            answerList.add(answer);
        }
        return answerList;
    }

    @Override
    public List<Answer> getAnswersByQuestionId(int questionId) {
        return this.answerRepository.findByQuestionId(questionId);
    }
}
