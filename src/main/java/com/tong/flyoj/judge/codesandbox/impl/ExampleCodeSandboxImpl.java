package com.tong.flyoj.judge.codesandbox.impl;

import com.tong.flyoj.judge.codesandbox.CodeSandbox;
import com.tong.flyoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.tong.flyoj.judge.codesandbox.model.ExecuteCodeResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 代码沙箱例子
 *
 * @author tong
 */
public class ExampleCodeSandboxImpl implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("默认代码沙箱");
        return null;
    }
}
