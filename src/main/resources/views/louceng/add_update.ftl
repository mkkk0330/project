<!DOCTYPE html>
<html>
    <head>
        <#include "../common.ftl">
    </head>
    <body class="childrenBody">
        <form class="layui-form" style="width:80%;">
            <input name="id" type="hidden" value="${(user.id)!}"/>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">楼层编号</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input userName"
                           lay-verify="required" name="name" id="userName"  value="${(user.name)!}" placeholder="请输入楼层编号">
                </div>
            </div>

            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">院系</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input userName"
                           lay-verify="required" name="introduction" id="name" value="${(user.introduction)!}" placeholder="请输入院系">
                </div>
            </div>

            <div class="magb15 layui-col-md4 layui-col-xs12">
                <label class="layui-form-label">管理者</label>
                <div class="layui-input-block">
                    <select name="admin_id"  xm-select="selectId">
                    </select>
                </div>
            </div>

            <br/>
            <div class="layui-form-item layui-row layui-col-xs12">
                <div class="layui-input-block">
                    <button class="layui-btn layui-btn-lg" lay-submit=""
                            lay-filter="addOrUpdateUser">确认
                    </button>
                    <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
                </div>
            </div>

        </form>

    <script type="text/javascript" src="${ctx}/js/louceng/add.update.js"></script>
    </body>
</html>