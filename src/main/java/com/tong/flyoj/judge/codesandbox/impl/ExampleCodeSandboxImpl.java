package com.tong.flyoj.judge.codesandbox.impl;

import com.tong.flyoj.judge.codesandbox.CodeSandbox;
import com.tong.flyoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.tong.flyoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.tong.flyoj.judge.codesandbox.model.JudgeInfo;
import com.tong.flyoj.model.enums.JudgeInfoMessageEnum;
import com.tong.flyoj.model.enums.QuestionSubmitStatusEnum;

import java.util.List;

/**
 * 代码沙箱例子
 *
 * @author tong
 */
public class ExampleCodeSandboxImpl implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("默认代码沙箱");
        List<String> inputList = executeCodeRequest.getInputList();
        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        executeCodeResponse.setOutputList(inputList);
        executeCodeResponse.setMessage("测试成功");
        executeCodeResponse.setStatus(QuestionSubmitStatusEnum.WAITING.getValue());
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getValue());
        judgeInfo.setMemory(100L);
        judgeInfo.setTime(100L);
        executeCodeResponse.setJudgeInfo(judgeInfo);
        return executeCodeResponse;

    }
}
