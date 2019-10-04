package com.chennan.cloud.service;

import com.chennan.cloud.config.MultipartSupportConfig;
import com.chennan.cloud.fallback.FdfsClientServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@FeignClient(name = "msc-fdfs-provider"
         , fallbackFactory = FdfsClientServiceFallback.class
          , configuration = {MultipartSupportConfig.class})
public interface FdfsClientService {

    /**
     * 文件上传
     * 最后返回fastDFS中的文件名称;group1/M00/01/04/CgMKrVvS0geAQ0pzAACAAJxmBeM793.doc
     * @param file     文件字节
     * @return fastDfs路径
     */
    @PostMapping(value = "/fastDfs/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadFile(@RequestParam(value = "file") MultipartFile file);

    /**
     * 下载文件字节
     * @param fileUrl 文件URL
     * @return 文件字节
     */
    @GetMapping(value = "/downloadFileBytes")
    byte[] downloadFileBytes(String fileUrl);

    /**
     * 下载文件
     *  返回文件字节流大小
     * @param fileUrl 文件URL
     * @param fileName 下载后的文件名称
     * @param request SpringMVC 内置request对象
     * @return 文件ResponseEntity响应
     */
    @GetMapping(value = "/fastDfs/downloadFile")
    ResponseEntity<byte[]> downloadFile(String fileUrl, String fileName, HttpServletRequest request);

}
