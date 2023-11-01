package com.dango.flyoj.controller;

import com.dango.flyoj.common.BaseResponse;
import com.dango.flyoj.common.ErrorCode;
import com.dango.flyoj.common.ResultUtils;
import com.dango.flyoj.exception.BusinessException;
import com.dango.flyoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.dango.flyoj.model.entity.User;
import com.dango.flyoj.service.QuestionSubmitService;
import com.dango.flyoj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 *  题目提交接口
 *
 * @author dango
 * 
 */
@RestController
@RequestMapping("/questionsubmit")
@Slf4j
public class QuestionSubmitController {

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private UserService userService;

    /**
     * 点赞 / 取消点赞
     *
     * @param questionSubmitAddRequest
     * @param request
     * @return resultNum 提交题目
     */
    @PostMapping("/")
    public BaseResponse<Integer> doQuestionSubmit(@RequestBody QuestionSubmitAddRequest questionSubmitAddRequest,
            HttpServletRequest request) {
        if (questionSubmitAddRequest == null || questionSubmitAddRequest.getQuestionId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 登录才能提交
        final User loginUser = userService.getLoginUser(request);
        long questionId = questionSubmitAddRequest.getQuestionId();
        int result = questionSubmitService.doQuestionSubmit(questionId, loginUser);
        return ResultUtils.success(result);
    }

}
