package com.chennan.cloud.web;

import com.chennan.cloud.service.FastDfsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@RequestMapping("/fastDfs")
@RestController
public class FastDfsController {

    @Autowired FastDfsService fastDfsService;

    /**
     * 文件上传
     * 最后返回fastDFS中的文件名称;group1/M00/01/04/CgMKrVvS0geAQ0pzAACAAJxmBeM793.doc
     * @param file     文件字节
     * @return fastDfs路径
     */
    @PostMapping(value = "/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(@RequestParam(value = "file") MultipartFile file){
        return fastDfsService.uploadFile(file);
    }

    /**
     * 下载文件字节
     * @param fileUrl 文件URL
     * @return 文件字节
     */
    @GetMapping(value = "/downloadFileBytes")
    public byte[] downloadFileBytes(String fileUrl){
        return fastDfsService.downloadFileBytes(fileUrl);
    }

    /**
     * 下载文件
     *  返回文件字节流大小
     * @param fileUrl 文件URL
     * @param fileName 下载后的文件名称
     * @param request SpringMVC 内置request对象
     * @return 文件ResponseEntity响应
     */
    @GetMapping(value = "/downloadFile")
    public ResponseEntity<byte[]> downloadFile(String fileUrl, String fileName, HttpServletRequest request) throws UnsupportedEncodingException {
        return fastDfsService.downloadFile(fileUrl, fileName, request);
    }
}
