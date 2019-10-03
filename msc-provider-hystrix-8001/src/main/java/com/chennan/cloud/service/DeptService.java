package com.chennan.cloud.service;


import com.chennan.cloud.bo.Dept;
import com.chennan.cloud.dao.DeptMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * 业务层实现类
 * @author chen.nan
 */
@Slf4j
@Service
public class DeptService {

    @Autowired private DeptMapper deptMapper;

    public int add(Dept dept) {
        return deptMapper.insert(dept);
    }

    public Dept get(Long deptNo) {
        return deptMapper.selectById(deptNo);
    }

    public List<Dept> list() {
        return deptMapper.selectList(null);
    }

    public int edit(Dept dept) {
        return deptMapper.updateById(dept);
    }

    public int delete(Long deptNo) {
        return deptMapper.deleteById(deptNo);
    }

    /**
     * 文件上传
     * @param file 文件
     * @return 0-上传失败 1-上传成功
     */
    public int upload(MultipartFile file){
        log.info("文件上传...provider...");
        String fileName = file.getOriginalFilename();
        Path path = Paths.get("E:\\01-code\\study\\file", fileName);
        try {
            file.transferTo(path);
            return 1;
        } catch (IOException e) {
            log.error(e.getMessage());
            return 0;
        }
    }

    public ResponseEntity<byte[]> download(HttpServletRequest request){
        log.info("文件下载...provider...");
        try(InputStream in = new FileInputStream(new File("E:\\01-code\\study\\file\\新建文本文档.txt"))) {
            String fileName = "test_download.txt";
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
            return new ResponseEntity<>(IOUtils.toByteArray(in) , headers, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
