layui.use(['form','jquery','jquery_cookie'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);

    form.on('submit(saveBtn)',function (data){

        $.ajax({
            type:"post",
            url:ctx+"/user/updatePwd",
            data:{
                oldPassword:data.field.old_password,
                newPassword:data.field.new_password,
                repeatPassword:data.field.again_password,
            },
            success:function (result) {
                //判断是否修改成功
                if (result.code == 200) {
                    //成功后，删除cookie，跳转到登陆页面
                    layer.msg("用户密码修改成功，3秒后退出系统",function (){
                        $.removeCookie("userIdStr",{domain:"localhost",path:"/crm"});
                        $.removeCookie("userName",{domain:"localhost",path:"/crm"});
                        $.removeCookie("trueName",{domain:"localhost",path:"/crm"});

                        //父窗口跳转
                        window.parent.location.href=ctx+"/index";
                    })
                } else {
                    layer.msg(result.msg,{icon:5});
                }
            }
        })
    })
});