package com.yeqifu.sys.service.impl;

import com.yeqifu.sys.entity.Bill;
import com.yeqifu.sys.mapper.BillMapper;
import com.yeqifu.sys.service.IBillService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * InnoDB free: 10240 kB; (`typeid`) REFER `bills/billtype`(`id`) 服务实现类
 * </p>
 *
 * @author luoyi
 * @since 2019-11-18
 */
@Service
public class BillServiceImpl extends ServiceImpl<BillMapper, Bill> implements IBillService {

}
