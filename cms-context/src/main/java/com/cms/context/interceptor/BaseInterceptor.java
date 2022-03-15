package com.cms.context.interceptor;

import com.cms.core.foundation.BaseEntity;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;

import java.time.LocalDateTime;
import java.util.Properties;

/**
 * @author guardwhy
 * @date 2022/3/14 19:01
 */
@Intercepts(@Signature(type = Executor.class, method = "update",args = {MappedStatement.class,Object.class}))
public class BaseInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        // 拿到当前执行的类型
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        // 判断执行类型
        switch (sqlCommandType){
            case INSERT:
                insert(invocation.getArgs()[1]);
                break;
            case UPDATE:
                update(invocation.getArgs()[1]);
                break;
            default:
                break;
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        // 条件判断
        if(target instanceof Executor){
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {

    }

    /***
     * 添加操作 传递实体的话可以添加
     * @param obj
     */
    private void insert(Object obj){
        if(obj instanceof BaseEntity){
            BaseEntity baseEntity = (BaseEntity) obj;
            // 设置时间
            baseEntity.setCreateTime(LocalDateTime.now());
        }
    }

    /***
     * 修改时间
     * @param obj
     */
    private void update(Object obj){
        if(obj instanceof BaseEntity){
            BaseEntity baseEntity = (BaseEntity) obj;
            baseEntity.setUpdateTime(LocalDateTime.now());
        }
    }
}
