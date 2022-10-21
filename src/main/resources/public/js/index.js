layui.use(['form','jquery','jquery_cookie'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);
    // 进行登录操作
    form.on('submit(login)', function (data) {
        //data = data.field;
        $.ajax({
            type:"post",
            url:ctx+"/admin/login",
            data:{
                userName:data.field.username,
                userPwd:data.field.password
            },
            dataType:"json",
            success:function (data) {
                if(data.code == 200){
                    layer.msg('登录成功', function () {
                        if ($("#rememberMe").prop("checked")){ //记住密码
                            $.cookie("username",data.result.username,{expires:7});
                            $.cookie("id",data.result.id,{expires:7});
                            $.cookie("name",data.result.name,{expires:7});
                        } else {
                            $.cookie("username",data.result.username);
                            $.cookie("id",data.result.id);
                            $.cookie("name",data.result.name);
                        }
                        window.location.href=ctx+"/main";
                    });
                }else{
                    layer.msg(data.msg,{icon:5});
                }
            }
        });
        return false;
    });
});