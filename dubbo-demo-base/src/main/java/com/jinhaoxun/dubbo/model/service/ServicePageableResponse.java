package com.jinhaoxun.dubbo.model.service;

import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * @description: 分页查询响应模型
 * @program: session1
 * @author: jinhaoxun
 * @create: 2019-03-11 18:36
 **/
@Setter
@Getter
public class ServicePageableResponse extends ServiceResponse {

    /** 总记录数 */
    private int totals;

}
