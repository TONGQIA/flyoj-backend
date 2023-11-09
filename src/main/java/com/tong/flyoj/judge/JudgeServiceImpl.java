package com.tong.flyoj.judge;

import cn.hutool.json.JSONUtil;
import com.tong.flyoj.common.ErrorCode;
import com.tong.flyoj.exception.BusinessException;
import com.tong.flyoj.judge.codesandbox.CodeSandbox;
import com.tong.flyoj.judge.codesandbox.CodeSandboxFactory;
import com.tong.flyoj.judge.codesandbox.CodeSandboxProxy;
import com.tong.flyoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.tong.flyoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.tong.flyoj.judge.strategy.DefaultJudgeStrategyImpl;
import com.tong.flyoj.judge.strategy.JudgeContext;
import com.tong.flyoj.judge.strategy.JudgeStrategy;
import com.tong.flyoj.model.dto.question.JudgeCase;
import com.tong.flyoj.model.dto.question.JudgeConfig;
import com.tong.flyoj.model.dto.questionsubmit.JudgeInfo;
import com.tong.flyoj.model.entity.Question;
import com.tong.flyoj.model.entity.QuestionSubmit;
import com.tong.flyoj.model.enums.QuestionSubmitStatusEnum;
import com.tong.flyoj.service.QuestionService;
import com.tong.flyoj.service.QuestionSubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 判题服务实现类
 */
@Service
public class JudgeServiceImpl implements JudgeService {

    @Value("${codesandbox.type:example}")
    private String type;


    @Autowired
    private QuestionSubmitService questionSubmitService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private JudgeManager judgeManager;

    @Override
    public QuestionSubmit doJudge(long questionSubmitId) {
        // 1. 根据题目提交id获得题目提交信息
        QuestionSubmit questionSubmit = questionSubmitService.getById(questionSubmitId);
        if (questionSubmit == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"题目提交信息不存在");
        }
        // 2. 查看题目是否存在
        Question question = questionService.getById(questionSubmit.getQuestionId());
        if (question == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"题目信息不存在");
        }
        // 3. 更改题目状态
        if (questionSubmit.getStatus().equals(QuestionSubmitStatusEnum.RUNNING.getValue()) ){
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"题目正在判题");
        }
        QuestionSubmit questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmit.getId());
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        boolean update = questionSubmitService.updateById(questionSubmitUpdate);
        if (!update){
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"题目状态更新错误");
        }

        // 4. 存在就将执行的请求类需要的值加入：code、language、inputList
        // 4.1 获取测试用例
        List<JudgeCase> judgeCaseList = JSONUtil.toList(question.getJudgeCase(), JudgeCase.class);
        List<String> inputList = judgeCaseList.stream().map(JudgeCase::getInput).collect(Collectors.toList());
        // 4.2 将请求需要的参数加入
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(questionSubmit.getCode())
                .language(questionSubmit.getLanguage())
                .inputList(inputList)
                .build();

        CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);
        CodeSandboxProxy codeSandboxProxy = new CodeSandboxProxy(codeSandbox);
        ExecuteCodeResponse executeCodeResponse = codeSandboxProxy.executeCode(executeCodeRequest);
        // 5. 根据沙箱的结果设置题目的判题状态和信息
        List<String> outputListReal = executeCodeResponse.getOutputList();
        List<String> outputListExpect = judgeCaseList.stream().map(JudgeCase::getOutput).collect(Collectors.toList());
        JudgeInfo judgeInfoReal = executeCodeResponse.getJudgeInfo();
        JudgeConfig judgeConfigExpect = JSONUtil.toBean(question.getJudgeConfig(), JudgeConfig.class);
        JudgeContext judgeContext = JudgeContext.builder()
                .outputListReal(outputListReal)
                .outputListExpect(outputListExpect)
                .judgeConfigExpect(judgeConfigExpect)
                .judgeInfoReal(judgeInfoReal)
                .questionSubmit(questionSubmit)
                .build();
        JudgeInfo judgeInfo = judgeManager.doJudge(judgeContext);
        // 6. 更新数据库
        questionSubmitUpdate= new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        questionSubmitUpdate.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
        update = questionSubmitService.updateById(questionSubmitUpdate);
        if (!update){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目提交状态更新错误");
        }
        QuestionSubmit questionSubmitResult = questionSubmitService.getById(questionSubmitId);
        return questionSubmitResult;
    }
}
