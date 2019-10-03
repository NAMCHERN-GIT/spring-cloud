package com.chennan.cloud.service;

import com.chennan.cloud.client.FastDfsClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@Service
public class FastDfsService {

    @Autowired private FastDfsClient client;

    public String uploadFile(MultipartFile file){
        byte[] bytes = new byte[0];
        try {
            bytes = file.getBytes();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        String originalFileName = file.getOriginalFilename();   //  获取源文件名称
        assert originalFileName != null;    //  获取文件后缀--.doc
        String extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        String fileName = file.getName();
        long fileSize = file.getSize();     //  获取文件大小
        log.info(originalFileName + "==" + fileName + "==" + fileSize + "==" + extension + "==" + bytes.length);
        return client.uploadFile(bytes, fileSize, extension);
    }

    public ResponseEntity<byte[]> downloadFile(String fileUrl, String fileName, HttpServletRequest request) throws UnsupportedEncodingException {
        byte[] bytes = client.downloadFile(fileUrl);
        HttpHeaders headers = new HttpHeaders();
        // 处理IE下载文件的中文名称乱码的问题
        String header = request.getHeader("User-Agent").toUpperCase();
        if (header.contains("MSIE") || header.contains("TRIDENT") || header.contains("EDGE")) {
            fileName = URLEncoder.encode(fileName, "UTF-8");
            fileName = fileName.replace("+", "%20");    //IE下载文件名空格变+号问题
        } else {
            fileName = new String(fileName.getBytes(), StandardCharsets.ISO_8859_1);
        }
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }
}
