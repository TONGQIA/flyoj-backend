package com.tong.flyoj.model.dto.question;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 题目用例
 *
 * @author tong
 * 
 */
@Data
public class JudgeCase implements Serializable {
    /**
     * 输入用例
     */
    private String input;

    /**
     * 输出用例
     */
    private String output;
}