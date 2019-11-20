package com.yeqifu.sys.controller;


import com.yeqifu.common.DataGridView;
import com.yeqifu.sys.service.IBilltypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * InnoDB free: 10240 kB 前端控制器
 * </p>
 *
 * @author luoyi
 * @since 2019-11-18
 */
@RestController
@RequestMapping("/billtype")
public class BilltypeController {

    @Autowired
    private IBilltypeService billtypeService;

    @RequestMapping("loadAllBillType")
    public DataGridView loadAllBillType(){
        return new DataGridView(0L,billtypeService.list());
    }

}

