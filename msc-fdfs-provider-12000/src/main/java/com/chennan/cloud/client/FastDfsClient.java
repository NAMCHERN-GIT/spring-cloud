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
