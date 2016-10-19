package com.dianping.cat.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.unidal.lookup.util.StringUtils;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;

/**
 * 使用AOP实现Cat的Transaction功能
 *
 * @author lidehua
 */
@Aspect
@Order(0)
public class CatAspect {

    /**
     * Transaction逻辑
     * @param pjp
     * @param catTransaction
     * @return
     * @throws Throwable
     */
    @Around(value = "execution(public * *(..)) && @annotation(catTransaction)")
    public Object transaction(final ProceedingJoinPoint pjp, CatTransaction catTransaction) throws Throwable {
        String type = StringUtils.isNotEmpty(catTransaction.type()) ? catTransaction.type() : catTransaction.value();
        String name = catTransaction.name();
        if (StringUtils.isEmpty(type)) {
            type = pjp.getTarget().getClass().getSimpleName();
        }
        if (StringUtils.isEmpty(name)) {
            name = pjp.getSignature().getName();
        }

        Transaction transaction = Cat.newTransaction(type, name);
        try {
            Object object = pjp.proceed();
            transaction.setStatus(Transaction.SUCCESS);
            return object;
        } catch (Throwable e) {
            transaction.setStatus(e);
            throw e;
        } finally {
            transaction.complete();
        }
    }

}