let core={
    //字符串操作
    String:{
        //判断字符串是否为空
        isEmpty:function(content){
            if(content===undefined){
                return true;
            }
            if($.trim(content).length===0){
                return true;
            }
            return false;
        }
    },
    // 设置限流工具类
    throttle:function (method, args, context){
        // 清空tId
        clearTimeout(method.tId);
        // 设置秒值
        method.tId =  setTimeout(function (){
            method.call(context, args);
        }, 200)
    },

    /**
     * http请求方法
     */
    http:function(option, callback){
        this.cancel && this.cancel.abort();
        //load: 加载loading autoComplete：自动完成, goBack自动回退
        let opt={load:true, autocomplete: true, goBack:true},loadHandler,loadTime,
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
                    let that = this, handler;
                    // 延时设置
                    setTimeout(function () {
                        // 判断请求接口
                        switch(res.restCode){
                            case CONSTANT.HTTP.ERROR:
                                core.prompt.alert(res.restInfo);
                                break;
                            case CONSTANT.HTTP.SUCCESS:
                                if(that.autocomplete){
                                    // 执行del操作时候，无需回跳
                                    if(that.goBack){
                                        handler=function(){
                                            // 回退刷新
                                            window.location.href = document.referrer;
                                        }
                                    }
                                    core.prompt.msg(res.restInfo,{shade:0.3, time: 1200}, handler);
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
    /**
     * 提示相关
     */
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
        },
        // 询问
        confirm:function (content, option, callback){
            LayUtil.layer.init(function (inner){
                LayUtil.layer.init(function (inner){
                    inner.confirm(content, option, callback);
                })
            })
        }
    },
    //业务相关
    business:{
        //删除
        delete:function(data,callback){
            let config = {url:"delete.do",goBack:false, data:{id:data.id}};
            core.prompt.confirm("确认执行该操作?",{icon:3,title:'提示'},function(){
                core.http(config,callback);
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

//树形表格属性
LayUtil.treeTableOption={
    treeColIndex: 1,
    treeSpid: 0,
    treeIdName: 'authorityId',
    treePidName: 'parentId',
    elem: '#treeTable',
    page: false
}

// 下拉树选项
LayUtil.selectTreeOption = {
    elem: "#selectTree",
    url: "${adminPath}/permission/selectTree.do",
    dataType: "json",
    async: false,
    method: 'post',
    ficon: ["1", "-1"],
    dataStyle: "layuiStyle",
    initLevel: 1,
    width: "100%",
    dot: false,
    checkbar: false,
    formatter: {
        title: function (data) {
            let s = data.name;
            if (data.children) {
                s += ' <span style=\'color:blue\'>(' + data.children.length + ')</span>';
            }
            return s;
        }
    },
    response: {
        statusCode: 200,
        statusName: "restCode",
        treeId: "id",
        message: "restCode",
        rootName: "data",
        title: 'name'
    },
};

//layui的表格固定配置
LayUtil.dataGridOption = {
    id: "dataGrid",
    elem: '#dataGrid',
    method: 'post',
    page: true,
    url:'',
    limit: 10,
    headSearch:"searchSubmit",
    request: {
        pageName: 'pageCurrent', //页码的参数名称，默认：page
        limitName: 'pageSize' //每页数据量的参数名，默认：limit
    },
    response: {
        statusName: 'restCode', //数据状态的字段名称，默认：code
        statusCode: 200, //成功的状态码，默认：0
        msgName: 'restCode', //状态信息的字段名称，默认：msg
        countName: 'pageCount', //数据总数的字段名称，默认：count
        rootName: 'data'
    },
    parseData: function (res) {
        // 下面字符串是对上面字符串的解释
        return {
            "restCode": res.restCode,
            "pageCount": res.data ? res.data.pageCount : undefined,
            "data": res.data ? res.data.content : undefined
        }
    }
};

//树形配置
LayUtil.treeOption = {
    elem: "#tree",
    url: "",
    dataType: "json",
    async: false,
    method: 'get',
    dataStyle: "layuiStyle",
    initLevel: 1,
    data: "",
    dot: false,
    checkbar: false,
    contentType:'application/json;charset=UTF-8',
    nodeIconArray: {"1": {"open": "dtree-icon-wenjianjiazhankai", "close": "dtree-icon-weibiaoti5"}},
    icon: ["1", "7"],
    formatter: {
        title: function (data) {
            let s = data.name;
            if (data.children) {
                s += ' <span style=\'color:blue\'>(' + data.children.length + ')</span>';
            }
            return s;
        }
    },
    response: {
        statusCode: 200,
        statusName: "restCode",
        treeId: "id",
        message: "restCode",
        rootName: "data",
        title: 'name'
    },
};


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
                // console.log(layer.msg(content, option, callback));
                return layer.msg(content, option, callback);
            },
            // 询问框: 内容，配置，回调函数
            confirm:function (content, option, callback){
                let that = this;
                this.layer.confirm(content,option,function (index){
                    that.layer.close(index);
                    // 条件判断
                    if(callback instanceof Function){
                        // 调用回调函数
                        callback();
                    }
                })
            }
        }
        LayUtil.layer = new Inner();
    })(LayUtil),
    // from表单
    form:(function(LayUtil){
        function Inner(){
        }
        Inner.prototype={
            construct:Inner,
            // 自动化封装
            init:function(callback){
                let that =this;
                layui.use('form',function(){
                    that.form = layui.form;
                    that.form.render();
                    if(callback instanceof Function){
                        //自动提交 如果返回undefined
                        let autoOperation = callback(that,that.form);
                        if(autoOperation === undefined){
                            (OPERATION_URL!==undefined && !core.String.isEmpty(OPERATION_URL)) && that.submit(function(data){
                                core.http({url:OPERATION_URL,data:data.field});
                            })
                        }
                    }
                });
                return this;
            },
            // 提交表单,事件监听
            submit:function(callback,name,type="submit"){
                this.form.on(type+"("+(name===undefined?'go':name)+")",function(obj){
                    if(callback instanceof Function){
                        //自动提交 如果返回undefined
                        let data = callback(obj);
                        if(data !== undefined){
                            (OPERATION_URL!==undefined && !core.String.isEmpty(OPERATION_URL)) &&
                            core.http({url:OPERATION_URL,data:data});
                        }
                        return false;
                    }
                    return true;
                })
            },
            // 自定义验证
            verify:function (validator){
                this.form.verify(validator);
            },
            // radio 事件监听
            radio:function (name, callback){
                this.submit(callback, name, "radio");
            }
        }
        // 绑定到静态方法
        LayUtil.form = new Inner();
    })(LayUtil),

    //树形表格
    treeTable:(function(LayUtil){
        function Inner(){}
        Inner.prototype={
            construct:Inner,
            init:function(config,callback){
                // 直接覆盖
                let that = this, option = $.extend({},LayUtil.treeTableOption,config);
                // 插件引入
                layui.extend({
                    treetable:'{/}'+ BASE_PATH +'/admin/layui/lay/modules/treetable'
                    // treetable引入进来
                }).use(['treetable','table'],function(){
                    that.treetable = layui.treetable;
                    // 开始渲染
                    that.treetable.render(option);
                    // 拿到table
                    that.table = layui.table;
                    // 调用工具栏右侧进行监听
                    that.rightTool(function(obj){
                        // 拿到表单数据进行判断
                        if(obj.event!==undefined && obj.event==="del"){
                            that.delete(obj.data,$.extend({},LayUtil.treeTableOption,config))
                        }
                    });
                    (callback instanceof Function) && callback(that,that.treetable,that.table);
                });
                return this;
            },
            //右侧工具栏
            rightTool:function(callback,filter='treeTable'){
                // 监听右侧
                this.table.on('tool('+filter+')',function(obj){
                    // 执行回调
                    (callback instanceof Function) && callback(obj)
                });
            },
            //表格单条删除操作
            delete:function(data,option){
                let that = this;
                // 调用ajax,重新加载
                core.business.delete(data,function(){
                    // 重新加载option参数
                    that.treetable.render(option);
                });
            }
        };
        LayUtil.treeTable = new Inner();
    })(LayUtil),
    //数据表格
    dataGrid:(function(LayUtil){
        function Inner(){}
        Inner.prototype={
            construct:Inner,
            init:function(option,callback){
                let config = $.extend({},LayUtil.dataGridOption,option),that = this;
                layui.use('table',function(){
                    that.table = layui.table;
                    that.table.render(config);
                    // 渲染
                    that.renderSearch(config.headSearch);
                })
            },
            //渲染form表头查询
            renderSearch:function (name){
                // layui使用
                layui.use('form', function (){
                    // 拿到form表格
                    var form = layui.form,that = this;
                    // 监听提交
                    form.on('submit('+name+')', function (data){
                        // 重新加载数据表格
                       that.table.reload('dataGrid',{
                           where:data.field,
                           page:{
                               curr:1
                           }
                       });
                       return false;
                    })
                })
            }
        };
        LayUtil.dataGrid = new Inner();
    })(LayUtil),
    // 下拉树形
    selectTree:(function (LayUtil){
        function Inner(){}
        Inner.prototype={
            construct:Inner,
            init:function(config,callback){
                // 直接覆盖
                let that = this,option = $.extend({},LayUtil.selectTreeOption,config);
                // {/}的意思即代表采用自有路径，即不跟随 base 路径 你还得把第三方js改成layui.denfine()那种格式
                // 插件引入
                layui.extend({
                    dtree:'{/}' + BASE_PATH + "/admin/layui/lay/modules/selectTree"
                }).use('dtree',function(){
                    that.dtree = layui.dtree;
                    // 开始渲染
                    that.dtree.renderSelect(option);
                    (callback instanceof Function) && callback(that,that.dtree);
                });
                return this;
            }
        };
        // 绑定静态方法
        LayUtil.selectTree = new Inner();
    })(LayUtil),
    // 树形结构
    tree:(function (LayUtil){
        function Inner(){}
        Inner.prototype={
            construct:Inner,
            init:function(config){
                // 获取到作用域，合并属性
                let that = this,option = $.extend({},LayUtil.treeOption,config);
                this.id = option.elem;
                // 复选框的配置
                if (option.checkbar!==undefined && option.checkbar) {
                    // 自定扩展的二级非最后一级图标，从1开始
                    option.nodeIconArray = {
                        "1": {
                            "open": "dtree-icon-wenjianjiazhankai",
                            "close": "dtree-icon-weibiaoti5"
                        }
                    };
                    option.icon = ["1", "8"];
                }
                // 插件引入
                layui.extend({
                    dtree: '{/}' + BASE_PATH + '/admin/layui/lay/modules/dtree'
                }).use('dtree',function(){
                    that.dtree = layui.dtree;
                    // 开始渲染
                    that.dtree.render(option);
                });
                return this;
            },
            // 获取选中的
            getChecked:function (obj,name){
                // 显示结果
                let arr = this.dtree.getCheckbarNodesParam(this.id.replace("#", ""));
                if(arr instanceof Array){
                    // 判断obj是否为数组，然后在进行遍历
                    if(obj!== undefined && name!== undefined){
                        for(let i=0,length=arr.length; i<length; i++){
                            obj[name+ "[" +i+ "]" ] = arr[i].nodeId;
                        }
                        return obj;
                    }
                }
                console.log(arr);
            }
        };
        // 绑定静态方法
        LayUtil.tree = new Inner();
    })(LayUtil)
}