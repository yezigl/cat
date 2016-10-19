/**
 * Copyright 2016 focus.cn. All Rights Reserved.
 */
package com.dianping.cat.spring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * description here
 *
 * @author dehuali116041
 * @version 2016年10月18日
 */
public class CatNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("annotation-driven", new CatBeanDefinitionParser());
    }

}
