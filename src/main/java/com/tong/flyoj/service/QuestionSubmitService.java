package com.tong.flyoj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tong.flyoj.model.dto.question.QuestionQueryRequest;
import com.tong.flyoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.tong.flyoj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.tong.flyoj.model.entity.Question;
import com.tong.flyoj.model.entity.QuestionSubmit;
import com.tong.flyoj.model.entity.User;
import com.tong.flyoj.model.vo.QuestionSubmitVO;
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
    long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);



    /**
     * 获取查询条件
     *
     * @param questionSubmitQueryRequest
     * @return
     */
    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest);


    /**
     * 获取帖子封装
     *
     * @param questionSubmit
     * @param loginUser
     * @return
     */
    QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser);

    /**
     * 分页获取帖子封装
     *
     * @param questionSubmitPage
     * @param loginUser
     * @return
     */
    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, User loginUser);

}
