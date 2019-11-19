package com.yeqifu.vo;

import com.yeqifu.sys.entity.Bill;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @Author: 落亦-
 * @Date: 2019/11/19 10:40
 */
@Data
@EqualsAndHashCode
public class BillsVo extends Bill {
    private Date startDate;
    private Date endDate;

    private Integer page=1;
    private Integer limit=10;
}
