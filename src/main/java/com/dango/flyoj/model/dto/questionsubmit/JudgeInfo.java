package com.dango.flyoj.model.dto.questionsubmit;

import lombok.Data;

import java.io.Serializable;

/**
 * 判题信息
 *
 * @author dango
 * 
 */
@Data
public class JudgeInfo implements Serializable {

    /**
     * 程序执行信息
     */
    private String message;

    /**
     * 消耗内存(KB)
     */
    private Long memory;

    /**
     * 消耗时间(ms)
     */
    private Long time;
}