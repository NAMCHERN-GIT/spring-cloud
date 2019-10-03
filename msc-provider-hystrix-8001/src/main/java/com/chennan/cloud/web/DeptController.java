package com.chennan.cloud.web;

import com.chennan.cloud.bo.Dept;
import com.chennan.cloud.service.DeptService;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 控制层实现
 * @author chen.nan
 */
@RequestMapping("/dept")
@RestController
public class DeptController {

    /**
     * 部门service
     */
    @Autowired private DeptService deptService;

    /**
     * 获取服务中的client
     */
    @Autowired private DiscoveryClient client;

    /**
     * 新增部门信息
     * @param dept 部门信息
     * @return 1-成功  0-失败
     */
    @PostMapping("/add")
    public int add(Dept dept) {
        return deptService.add(dept);
    }

    /**
     * {@link @HystrixCommand(fallbackMethod = "processHandlerGet")}
     * 一旦调用服务方法失败并抛出了错误的信息后，会自动调用 @HystrixCommand 标注好的 fallbackMethod 调用类中的指定方法。
     *
     * 缺点：每一个方法都遭这里面单独定义一个fallback方法，太繁琐，增加了系统的耦合度
     * @param deptNo 部门编号
     * @return  Dept
     */
    @GetMapping("/get")
    //@HystrixCommand(fallbackMethod = "processHandlerGet")
    public Dept get(Long deptNo) {
        Dept dept = deptService.get(deptNo);
        if (dept == null) throw new RuntimeException("deptNo equal " + deptNo + " is not null");
        return dept;
    }

    /*public Dept processHandlerGet(Long deptNo){
        return new Dept()
                .setDeptNo(deptNo)
                .setDeptName("deptNo equal " + deptNo + " is not null")
                .setDbSource("no db_source in mysql");
    }*/

    /**
     * 查询所有的部门列表
     * @return 所有的部门列表
     */
    @GetMapping("/list")
    public List<Dept> list() {
        return deptService.list();
    }

    /**
     * 修改部门
     * @param dept 部门信息
     * @return 1-成功  0-失败
     */
    @PostMapping("/edit")
    public int edit(Dept dept) {
        return deptService.edit(dept);
    }

    /**
     * 删除部门
     * @param deptNo 部门编号
     * @return 1-成功  0-失败
     */
    @PostMapping("/delete")
    public int delete(Long deptNo) {
        return deptService.delete(deptNo);
    }

    /**
     * 文件上传
     * @param file 文件
     * @return 0-上传失败 1-上传成功
     */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public int upload(@RequestPart(value = "file") MultipartFile file){
        return deptService.upload(file);
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> download(HttpServletRequest request){
        return deptService.download(request);
    }

    /**
     * 获取服务提供者信息
     * @return String 服务提供者信息
     */
    @GetMapping("/getDiscovery")
    public String getDiscovery(){
        List<String> services = client.getServices();
        services.forEach(System.out::println);
        List<ServiceInstance> serviceInstanceList = client.getInstances("msc-provider");
        return serviceInstanceList.stream().map(instance-> "["
                + instance.getServiceId()
                + "\t" + instance.getHost()
                + "\t" + instance.getPort()
                + "\t" +instance.getUri()
                + "\t" + instance.getInstanceId()
                + "]"
        ).collect(Collectors.joining());
    }

}
