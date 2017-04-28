/**
 * Copyright 2016 focus.cn. All Rights Reserved.
 */
package com.dianping.cat.configuration;

import java.util.Arrays;
import java.util.List;

/**
 * 工具类
 *
 * @author lidehua
 * @since 2016年11月7日
 */
public class ClientConfigUtils {

    private static final List<String> ENV_PRODUCT = Arrays.asList("product", "prod", "work");

    private static final String TEST_SUFFIX = ".test";
    
    private static final String DOMAIN_CAT = "cat";

    /**
     * 是否测试环境
     * 
     * @return
     */
    public static boolean isTestEnv() {
        String env = System.getProperty("spring.profiles.active");
        if (env == null) {
            env = System.getenv("spring.profiles.active");
        }
        if (env != null) {
            return !ENV_PRODUCT.contains(env.toLowerCase());
        }
        return true;
    }

    public static String getDomainId(String domain) {
        if (domain.equals(DOMAIN_CAT)) {
            return domain;
        }
        if (isTestEnv()) {
            if (domain.contains(TEST_SUFFIX)) {
                return domain;
            }
            return domain + TEST_SUFFIX;
        }
        return domain;
    }
}
