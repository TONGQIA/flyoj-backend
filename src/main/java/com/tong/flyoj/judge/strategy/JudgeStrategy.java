package com.tong.flyoj.judge.strategy;

import com.tong.flyoj.judge.codesandbox.model.JudgeInfo;

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
