let core={
    http:function(option){
        let opt={load:true},loadHandler,options ={
            url:"",
            method:"post",
            contentType:"application/x-www-form-urlencoded",
            dataType:"json",
            beforeSend:function(){
                this.load &&  (loadHandler = LayUtil.layer.init(function(inner,layer){
                    inner.loading({shade:1})
                }))
            },
            success:function(res){
                if(res.restCode===200){
                    loadHandler.closeLoading();
                    console.log("密码错误了!")
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
            },
            // 显示loading加载
            loading:function (config={}){
                this.layer.load(config);
            },
            closeLoading:function (){
                this.layer.closeAll('loading');
            }
        },
        LayUtil.layer = new Inner();
    })(LayUtil)
}