package com.tong.flyoj.judge.strategy;

import cn.hutool.json.JSONUtil;
import com.tong.flyoj.model.dto.question.JudgeCase;
import com.tong.flyoj.model.dto.question.JudgeConfig;
import com.tong.flyoj.model.dto.questionsubmit.JudgeInfo;
import com.tong.flyoj.model.enums.JudgeInfoMessageEnum;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 默认判题策略
 */
public class DefaultJudgeStrategyImpl implements JudgeStrategy {
    @Override
    public JudgeInfo doJudge(JudgeContext judgeContext) {
        // 获得参数
        JudgeInfo judgeInfoReal = judgeContext.getJudgeInfoReal();
        Long outputMemoryReal = judgeInfoReal.getMemory();
        Long outputTimeReal = judgeInfoReal.getTime();
        JudgeConfig judgeConfigExpect = judgeContext.getJudgeConfigExpect();
        List<String> outputListReal = judgeContext.getOutputListReal();
        List<String> outputListExpect = judgeContext.getOutputListExpect();

        // 1. 设置需要返回的结果
        JudgeInfo judgeInfoResponse = new JudgeInfo();
        // 1.1 设置判题状态
        judgeInfoResponse.setMessage(JudgeInfoMessageEnum.ACCEPTED.getValue());
        judgeInfoResponse.setMemory(outputMemoryReal);
        judgeInfoResponse.setTime(outputTimeReal);


        // 2 判断输出结果的数量是否符合预期输出

        if (outputListReal.size()!=outputListExpect.size()){
            judgeInfoResponse.setMessage(JudgeInfoMessageEnum.WRONG_ANSWER.getValue());
            return judgeInfoResponse;
        }
        // 3 依次判断每一项输出是否和用例输出结果一致
        if (!outputListReal.stream().equals(outputListExpect)){
            judgeInfoResponse.setMessage(JudgeInfoMessageEnum.WRONG_ANSWER.getValue());
            return judgeInfoResponse;
        }
        // 4 判断题目的限制是否符合要求
        Long outputMemoryExpect = judgeConfigExpect.getMemoryLimit();
        Long outputTimeExpect = judgeConfigExpect.getTimeLimit();
        if (outputMemoryReal > outputMemoryExpect){
            judgeInfoResponse.setMessage(JudgeInfoMessageEnum.MEMORY_LIMIT_EXCEEDED.getValue());
            return judgeInfoResponse;
        }
        if (outputTimeReal > outputTimeExpect){
            judgeInfoResponse.setMessage(JudgeInfoMessageEnum.TIME_LIMIT_EXCEEDED.getValue());
            return judgeInfoResponse;
        }

        return judgeInfoResponse;

    }
}
