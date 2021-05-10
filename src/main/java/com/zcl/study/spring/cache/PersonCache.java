package com.zcl.study.spring.cache;

import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Service;

/**
 * spring-demo .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-3-12 .
 */
@Service
public class PersonCache {

    private LoadingCache<String, String> dataListCache;


}
