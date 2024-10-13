package com.engapp.QuizService.service.implement;

import com.engapp.QuizService.dto.request.AnswerRequest;
import com.engapp.QuizService.dto.request.update.AnswerUpdateRequest;
import com.engapp.QuizService.exception.ApplicationException;
import com.engapp.QuizService.exception.ErrorCode;
import com.engapp.QuizService.mapper.AnswerMapper;
import com.engapp.QuizService.pojo.Answer;
import com.engapp.QuizService.pojo.Question;
import com.engapp.QuizService.repository.AnswerRepository;
import com.engapp.QuizService.repository.QuestionRepository;
import com.engapp.QuizService.service.AnswerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class AnswerServiceImplement implements AnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private AnswerMapper answerMapper;

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public Answer createAnswer(AnswerRequest answerRequest) {
        Answer answer = this.answerMapper.answerRequestToAnswer(answerRequest);
        answer.setCreatedDate(Instant.now());
        return answerRepository.save(answer);
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public Set<Answer> createMultipleAnswers(Set<AnswerRequest> answerRequests) {
        Set<Answer> answerSet = new HashSet<>();
        Question question = answerRequests.iterator().next().getQuestion();
        if(checkDuplicateKeyAnswerRequest(answerRequests)){
            this.questionRepository.deleteById(question.getId());
            throw new ApplicationException(ErrorCode.RESULT_INVALID);
        }
        answerRequests.forEach(requestItem ->
                        answerSet.add(this.createAnswer(requestItem))
        );
        return answerSet;
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public Answer updateAnswer(AnswerUpdateRequest answerUpdateRequest) {
        Answer answer = this.getAnswerById(answerUpdateRequest.getId());

        answer.setContent(answerUpdateRequest.getContentUpdate());
        answer.setAnswerKey(answerUpdateRequest.getAnswerKeyUpdate());

        return this.answerRepository.save(answer);
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public Answer getAnswerById(int id) {
        return this.answerRepository.findById(id).orElseThrow(() -> new ApplicationException(ErrorCode.NOT_EXIST));
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public Set<Answer> updateMultipleAnswers(Set<AnswerUpdateRequest> answerUpdateRequests) {
        if(checkDuplicateKeyAnswerUpdateRequest(answerUpdateRequests)){
            throw new ApplicationException(ErrorCode.RESULT_INVALID);
        };
        Set<Answer> answerSet = new HashSet<>();
        for(AnswerUpdateRequest answerUpdateRequest : answerUpdateRequests) {
            Answer answer = this.updateAnswer(answerUpdateRequest);
            answerSet.add(answer);
        }
        return answerSet;
    }

    @Override
    public List<Answer> getAnswersByQuestionId(int questionId) {
        return this.answerRepository.findByQuestionId(questionId);
    }

    public boolean checkDuplicateKeyAnswerRequest(Set<AnswerRequest> answerRequestSet) {
        Set<String> answerSet = new HashSet<>();

        for (AnswerRequest answer : answerRequestSet) {
            if (!answerSet.add(answer.getAnswerKey())) {
                return true;
            }
        }
        return false;
    }

    public boolean checkDuplicateKeyAnswerUpdateRequest(Set<AnswerUpdateRequest> answerUpdateRequestSet) {
        Set<String> answerSet = new HashSet<>();

        for (AnswerUpdateRequest answer : answerUpdateRequestSet) {
            if (!answerSet.add(answer.getAnswerKeyUpdate())) {
                return true;
            }
        }
        return false;
    }
}
