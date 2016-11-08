/**
 * Copyright 2016 focus.cn. All Rights Reserved.
 */
package com.dianping.cat.configuration;

/**
 * 工具类
 *
 * @author lidehua
 * @since 2016年11月7日
 */
public class ClientConfigUtils {

    private static final String ENV_PRODUCT = "product";

    private static final String TEST_SUFFIX = ".test";

    /**
     * 是否测试环境
     * 
     * @return
     */
    public static boolean isTestEnv() {
        String env = System.getProperty("ENV");
        if (env == null) {
            env = System.getenv("ENV");
        }
        if (env != null) {
            return !ENV_PRODUCT.equalsIgnoreCase(env);
        }
        return true;
    }

    public static String getDomainId(String domain) {
        if (isTestEnv()) {
            if (domain.contains(TEST_SUFFIX)) {
                return domain;
            }
            return domain + TEST_SUFFIX;
        }
        return domain;
    }
}
