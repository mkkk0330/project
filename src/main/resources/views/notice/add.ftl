<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <input name="id" type="hidden" value="${(notice.id)!}"/>
    <input id="assignManId" type="hidden" value="${(saleChance.assignMan)!}"/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">通知标题</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   lay-verify="required" name="title" id="customerName"  value="${(notice.title)!}" placeholder="请输入通知标题">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">通知信息</label>
        <div class="layui-input-block">
            <textarea placeholder="请输入机会通知信息" name="text" class="layui-textarea">${(notice.text)!}</textarea>
        </div>
    </div>

    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit=""
                    lay-filter="addOrUpdateSaleChance">确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/notice/add.update.js"></script>
</body>
</html>