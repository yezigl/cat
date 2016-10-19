package com.dianping.cat.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.dianping.cat.message.Transaction;

/**
 * 提供CAT {@link Transaction}自动化的配置方式
 * <br>
 * <a href="http://cat.dianpingoa.com/cat/r/home?op=view&docName=integration">CAT集成</a>
 * <br>
 * 用法:
 * <pre>
 *    &#064;CatTransaction(type = "type", name = "name")
 *    public String demoMethod {
 *        ...
 *    }
 * </pre>
 *
 * @author lidehua
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface CatTransaction {

    /**
     * alias for {@link #type()}
     */
    String value() default "";

    /**
     * Cat.newTransaction(type, name)中的type，如果不指定，取类的名字
     */
    String type() default "";

    /**
     * Cat.newTransaction(type, name)中的name，如果不指定，取方法的名字
     */
    String name() default "";
}