package com.dango.flyoj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dango.flyoj.model.entity.Question;
import com.dango.flyoj.service.QuestionService;
import com.dango.flyoj.mapper.QuestionMapper;
import org.springframework.stereotype.Service;

/**
* @author tong
* @description 针对表【question(题目)】的数据库操作Service实现
* @createDate 2023-11-01 20:17:55
*/
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
    implements QuestionService{

}




