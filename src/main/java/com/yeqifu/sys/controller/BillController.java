package com.yeqifu.sys.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yeqifu.common.DataGridView;
import com.yeqifu.common.ResultObj;
import com.yeqifu.sys.entity.Bill;
import com.yeqifu.sys.entity.Billtype;
import com.yeqifu.sys.service.IBillService;
import com.yeqifu.sys.service.IBilltypeService;
import com.yeqifu.vo.BillsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * InnoDB free: 10240 kB; (`typeid`) REFER `bills/billtype`(`id`) 前端控制器
 * </p>
 *
 * @author luoyi
 * @since 2019-11-18
 */
@Controller
@RequestMapping("/bills")
public class BillController {

    @Autowired
    private IBillService billService;

    @Autowired
    private IBilltypeService billtypeService;

    /**
     * 跳转到主页
     * @return
     */
    @RequestMapping("toBillsList")
    public String toBillsList(){
        return "list";
    }

    /**
     *
     * @param billVo
     * @return
     */
    @ResponseBody
    @RequestMapping("loadAllBills")
    public DataGridView loadAllBills(BillsVo billVo){
        IPage<Bill> page = new Page<Bill>(billVo.getPage(),billVo.getLimit());
        QueryWrapper<Bill> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(null!=billVo.getTypeid()&&billVo.getTypeid()!=0,"typeid",billVo.getTypeid());
        queryWrapper.ge(billVo.getStartDate()!=null,"billtime",billVo.getStartDate());
        queryWrapper.le(billVo.getEndDate()!=null,"billtime",billVo.getEndDate());
        queryWrapper.orderByDesc("billtime");
        billService.page(page,queryWrapper);

        List<Bill> records = page.getRecords();
        for (Bill bills : records) {
            Billtype billtype = this.billtypeService.getById(bills.getTypeid());
            bills.setTypeName(billtype.getName());
        }
        return new DataGridView(page.getTotal(),records);
    }

    /**
     * 添加账单
     * @param billsVo
     * @return
     */
    @RequestMapping("addBills")
    @ResponseBody
    public ResultObj addBills(BillsVo billsVo){
        try {
            this.billService.save(billsVo);
            return new ResultObj(200,"添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultObj(-1,"录入失败");
        }
    }

}

