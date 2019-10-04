# spring-cloud 整合 FastDFS实现文件上传，下载
## 核心实现
### 1.pom加入fastdfs-client依赖,参考文件[pom.xml](./pom.xml)
```xml
    <dependency>
        <groupId>com.github.tobato</groupId>
        <artifactId>fastdfs-client</artifactId>
        <version>1.26.7</version>
    </dependency>
```
### 2.yaml中核心属性配置,参考配置文件[application.yml](./src/main/resources/application.yml)
```yaml
fdfs:
  so-timeout: 1500
  connect-timeout: 600
  # FastDfs 服务器列表
  tracker-list:
    - 192.168.232.151:22122
  pool:
    max-total: 200
    max-total-per-key: 50
    max-wait-millis: 5000
```
### 3.实现上传下载核心实现类
[FastDfsConfig](./src/main/java/com/chennan/cloud/config/FastDfsConfig.java) 配置类
```java
package com.chennan.cloud.config;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;

/**
 * 引入FastDFS配置类
 * @author chen.nan
 */
@Configuration
@Import(FdfsClientConfig.class)
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class FastDfsConfig {}

```
[FastDfsClient](./src/main/java/com/chennan/cloud/client/FastDfsClient.java) 实现类
```java
package com.chennan.cloud.client;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;

@Slf4j
@Component
public class FastDfsClient {

    @Autowired private FastFileStorageClient fastFileStorageClient;

    /**
     * 文件上传
     * 最后返回fastDFS中的文件名称;group1/M00/01/04/CgMKrVvS0geAQ0pzAACAAJxmBeM793.doc
     *
     * @param bytes     文件字节
     * @param fileSize  文件大小
     * @param extension 文件扩展名
     * @return fastDfs路径
     */
    public String uploadFile(byte[] bytes, long fileSize, String extension) {
        log.info("上传文件至FastDFS...start");
        long start = System.currentTimeMillis();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        StorePath storePath = fastFileStorageClient.uploadFile(byteArrayInputStream, fileSize, extension, null);
        long end = System.currentTimeMillis();
        long cost = end - start;
        log.info(storePath.getGroup() + "==" + storePath.getPath() + "======" + storePath.getFullPath());
        log.info("上传文件至FastDFS...end, cost time is {} milliseconds", cost);
        return storePath.getFullPath();
    }

    /**
     * 下载文件
     *  返回文件字节流大小
     * @param fileUrl 文件URL
     * @return 文件字节
     */
    public byte[] downloadFile(String fileUrl){
        log.info("从FastDFS中下载文件...start");
        long start = System.currentTimeMillis();
        String group = fileUrl.substring(0, fileUrl.indexOf("/"));
        String path = fileUrl.substring(fileUrl.indexOf("/") + 1);
        DownloadByteArray downloadByteArray = new DownloadByteArray();
        byte[] bytes = fastFileStorageClient.downloadFile(group, path, downloadByteArray);
        long end = System.currentTimeMillis();
        long cost = end - start;
        log.info("从FastDFS中下载文件....end, cost time is {} milliseconds", cost);
        return bytes;
    }
}
```