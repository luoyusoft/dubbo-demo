package com.jinhaoxun.dubbo.module.article.business;

import com.jinhaoxun.dubbo.module.article.service.MessageService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: jinhaoxun
 * @Date: 2019/12/10 14:42
 * @Version: 1.0
 */
@Service
public class MessageBusiness {

    @Reference
    private MessageService messageService;

}