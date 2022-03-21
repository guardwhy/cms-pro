let core={
    // 设置限流工具类
    throttle:function (method, args, context){
        // 清空tId
        clearTimeout(method.tId);
        // 设置秒值
        method.tId =  setTimeout(function (){
            method.call(context, args);
        }, 200)
    },
    http:function(option, callback){
        this.cancel && this.cancel.abort();
        //load: 加载loading autoComplete：自动完成
        let opt={load:true, autocomplete: true},loadHandler,loadTime,
            options ={
                url:"",
                method:"post",
                contentType:"application/x-www-form-urlencoded",
                dataType:"json",
                beforeSend:function(){
                    // 发送请求的当前时间
                    this.load &&  ((loadTime = new Date().getTime()) && (loadHandler = LayUtil.layer.init(function(inner,layer){
                        // 遮罩
                        inner.loading(0,{shade:0.1})
                    })))
                },
                // 自定义操作
                success:function(res){
                    // 处理Loading加载
                    if(this.load && loadHandler){
                        let time = 0;
                        // ajax执行的时间
                        if(new Date().getTime()-loadTime<500){
                            time = 500;
                        }
                        // 定时任务
                        setTimeout(function(){
                            loadHandler.closeLoading();
                        }, time)
                    }
                    // autoComplete本身
                    let that = this;
                    // 延时设置
                    setTimeout(function () {
                        // 判断请求接口
                        switch(res.restCode){
                            case CONSTANT.HTTP.ERROR:
                                core.prompt.alert(res.restInfo);
                                break;
                            case CONSTANT.HTTP.SUCCESS:
                                if(that.autocomplete){
                                    core.prompt.msg(res.restInfo,{shade:0.3, time: 1200});
                                }
                                break;
                        }
                        // 处理自定义的问题
                        (callback instanceof Function) && callback(res);

                    }, 600)
                }
            };
        Object.assign(opt,options,option);
        this.cancel = $.ajax(opt);
    },
    prompt:{
        // 警告弹窗
        alert:function (content, opt){
            core.prompt.msg(content, $.extend({},{
                icon:5,
                shift:6,
                shade:0.3,
                time:1500,
                area:'auto',
                shadeClose:true
            },opt));
        },
        // 信息框提示
        msg:function (content, option, callback){
            LayUtil.layer.init(function (inner){
                inner.msg(content, option, callback)
            })
        }
    }
};

// 定义常量
const CONSTANT = {
    // http相关
    HTTP:{
        SUCCESS:200,
        ERROR:500
    }
};

// layui工具类
function LayUtil(){

}

LayUtil.prototype = {
    construct:LayUtil,
    // 弹窗
    layer:(function (LayUtil){
        function Inner(){
        }

        Inner.prototype={
            construct:Inner,
            // 项目初始化自动封装
            init:function (callback){
                let that = this;
                layui.use('layer', function (){
                    that.layer = layui.layer;
                    if(callback instanceof Function){
                        callback(that,that.layer);
                    }
                })
                return this;
            },
            // 显示loading加载
            loading:function (config={}){
                this.layer.load(config);
            },
            // 关闭loading
            closeLoading:function (){
                this.layer.closeAll('loading');
            },
            // 登录成功后响应
            msg:function (content, option, callback){
                console.log(layer.msg(content, option, callback));
            }
        }
        LayUtil.layer = new Inner();
    })(LayUtil),
    // from表单
    form:(function (LayUtil){
        function Inner(){

        }
        Inner.prototype = {
            construct: Inner,
            // 项目初始化自动封装
            init: function (callback) {
                let that = this;
                layui.use('form', function () {
                    that.form = layui.form;
                    // 自动化完成渲染
                    that.form.render();
                    if (callback instanceof Function) {
                        callback(that, that.form);
                    }
                });
                return this;
            },
            // 提交表单
            submit:function (callback, name, type="submit"){
                this.form.on(type+"("+(name === undefined? 'go':name) + ")", function (obj){
                    if(callback instanceof Function){
                        callback(obj);
                        return false;
                    }
                    return true;
                })
            },
            // 自定义验证
            verify:function (validator){
                this.form.verify(validator);
            }
        }
        // 绑定到静态方法
        LayUtil.form = new Inner();
    })(LayUtil)
}