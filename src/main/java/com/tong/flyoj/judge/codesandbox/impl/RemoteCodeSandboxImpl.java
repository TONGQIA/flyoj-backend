package com.tong.flyoj.judge.codesandbox.impl;

import com.tong.flyoj.judge.codesandbox.CodeSandbox;
import com.tong.flyoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.tong.flyoj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * 远程代码沙箱
 *
 * @author tong
 */
public class RemoteCodeSandboxImpl implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("远程代码沙箱");
        return null;
    }
}
