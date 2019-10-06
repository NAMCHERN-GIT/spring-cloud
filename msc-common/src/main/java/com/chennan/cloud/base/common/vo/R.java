package com.chennan.cloud.base.common.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 页面响应的类
 * 
 * <br><br>
 * 设计原则: 规范Controller返回的JSON串.
 * <br> 1. 返回错误,必须带错误信息,可选带详细信息(默认为空字符串)
 * <br> 2. 返回成功,可选带成功信息(默认为空字符串),可选带详细信息(默认为空字符串)
 * <br>      如果为普通查询,则返回data段
 * <br>      如果为分页查询,则返回data段和page段
 * 
 * 
 * <br> 日期: 20180507
 * <br> 思考: 讨论考虑追加错误代码,同时修正此类
 * <br> 规范: 
 * <br> //标准返回数据
 * <br> {
 * <br>     //错误代码[必选],0表示成功(没有错误),-1表示未知错误,60001 --> 69999 为其他错误代码,在topcheer.message中定义
 * <br>     errcode: 0
 * <br>
 * <br>     //信息[必选,默认为空字符串]
 * <br>     errmsg: ''/...
 * <br>   
 * <br>     //详细信息[必选,默认为空字符串]
 * <br>     errdetail:''
 * <br>
 * <br>     //数据[可选],数据都在此处
 * <br>     data: [{},{},{}]
 * <br>
 * <br>     //分页信息[可选]: 当前页,每页显示条数,总页数,总记录数
 * <br>     page: {current:1, size: 20,pages: 5,total: 300}
 * <br>
 * <br>		//代码信息[可选]: 对于list方法中返回的字段,如果包含代码则添加此字段
 * <br>     code: {"status": {"0": "无效", "1": "有效"}, "sex": {"M":"男","F":"女"}}
 * <br> }
 * <br>
 * 
 * @author he_pe 2018-01-16
 */
public class R extends LinkedHashMap<String, Object> {

	private static final long serialVersionUID = 1L;
	private static final String ERR_CODE   = "errcode";
	private static final String ERR_MSG    = "errmsg";
	private static final String ERR_DETAIL = "errdetail";

	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// 私有化构造函数,提供静态的"返回错误"和"返回成功"方法
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	private R() {
		super(8); 
		//initialCapacity 默认16,用不了那么多. 根据建议为2的N次方,此处按照规范一般最多为6个,所以可设置为8. 
		//loadFactor
	} 
	public static R err(String errmsg) {
		return Objects.requireNonNull(Objects.requireNonNull(new R().put(ERR_CODE, -1))
				.put(ERR_MSG, StringUtils.isBlank(errmsg) ? "未知错误" : errmsg))
				      .put(ERR_DETAIL, "");
	}
	public static R ok() {
		return Objects.requireNonNull(Objects.requireNonNull(new R().put(ERR_CODE, 0))
				.put(ERR_MSG, ""))
			          .put(ERR_DETAIL, "");
	}

	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// 取错误代码,错误信息,错误详细信息
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public int getErrCode() {
		return (int) this.get(ERR_CODE);
	}
	
	public String getErrMsg() {
		return (String) this.get(ERR_MSG);
	}
	
	public String getErrDetail() {
		return (String) this.get(ERR_DETAIL);
	}
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// 增加错误代码,错误信息,错误详细信息,数据和枚举代码
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public R setErrCode(int errcode) {
		return this.put(ERR_CODE, errcode);
	}
	
	public R setErrMsg(String errmsg) {
		return this.put(ERR_MSG, errmsg);
	}
	
	public R setErrDetail(String errdetail) {
		return this.put(ERR_DETAIL, errdetail);
	}
	
	public <T> R  addData(T data) {
		if(data instanceof Page) {
			@SuppressWarnings("rawtypes")
			Page page = (Page) data;
			Map<String,Long> map = new LinkedHashMap<>();
			map.put("current", page.getCurrent());
			map.put("size", page.getSize());
			map.put("pages", page.getPages());
			map.put("total", page.getTotal());
			return Objects.requireNonNull(this.put("data", page.getRecords()))
			           .put("page", map);
		}else {
			return this.put("data", data);
		}
	}
	
	public R addCode(Map<String,Map<String,String>> codeMap) {
		if(codeMap == null) return this;
		return this.put("code", codeMap);
	}
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// 增加其他: 按照统一规范默认外部不应该调用这个接口,因此暂时添加过时接口作为警告提示
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	@Override
	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}

//* ******************** 以下标准已过时
//* <br> 
//* <br> //标准返回数据
//* <br> {
//* <br>     //是否成功[必选]
//* <br>     success: true/false
//* <br>
//* <br>     //信息[必选,默认为空字符串]
//* <br>     message: ''/...
//* <br>   
//* <br>     //详细信息[必选,默认为空字符串]
//* <br>     detailmsg:''
//* <br>
//* <br>     //数据[可选],数据都在此处(包括分页的)
//* <br>     data: [{},{},{}]
//* <br>
//* <br>     //分页信息[可选]: 当前页,每页显示条数,总页数,总记录数
//* <br>     page: {current:1, size: 20,pages: 5,total: 300}
//* <br>
//* <br>		//代码信息[可选]: 对于list方法中返回的字段,如果包含代码则添加此字段
//* <br>     code: {"status": {"0": "无效", "1": "有效"}, "sex": {"M":"男","F":"女"}}
//* <br> }