layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;


    /**
     *关闭弹出层
     *
     */
    $("#closeBtn").click(function () { // 当你在iframe页面关闭自身时
        var index = parent.layer.getFrameIndex(window.name);// 先得到当前iframe层的索引
        parent.layer.close(index); // 再执行关闭
        });


    form.on('submit(addOrUpdateSaleChance)',function (data) {
        var index= layer.msg("数据提交中,请稍后...", {
            icon:16,  //图表
            time:false,  //不关闭
            shade:0.8
        });

        var url = ctx+"/notice/add";
        //获取隐藏域中的ID并判断是否为空，为空执行添加，不为空执行修改
        var saleChanceId = $("[name='id']").val();

        /*if (saleChanceId != null && saleChanceId !='') {
            url = ctx+"/repair/update";
        }*/

        $.post(url,data.field,function (result) {
            if(result.code==200){
                //成功
                layer.msg("操作成功",{icon:6});
                //关闭加载层
                layer.close(index);
                //关闭弹出层
                layer.closeAll("iframe");
                // 刷新父页面
                parent.location.reload();
            }else{
                layer.msg(result.msg,{icon:5});
            }
        });
        return false;
    });


});