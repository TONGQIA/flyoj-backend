package com.tong.flyoj.judge.strategy;

import com.tong.flyoj.model.dto.question.JudgeConfig;
import com.tong.flyoj.model.dto.questionsubmit.JudgeInfo;
import com.tong.flyoj.model.vo.QuestionSubmitVO;

/**
 * 判题服务
 */
public interface JudgeStrategy {
    /**
     * 判断结果
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext);

}
