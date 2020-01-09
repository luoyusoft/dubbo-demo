package com.jinhaoxun.dubbo.module.article;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinhaoxun.dubbo.pojo.article.PrimaryLabel;
import com.jinhaoxun.dubbo.mapper.article.PrimaryLabelMapper;
import com.jinhaoxun.dubbo.module.article.service.PrimaryLabelService;
import org.springframework.stereotype.Component;
import org.apache.dubbo.config.annotation.Service;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 一级标签服务类
 */
@Service
@Component
public class PrimaryLabelServiceImpl extends ServiceImpl<PrimaryLabelMapper, PrimaryLabel> implements PrimaryLabelService {

}