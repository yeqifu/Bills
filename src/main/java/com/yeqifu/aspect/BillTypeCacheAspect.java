package com.yeqifu.aspect;

import com.yeqifu.sys.entity.Billtype;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 落亦-
 * @Date: 2019/11/19 14:51
 */
@Aspect
@Component
@EnableAspectJAutoProxy
public class BillTypeCacheAspect {

    /**
     * 缓存对象
     */
    private Map<String,Object> cache = new HashMap<>();

    /**
     * 缓存前缀
     */
    private static final String BILL_TYPE_CACHE_PREFIX="billtype:";

    @Pointcut("execution(* com.yeqifu.sys.service.impl.BilltypeServiceImpl.getById(..))")
    public void pc(){

    }

    @Around(value = "pc()")
    public Object cacheBillType(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();//得到目标方法的参数
        //取出ID
        Integer typeId = (Integer) args[0];
        //从缓存里面取对象
        Object obj =cache.get(BILL_TYPE_CACHE_PREFIX+typeId);
        if (null!=obj){ //说明缓存里面有数据
            System.out.println("缓存里面有数据");
            return obj;
        }else {
            System.out.println("缓存里面没有数据，执行SQL查询");
            //放行执行目标方法 查询数据
            Billtype res = (Billtype) point.proceed();
            //放入缓存
            cache.put(BILL_TYPE_CACHE_PREFIX+res.getId(),res);
            return res;
        }
    }

}
