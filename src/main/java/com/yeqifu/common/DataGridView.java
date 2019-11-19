package com.yeqifu.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 落亦-
 * @Date: 2019/11/19 10:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataGridView {
    private Long code=0L;
    private String msg="";
    private Long count;
    private Object data;

    public DataGridView(Long count, Object data) {
        this.count = count;
        this.data = data;
    }
}
