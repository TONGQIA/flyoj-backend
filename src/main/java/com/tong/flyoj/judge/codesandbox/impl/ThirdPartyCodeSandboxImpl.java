package com.tong.flyoj.judge.codesandbox.impl;

import com.tong.flyoj.judge.codesandbox.CodeSandbox;
import com.tong.flyoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.tong.flyoj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * 第三方代码沙箱
 *
 * @author tong
 */
public class ThirdPartyCodeSandboxImpl implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("第三方代码沙箱");
        return null;
    }
}
