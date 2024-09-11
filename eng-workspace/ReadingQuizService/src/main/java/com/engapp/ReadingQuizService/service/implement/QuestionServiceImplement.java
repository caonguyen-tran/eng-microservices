package com.engapp.ReadingQuizService.service.implement;

import com.engapp.ReadingQuizService.dto.request.QuestionRequest;
import com.engapp.ReadingQuizService.dto.request.update.QuestionUpdateRequest;
import com.engapp.ReadingQuizService.exception.ApplicationException;
import com.engapp.ReadingQuizService.exception.ErrorCode;
import com.engapp.ReadingQuizService.mapper.QuestionMapper;
import com.engapp.ReadingQuizService.pojo.Question;
import com.engapp.ReadingQuizService.repository.QuestionRepository;
import com.engapp.ReadingQuizService.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@Slf4j
public class QuestionServiceImplement implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public Question createQuestion(QuestionRequest questionRequest) {
        if(this.getQuestionByQuestionNumber(questionRequest.getQuestionSetIdRequest(), questionRequest.getQuestionNumber()) != null){
            throw new ApplicationException(ErrorCode.QUESTION_NUMBER_EXIST);
        }

        Question question = this.questionMapper.questionRequestToQuestion(questionRequest);
        question.setCreatedDate(Instant.now());
        question.setUpdatedDate(Instant.now());

        return this.questionRepository.save(question);
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Question> getAllQuestions() {
        return this.questionRepository.findAll();
    }

    @Override
    public List<Question> getByQuestionSetId(int questionSetId) {
        return this.questionRepository.getQuestionsByQuestionSetId(questionSetId);
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public Question updateQuestion(QuestionUpdateRequest questionUpdateRequest) {
        Question question = this.getQuestionById(questionUpdateRequest.getId());

        if(this.getQuestionByQuestionNumber(question.getId(), questionUpdateRequest.getQuestionNumberUpdate()) != null){
            throw new ApplicationException(ErrorCode.QUESTION_NUMBER_EXIST);
        }

        question.setUpdatedDate(Instant.now());
        question.setQuestionNumber(questionUpdateRequest.getQuestionNumberUpdate());
        question.setQuestionContent(questionUpdateRequest.getQuestionContentUpdate());
        question.setExplainAnswer(questionUpdateRequest.getExplainAnswerUpdate());
        return this.questionRepository.save(question);
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteQuestion(int questionId) {
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public Question getQuestionByQuestionNumber(int questionId, int questionNumber) {
        return this.questionRepository.findByQuestionNumber(questionId, questionNumber);
    }

    @Override
    public Question getQuestionById(int questionId) {
        return this.questionRepository.findById(questionId).orElseThrow(() -> new ApplicationException(ErrorCode.NOT_EXIST));
    }

    @Override
    public void saveQuestion(Question question){
        this.questionRepository.save(question);
    }
}
