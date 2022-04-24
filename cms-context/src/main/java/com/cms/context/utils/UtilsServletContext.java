package com.cms.context.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author guardwhy
 * @date 2022/4/20 11:13
 * 获取项目路径
 */
@Component
public class UtilsServletContext implements ServletContextAware {
    // 导入环境对象
    private ServletContext servletContext;

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    /**
     * @param path 传入的路径
     * @return 容器路径
     */
    public String getRealPath(String path) {
        return servletContext.getRealPath(StringUtils.isBlank(path) ? "/" : path);
    }

    /***
     * 获取模板相对位置  返回相对于/webapp/WEB-INF/后的路径
     * @param tplDirName    模板目录名称
     * @param tplPrefix     模板前缀名称
     * @return              List
     */
    public List<String> getTplRelativePath(String tplDirName, String tplPrefix){
        // 完整路径
        String dirPath = "/WEB-INF/front/default/" + tplDirName;
        // 将路径转换成文件
        File file = new File(getRealPath(dirPath));
        // 再次定义一个目录
        File webInfoFile = new File(getRealPath("/WEB-INF"));
        // 文件集合
        Collection<File> fileList = FileUtils.listFiles(file, FileFilterUtils.prefixFileFilter(tplPrefix), null);
        // 过滤文件
        return fileList.stream().map(x->StringUtils.substring(StringUtils.replace(x.getAbsolutePath(),
                File.separator,"/"),webInfoFile.getAbsolutePath().length())
        ).collect(Collectors.toList());
    }

    /***
     * 获取项目的虚拟路径
     * @return
     */
    public String getContextPath(){
        return servletContext.getContextPath();
    }
}
