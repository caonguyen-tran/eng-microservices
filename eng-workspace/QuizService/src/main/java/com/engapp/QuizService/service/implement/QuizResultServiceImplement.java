package com.engapp.QuizService.service.implement;

import com.engapp.QuizService.configuration.CustomUserDetails;
import com.engapp.QuizService.configuration.PrincipalConfiguration;
import com.engapp.QuizService.exception.ApplicationException;
import com.engapp.QuizService.exception.ErrorCode;
import com.engapp.QuizService.pojo.QuizResult;
import com.engapp.QuizService.repository.QuizResultRepository;
import com.engapp.QuizService.service.QuizResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class QuizResultServiceImplement implements QuizResultService {
    @Autowired
    private PrincipalConfiguration principalConfiguration;

    @Autowired
    private QuizResultRepository quizResultRepository;

    @Override
    public QuizResult createQuizResult(QuizResult quizResult) {
        CustomUserDetails customUserDetails = principalConfiguration.getCustomUserDetails();
        if(getQuizResultByUserAndQuestionSet(customUserDetails.getId(), quizResult.getQuestionSet().getId()) != null){
            return null;
        }
        quizResult.setCreatedDate(Instant.now());
        quizResult.setStartTime(Instant.now());
        quizResult.setEndTime(Instant.now());
        quizResult.setOverallPoint(0);
        quizResult.setCorrectPercentage(0.0);
        quizResult.setCorrectAnswers(0);
        quizResult.setUserId(customUserDetails.getId());
        return this.quizResultRepository.save(quizResult);
    }

    @Override
    public QuizResult getQuizResultByUserAndQuestionSet(String userId, int questionSetId) {
        return this.quizResultRepository.findByUserAndQuestionSet(userId, questionSetId);
    }

    @Override
    public QuizResult findById(int id) {
        return this.quizResultRepository.findById(id).orElseThrow(() -> new ApplicationException(ErrorCode.NOT_EXIST));
    }

    @Override
    public List<QuizResult> getByOwner() {
        CustomUserDetails customUserDetails = principalConfiguration.getCustomUserDetails();
        return this.quizResultRepository.listByUserId(customUserDetails.getId());
    }
}
