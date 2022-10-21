<!DOCTYPE html>
<html>
    <head>
        <title>楼层管理</title>
        <#include "../common.ftl">
    </head>
    <body class="childrenBody">
        <form class="layui-form" >
            <blockquote class="layui-elem-quote quoteBox">
                <form class="layui-form">
                    <div class="layui-inline">
                        <div class="layui-input-inline">
                            <input type="text" name="name" class="layui-input searchVal" placeholder="楼层编号 " />
                        </div>
                        <a class="layui-btn search_btn" data-type="reload" id="btnSearch">
                            <i class="layui-icon">&#xe615;</i>
                            搜索
                        </a>
                    </div>
                </form>
            </blockquote>

            <#--数据表格-->
            <table id="loucengList" class="layui-table"  lay-filter="louceng"></table>

            <#--头部工具栏-->
            <script type="text/html" id="toolbarDemo">
                <div class="layui-btn-container">
                    <a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
                        <i class="layui-icon">&#xe608;</i>
                        添加楼层
                    </a>
                    <a class="layui-btn layui-btn-normal delNews_btn" lay-event="del">
                        <i class="layui-icon">&#xe608;</i>
                        删除楼层
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

    <script type="text/javascript" src="${ctx}/js/louceng/louceng.js"></script>
    </body>
</html>