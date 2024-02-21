package com.tong.flyoj.judge;

import com.tong.flyoj.judge.strategy.DefaultJudgeStrategyImpl;
import com.tong.flyoj.judge.strategy.JavaLanguageJudgeStrategyImpl;
import com.tong.flyoj.judge.strategy.JudgeContext;
import com.tong.flyoj.judge.strategy.JudgeStrategy;
import com.tong.flyoj.judge.codesandbox.model.JudgeInfo;
import com.tong.flyoj.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

/**
 * 判题管理（简化版）-->策略模式
 */
@Service
public class JudgeManager {
    JudgeInfo doJudge(JudgeContext judgeContext){
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy;
        switch (language){
            case "java":
                judgeStrategy = new JavaLanguageJudgeStrategyImpl();
                break;
            default:
                judgeStrategy = new DefaultJudgeStrategyImpl();

        }
        return judgeStrategy.doJudge(judgeContext);

    }

}
