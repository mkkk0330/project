<!DOCTYPE html>
<html>
    <head>
        <title>宿舍管理员</title>
        <#include "../common.ftl">
    </head>
    <body class="childrenBody">
        <form class="layui-form" >
            <blockquote class="layui-elem-quote quoteBox">
                <form class="layui-form">
                    <div class="layui-inline">
                        <div class="layui-input-inline">
                            <input type="text" name="userName" class="layui-input searchVal" placeholder="真实名字 " />
                        </div>
                        <a class="layui-btn search_btn" data-type="reload" id="btnSearch">
                            <i class="layui-icon">&#xe615;</i>
                            搜索
                        </a>
                    </div>
                </form>
            </blockquote>

            <#--数据表格-->
            <table id="houseList" class="layui-table"  lay-filter="house"></table>

            <#--头部工具栏-->
            <script type="text/html" id="toolbarDemo">
                <div class="layui-btn-container">
                    <a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
                        <i class="layui-icon">&#xe608;</i>
                        添加用户
                    </a>
                    <a class="layui-btn layui-btn-normal delNews_btn" lay-event="del">
                        <i class="layui-icon">&#xe608;</i>
                        删除用户
                    </a>
                </div>
            </script>

            <!--操作-->
            <#--行工具栏-->
            <script id="userListBar" type="text/html">
                <a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
                <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
            </script>
        </form>

    <script type="text/javascript" src="${ctx}/js/house_parent/house_parent.js"></script>
    </body>
</html>