package com.engapp.QuizService.service.implement;

import com.engapp.QuizService.dto.request.AnswerRequest;
import com.engapp.QuizService.dto.request.QuestionRequest;
import com.engapp.QuizService.dto.request.update.QuestionUpdateRequest;
import com.engapp.QuizService.exception.ApplicationException;
import com.engapp.QuizService.exception.ErrorCode;
import com.engapp.QuizService.mapper.QuestionMapper;
import com.engapp.QuizService.pojo.Answer;
import com.engapp.QuizService.pojo.Question;
import com.engapp.QuizService.repository.QuestionRepository;
import com.engapp.QuizService.service.AnswerService;
import com.engapp.QuizService.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class QuestionServiceImplement implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private AnswerService answerService;

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public Question createQuestion(QuestionRequest questionRequest) {
        if(this.getQuestionByQuestionNumber(questionRequest.getQuestionSetIdRequest(), questionRequest.getQuestionNumber()) != null){
            throw new ApplicationException(ErrorCode.QUESTION_NUMBER_EXIST);
        }

        Question question = this.questionMapper.questionRequestToQuestion(questionRequest);
        question.setCreatedDate(Instant.now());
        question.setUpdatedDate(Instant.now());

        Question questionFirstPersisted = questionRepository.save(question);

        //create multiple answer base on Question Persisted
        Set<AnswerRequest> answerRequestSet = questionRequest.getAnswerSet();
        for(AnswerRequest answerRequest : answerRequestSet){
            answerRequest.setQuestion(question);
        }
        Set<Answer> answerSet = this.answerService.createMultipleAnswers(answerRequestSet);

        questionFirstPersisted.setAnswers(answerSet);

        //Return second question persisted
        return questionRepository.save(questionFirstPersisted);
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
        question.setCorrectAnswer(questionUpdateRequest.getCorrectAnswerUpdate());
        return this.questionRepository.save(question);
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteQuestion(int questionId) {
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public Question getQuestionByQuestionNumber(int questionSetId, int questionNumber) {
        return this.questionRepository.findByQuestionNumber(questionSetId, questionNumber);
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
