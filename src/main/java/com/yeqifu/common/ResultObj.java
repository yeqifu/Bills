package com.yeqifu.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 落亦-
 * @Date: 2019/11/18 18:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultObj {
    //业务响应码
    private Integer code;
    //业务消息
    private String msg;

}
