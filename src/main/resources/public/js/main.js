layui.use(['element', 'layer', 'layuimini','jquery','jquery_cookie'], function () {
    var $ = layui.jquery,
        layer = layui.layer,
        $ = layui.jquery_cookie($);

    // 菜单初始化
    $('#layuiminiHomeTabIframe').html('<iframe width="100%" height="100%" frameborder="0"  src="welcome"></iframe>')
    layuimini.initTab();

    $(".login-out").click(function (){
        layer.confirm("确认退出系统吗?",{icon:3,title:"系统提示"},function (index){
            layer.close(index);
            //删除cookie
            $.removeCookie("id",{domain:"localhost",path:"/dor"});
            $.removeCookie("username",{domain:"localhost",path:"/dor"});
            $.removeCookie("name",{domain:"localhost",path:"/dor"});


            window.parent.location.href=ctx+"/index";
        })
    })
});