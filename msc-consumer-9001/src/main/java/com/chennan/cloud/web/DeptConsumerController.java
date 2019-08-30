package com.chennan.cloud.web;

import com.chennan.cloud.bo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 消费端web层。
 * @author chen.nan
 */
@RequestMapping("/dept")
@RestController
public class DeptConsumerController {

    // private static final String REST_URL_PREFIX = "http://localhost:9100";
    /**
     * 使用微服务名称进行微服务调用，不用再关心地址和端口
     */
    private static final String REST_URL_PREFIX = "http://msc-provider";

    /**
     * 使用 RestTemplate访问restful接口非常的简单粗暴无脑。（url，requestMap，ResponseBean.class），
     * 这三个参数分别代表REST请求地址，请求参数，HTTP响应转换被转换成的对象类型。
     */
    @Autowired RestTemplate restTemplate;

    @PostMapping("/add")
    public Integer add(Dept dept){
        return restTemplate.postForObject(REST_URL_PREFIX + "/dept/add", dept, Integer.class);
    }

    @GetMapping("/get")
    public Dept get(Long deptNo){
        Map<String,Object> parms = new LinkedHashMap<>();
        parms.put("deptNo", deptNo);
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/get?deptNo={deptNo}", Dept.class, parms);
    }

    @GetMapping("/list")
    public List list(){
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/list" , List.class);
    }

    @PostMapping("/edit")
    public Integer edit(Dept dept){
        return restTemplate.postForObject(REST_URL_PREFIX + "/dept/edit" , dept, Integer.class);
    }

    @PostMapping("/delete")
    public Integer delete(Long deptNo){
        Map<String,Object> parms = new LinkedHashMap<>();
        parms.put("deptNo", deptNo);
        return restTemplate.postForObject(REST_URL_PREFIX + "/dept/delete", parms, Integer.class);
    }

    @GetMapping("/discovery")
    public String discovery(){
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/getDiscovery", String.class);
    }

}
