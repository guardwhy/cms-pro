<#--通用form模板-->
<#macro form>
    <form class="layui-form" method="post">
        <#nested>
    </form>
</#macro>

<#--通用form中的每一项 一行item-->
<#macro item class="">
    <div class="layui-form-item ${class}">
        <#nested>
    </div>
</#macro>

<#--    item中左右两边的inline-->
<#macro inline required=false full=false label="">
    <div class="layui-inline ${full?string("cms-inline-100","cms-inline-50")}">
        <label class="layui-form-label layui-col-md6 <#if required>cms-label-required</#if>" style="width:197px;">${label}</label>
        <div class="layui-input-block layui-col-md6 cms-inline-block">
            <#nested>
        </div>
    </div>
</#macro>

<#--提交按钮-->
<#macro submit>
    <div class="layui-form-item">
        <div class="layui-inline cms-inline-50">
            <label class="layui-form-label layui-col-md6" style="width:197px;"></label>
            <div class="layui-input-block layui-col-md6 cms-inline-block">

            </div>
        </div>
        <div class="layui-inline cms-inline-50">
            <label class="layui-form-label layui-col-md6" style="width:197px;"></label>
            <div class="layui-input-block layui-col-md6 cms-inline-block">
                <p class="cms-flex-end cms-pt50">
                    <button class="layui-btn layui-btn-sm layui-btn-primary">重置</button>
                    <button class="layui-btn layui-btn-sm layui-btn-normal" type="button" lay-submit lay-filter="go">提交</button>
                </p>
            </div>
        </div>
    </div>
</#macro>

<#--单选框    -->
<#macro radio list name value="" itemLabel="" itemValue=""  filter="" enum=false>
    <#if list?is_sequence>
        <#if enum==false>
            <#if itemLabel!="" && itemValue!="">
                <#list list as item>
                    <input type="radio" name="${name}" title="${item[itemLabel]}" lay-filter="${filter}" <#if value=="${item[itemValue]}">checked</#if> value="${item[itemValue]}">
                </#list>
            <#else>

            </#if>
        <#else>
            <#list list as item>
                <input type="radio" name="${name}" title="${item.label}" lay-filter="${filter}" <#if value=="${item.getOrdinal()}">checked</#if> value="${item.getOrdinal()}">
            </#list>
        </#if>
    <#else>

    </#if>
</#macro>

<#--封装表头查询-->
<#macro headSearch>
    <#nested>
    <button class="layui-btn" lay-submit lay-filter="searchSubmit">查询</button>
</#macro>
