layui.use(['form', 'layer','formSelects'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;
    formSelects = layui.formSelects;

    /**
     *关闭弹出层
     *
     */
    $("#closeBtn").click(function () { // 当你在iframe页面关闭自身时
        var index = parent.layer.getFrameIndex(window.name);// 先得到当前iframe层的索引
        parent.layer.close(index); // 再执行关闭
    });
    /**
     * 加载下拉框角色数据
     */
    formSelects.config('selectId',{
        type:"post",
        searchUrl:ctx + "/louceng/queryAllAdmin",
        //自定义返回数据中name的key, 默认 name
        keyName: 'rname',
        //自定义返回数据中value的key, 默认 value
        keyVal: 'id'
    },true);

    /**
     * 监听表单的提交
     */
    form.on("submit(addOrUpdateUser)",function (data){
        // 提交数据时的加载层 （https://layer.layui.com/）
        var index = layer.msg("数据提交中,请稍后...",{
            icon:16, // 图标
            time:false, // 不关闭
            shade:0.8 // 设置遮罩的透明度
        });

        var url = ctx+"/louceng/save";
        var id = $('[name="id"]').val();
        //如果进入判断则是修改
        if(id){
            url = ctx+"/louceng/update";
        }

        //发送请求
        $.post(url,data.field,function (data){
            if(data.code == 200){
                //关闭弹出框
                layer.close(index);
                //关闭iframe层
                layer.closeAll("iframe");
                //刷新父页面，将添加的新数据展示
                parent.location.reload();
            }else{
                layer.msg(data.msg,{icon:5})
            }
        });

        return false;
    });
});