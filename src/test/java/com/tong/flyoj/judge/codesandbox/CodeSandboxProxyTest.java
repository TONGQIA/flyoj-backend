package com.tong.flyoj.judge.codesandbox;

import cn.hutool.json.JSONUtil;
import com.tong.flyoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.tong.flyoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.tong.flyoj.model.dto.question.JudgeCase;
import com.tong.flyoj.model.entity.Question;
import com.tong.flyoj.model.enums.QuestionSubmitLanguageEnum;
import com.tong.flyoj.service.QuestionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CodeSandboxProxyTest {

    @Value("${codesandbox.type:example}")
    private String type;

    @Resource
    private QuestionService questionService;

    @Test
    void executeCode() {
        String code = "int main(){}";
        String language = QuestionSubmitLanguageEnum.JAVA.getValue();
        Question question = questionService.getById("1722554521034543105");
        System.out.println(question.getJudgeCase());
        String judgeCase = question.getJudgeCase();
        List<JudgeCase> judgeCaseList = JSONUtil.toList(judgeCase, JudgeCase.class);
        List<String> inputList = judgeCaseList.stream().map(JudgeCase::getInput).collect(Collectors.toList());

        List<String> list = Arrays.asList("1 2", "2 3");

        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(list)
                .build();

        CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);
        CodeSandboxProxy codeSandboxProxy = new CodeSandboxProxy(codeSandbox);
        ExecuteCodeResponse executeCodeResponse = codeSandboxProxy.executeCode(executeCodeRequest);
        Assertions.assertNotNull(executeCodeResponse);
    }
}