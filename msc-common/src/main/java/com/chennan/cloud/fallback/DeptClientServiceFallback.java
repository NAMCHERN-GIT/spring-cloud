package com.chennan.cloud.fallback;

import com.chennan.cloud.fallback.impl.DeptClientServiceImpl;
import com.chennan.cloud.service.DeptClientService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 千万不要忘记添加注解 {@link @Component}
 * @author chen.nan
 */
@Component
public class DeptClientServiceFallback implements FallbackFactory<DeptClientService> {
    @Override
    public DeptClientService create(Throwable cause) {
        return new DeptClientServiceImpl();
    }
}
