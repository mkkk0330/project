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
        searchUrl:ctx + "/q/a",
        //自定义返回数据中name的key, 默认 name
        keyName: 'rname',
        //自定义返回数据中value的key, 默认 value
        keyVal: 'id'
    },true);

    /*formSelects.config('selectId',{
        type:"post",
        searchUrl:ctx + "",
        //自定义返回数据中name的key, 默认 name
        keyName: 'rname',
        //自定义返回数据中value的key, 默认 value
        keyVal: 'id'
    },true);*/

    /**
     * 加载下拉框
     */
    $.ajax({
        type:"get",
        url:ctx + "/user/queryAllSales",
        data:{},
        success:function (data) {
            //判断返回的data是否为空
            if (data !=null){
                //获取隐藏域中的指派人ID
                var assignManId = $("#assignManId").val();
                for (var i=0; i<data.length;i++) {
                    var opt = "";
                    if (assignManId ==data[i].id) {
                        opt = "<option value='"+data[i].id+"' selected>"+data[i].uname+"</option>";
                    } else {
                        opt = "<option value='"+data[i].id+"'>"+data[i].uname+"</option>";
                    }
                    $("#assignMan").append(opt);
                }
            }
            //重新渲染
            layui.form.render("select");
        }
    });




    form.on('submit(addOrUpdateSaleChance)',function (data) {
        var index= layer.msg("数据提交中,请稍后...", {
            icon:16,  //图表
            time:false,  //不关闭
            shade:0.8
        });

        var url = ctx+"/add/addstudent";
        //获取隐藏域中的ID并判断是否为空，为空执行添加，不为空执行修改
        var saleChanceId = $("[name='id']").val();

        if (saleChanceId != null && saleChanceId !='') {
            url = ctx+"/add/update";
        }
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