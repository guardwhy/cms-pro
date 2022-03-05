let core={
    http:function(option){
        let opt={load:true},options ={
            url:"",
            method:"post",
            contentType:"application/x-www-form-urlencoded",
            dataType:"json",
            // 发送请求之前执行
            beforeSend:function (){
                // 判断登录状态
                this.load && LayUtil.layer.init(function (inner, layer){
                    layer.load()
                })
            },
            success:function(res){
                if(res==="密码错误"){
                    console.log("用户密码错误！！！");
                }
            }
        };
        Object.assign(opt,options,option);
        $.ajax(opt);
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
            }
        }
        LayUtil.layer = new Inner();
    })(LayUtil)
}