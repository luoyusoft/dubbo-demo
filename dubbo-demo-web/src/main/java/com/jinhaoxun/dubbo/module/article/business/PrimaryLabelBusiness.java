package com.jinhaoxun.dubbo.module.article.business;

import com.jinhaoxun.dubbo.module.article.service.PrimaryLabelService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: jinhaoxun
 * @Date: 2019/12/10 14:42
 * @Version: 1.0
 */
@Service
public class PrimaryLabelBusiness {

    @Reference
    private PrimaryLabelService primaryLabelService;

}