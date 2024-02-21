package com.tong.flyoj.judge.codesandbox;

import com.tong.flyoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.tong.flyoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.tong.flyoj.model.enums.QuestionSubmitLanguageEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Validate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class CodeSandboxTest {

    @Value("${codesandbox.type:example}")
    private String type;

    @Test
    void executeCode() {
        String code = "public class Main {\n" +
                "\n" +
                "    public static void main(String[] args) {\n" +
                "        int a = Integer.parseInt(args[0]);\n" +
                "        int b = Integer.parseInt(args[1]);\n" +
                "        System.out.println(\"结果：\"+ (a + b));\n" +
                "    }\n" +
                "}";
        String language = QuestionSubmitLanguageEnum.JAVA.getValue();
        List<String> list = Arrays.asList("1 2", "2 3");

        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(list)
                .build();

        CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        Assertions.assertNotNull(executeCodeResponse);
    }
}