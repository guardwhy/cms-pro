package com.cms.portal.freemarker.directive;

import com.cms.service.api.CmsFriendLinkService;
import com.cms.service.dto.CmsFriendLinkDto;
import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author guardwhy
 * @date 2022/4/17 12:16
 * 内部指令方法
 */
public class CmsFriendLinkDirective implements TemplateDirectiveModel {
    // 注入友情链接业务层
    @Autowired
    private CmsFriendLinkService cmsFriendLinkService;

    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody)
            throws TemplateException, IOException {
        // 输出结果
        System.out.println(templateModels.length);
        // 查询数据
        List<CmsFriendLinkDto> list = cmsFriendLinkService.getList(new CmsFriendLinkDto());
        DefaultObjectWrapper defaultObjectWrapper = new DefaultObjectWrapper(Configuration.VERSION_2_3_23);
        // 设置命名空间变量
        environment.setVariable("result",defaultObjectWrapper.wrap(list));
        // 将环境变量写出去
        templateDirectiveBody.render(environment.getOut());
    }
}
