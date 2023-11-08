package com.tong.flyoj.judge.codesandbox;

import com.tong.flyoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.tong.flyoj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * 代码沙箱接口
 *
 * @author tong
 *
 */
public interface CodeSandbox {
    /**
     * 执行代码
     * @param executeCodeRequest
     * @return
     */
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
