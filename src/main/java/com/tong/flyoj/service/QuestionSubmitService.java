package com.tong.flyoj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tong.flyoj.model.dto.question.QuestionQueryRequest;
import com.tong.flyoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.tong.flyoj.model.entity.Question;
import com.tong.flyoj.model.entity.QuestionSubmit;
import com.tong.flyoj.model.entity.User;
import com.tong.flyoj.model.vo.QuestionVO;

import javax.servlet.http.HttpServletRequest;

/**
* @author tong
* @description 针对表【question_submit(题目提交)】的数据库操作Service
* @createDate 2023-11-01 20:25:50
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {

    /**
     * 提交
     *
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return
     */
    int doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);

    /**
     * 帖子点赞（内部服务）
     *
     * @param userId
     * @param questionId
     * @return
     */
    int doQuestionSubmitInner(long userId, long questionId);


    /**
     * 获取查询条件
     *
     * @param questionSumbitQueryRequest
     * @return
     */
    QueryWrapper<QuestionSumbit> getQueryWrapper(QuestionSumbitQueryRequest questionSumbitQueryRequest);


    /**
     * 获取帖子封装
     *
     * @param questionSumbit
     * @param request
     * @return
     */
    QuestionSumbitVO getQuestionSumbitVO(QuestionSumbit questionSumbit, HttpServletRequest request);

    /**
     * 分页获取帖子封装
     *
     * @param questionSumbitPage
     * @param request
     * @return
     */
    Page<QuestionSumbitVO> getQuestionSumbitVOPage(Page<QuestionSumbit> questionSumbitPage, HttpServletRequest request);

}
