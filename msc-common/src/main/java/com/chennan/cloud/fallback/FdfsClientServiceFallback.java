package com.chennan.cloud.fallback;

import com.chennan.cloud.fallback.impl.FdfsClientServiceImpl;
import com.chennan.cloud.service.FdfsClientService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class FdfsClientServiceFallback  implements FallbackFactory<FdfsClientService> {
    @Override
    public FdfsClientService create(Throwable cause) {
        return new FdfsClientServiceImpl();
    }
}
