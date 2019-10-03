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

    @PostMapping(value = "/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(@RequestParam(value = "file") MultipartFile file){
        return fastDfsService.uploadFile(file);
    }

    @GetMapping(value = "/downloadFile")
    public ResponseEntity<byte[]> downloadFile(String fileUrl, String fileName, HttpServletRequest request) throws UnsupportedEncodingException {
        return fastDfsService.downloadFile(fileUrl, fileName, request);
    }
}
