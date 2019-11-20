package com.yeqifu.vo;

import com.yeqifu.sys.entity.Bill;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: 落亦-
 * @Date: 2019/11/19 10:40
 */
@Data
@EqualsAndHashCode
public class BillsVo extends Bill {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private Integer page=1;
    private Integer limit=10;
}
