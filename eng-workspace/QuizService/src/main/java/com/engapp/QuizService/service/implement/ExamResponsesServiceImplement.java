package com.engapp.QuizService.service.implement;

import com.engapp.QuizService.configuration.CustomUserDetails;
import com.engapp.QuizService.configuration.PrincipalConfiguration;
import com.engapp.QuizService.exception.ApplicationException;
import com.engapp.QuizService.exception.ErrorCode;
import com.engapp.QuizService.pojo.ExamResponses;
import com.engapp.QuizService.pojo.Question;
import com.engapp.QuizService.pojo.QuizResult;
import com.engapp.QuizService.repository.ExamResponseRepository;
import com.engapp.QuizService.service.ExamResponsesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExamResponsesServiceImplement implements ExamResponsesService {
    @Autowired
    private ExamResponseRepository examResponseRepository;

    @Autowired
    private PrincipalConfiguration principalConfiguration;

    @Override
    public List<ExamResponses> createMultipleExamResponses(List<Question> questions, QuizResult quizResult) {
        CustomUserDetails userDetails = this.principalConfiguration.getCustomUserDetails();
        List<ExamResponses> examResponses = new ArrayList<>();
        for (Question question : questions) {
            examResponses.add(createSingleExamResponse(question, quizResult, userDetails.getId()));
        }
        return examResponses;
    }

    @Override
    public ExamResponses createSingleExamResponse(Question question, QuizResult quizResult, String userId) {
        ExamResponses examResponses = new ExamResponses();
        examResponses.setQuestion(question);
        examResponses.setResult(quizResult);
        examResponses.setUserId(userId);
        examResponses.setIsAnswer(false);
        return this.examResponseRepository.save(examResponses);
    }

    @Override
    public List<ExamResponses> getMultipleExamResponses(QuizResult quizResult) {
        CustomUserDetails userDetails = this.principalConfiguration.getCustomUserDetails();
        if(!quizResult.getUserId().equals(userDetails.getId())) {
            throw new ApplicationException(ErrorCode.NOT_ACCEPTABLE);
        }

        return this.examResponseRepository.findByResultId(quizResult.getId());
    }
}
