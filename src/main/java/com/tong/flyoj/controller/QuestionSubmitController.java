package com.tong.flyoj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tong.flyoj.annotation.AuthCheck;
import com.tong.flyoj.common.BaseResponse;
import com.tong.flyoj.common.ErrorCode;
import com.tong.flyoj.common.ResultUtils;
import com.tong.flyoj.constant.UserConstant;
import com.tong.flyoj.exception.BusinessException;
import com.tong.flyoj.model.dto.question.QuestionQueryRequest;
import com.tong.flyoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.tong.flyoj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.tong.flyoj.model.entity.Question;
import com.tong.flyoj.model.entity.QuestionSubmit;
import com.tong.flyoj.model.entity.User;
import com.tong.flyoj.model.vo.QuestionSubmitVO;
import com.tong.flyoj.service.QuestionSubmitService;
import com.tong.flyoj.service.UserService;
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
 * @author tong
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
     * @param questionSubmitAddRequest
     * @param request
     * @return resultNum 提交题目
     */
    @PostMapping("/")
    public BaseResponse<Long> doQuestionSubmit(@RequestBody QuestionSubmitAddRequest questionSubmitAddRequest,
            HttpServletRequest request) {
        if (questionSubmitAddRequest == null || questionSubmitAddRequest.getQuestionId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 登录才能提交
        final User loginUser = userService.getLoginUser(request);

        long result = questionSubmitService.doQuestionSubmit(questionSubmitAddRequest, loginUser);
        return ResultUtils.success(result);
    }

    /**
     * 分页获取题目提交列表（除管理员外，用户只能看到非答案、提交代码等公开信息）
     * @param questionSubmitQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/list/page")
    public BaseResponse<Page<QuestionSubmitVO>> listQuestionSubmitByPage(@RequestBody QuestionSubmitQueryRequest questionSubmitQueryRequest,
                                                                         HttpServletRequest request) {
        long current = questionSubmitQueryRequest.getCurrent();
        long size = questionSubmitQueryRequest.getPageSize();
        // 从数据库中查询原始的题目提交分页信息
        Page<QuestionSubmit> questionSubmitPage = questionSubmitService.page(new Page<>(current, size),
                questionSubmitService.getQueryWrapper(questionSubmitQueryRequest));

        User loginUser = userService.getLoginUser(request);
        // 脱敏后返回
        return ResultUtils.success(questionSubmitService.getQuestionSubmitVOPage(questionSubmitPage,loginUser));
    }

}
