package com.chennan.cloud.web;

import com.chennan.cloud.bo.Dept;
import com.chennan.cloud.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 消费端web层。
 * @author chen.nan
 */
@RequestMapping("/dept")
@RestController
public class DeptFeignConsumerController {

    /**
     * 使用Feign，接口+注解的方式，简化client的开发
     */
    @Autowired private DeptClientService clientService;

    @PostMapping("/add")
    public Integer add(Dept dept){
        return clientService.add(dept);
    }

    @GetMapping("/get")
    public Dept get(Long deptNo){
        return clientService.get(deptNo);
    }

    @GetMapping("/list")
    public List list(){
        return clientService.list();
    }

    @PostMapping("/edit")
    public Integer edit(Dept dept){
        return clientService.edit(dept);
    }

    @PostMapping("/delete")
    public Integer delete(Long deptNo){
        return clientService.delete(deptNo);
    }

    @GetMapping("/discovery")
    public String discovery(){
        return clientService.discovery();
    }

    /**
     * 文件上传
     * @param file 文件
     * @return 0-上传失败 1-上传成功
     */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public int upload(@RequestPart(value = "file") MultipartFile file){
        return clientService.upload(file);
    }

    /**
     * 文件下载
     * @return ResponseEntity<byte[]>
     */
    @GetMapping("/download")
    public ResponseEntity<byte[]> download(){
        return clientService.download();
    }

}
