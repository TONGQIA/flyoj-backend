package com.tong.flyoj.judge.strategy;

import com.tong.flyoj.model.dto.question.JudgeConfig;
import com.tong.flyoj.model.dto.questionsubmit.JudgeInfo;
import com.tong.flyoj.model.entity.QuestionSubmit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JudgeContext {
    private JudgeInfo judgeInfoReal;

    private JudgeConfig judgeConfigExpect;

    private List<String> outputListReal;

    private List<String> outputListExpect;

    private QuestionSubmit questionSubmit;



}
