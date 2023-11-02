package com.tong.flyoj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tong.flyoj.model.entity.QuestionSubmit;
import com.tong.flyoj.model.entity.User;

/**
* @author tong
* @description 针对表【question_submit(题目提交)】的数据库操作Service
* @createDate 2023-11-01 20:25:50
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {

    /**
     * 提交
     *
     * @param questionId
     * @param loginUser
     * @return
     */
    int doQuestionSubmit(long questionId, User loginUser);

    /**
     * 帖子点赞（内部服务）
     *
     * @param userId
     * @param questionId
     * @return
     */
    int doQuestionSubmitInner(long userId, long questionId);
}
