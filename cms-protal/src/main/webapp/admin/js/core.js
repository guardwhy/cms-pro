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
    http:function(option){
        // 输出相关结果
        console.log(this.cancel);
        this.cancel && this.cancel.abort();
        let opt={load:true},loadHandler,options ={
            url:"",
            method:"post",
            contentType:"application/x-www-form-urlencoded",
            dataType:"json",
            beforeSend:function(){
                this.load &&  (loadHandler = LayUtil.layer.init(function(inner,layer){
                    // 遮罩
                    inner.loading(0, {shade:0.1})
                }))
            },
            success:function(res){
                if(res.restCode===CONSTANT.HTTP.SUCCESS){
                    loadHandler.closeLoading();
                }
            }

        };
        Object.assign(opt,options,option);
        this.cancel = $.ajax(opt);
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
    this.run = function (){

    }
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
            closeLoading:function (){
                this.layer.closeAll('loading');
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