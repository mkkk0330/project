<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <#--设置营销机会ID的隐藏域-->
    <input type="hidden" name="id"  value="${(student.id)!}">
<#--    &lt;#&ndash;设置指派人的隐藏域ID&ndash;&gt;-->
<#--    <input type="hidden" name="man"  value="${(saleChance.assignMan)!}">-->

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">序号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   name="number" id="number" placeholder="请输入学生序号" value="${(student.number)!}">
        </div>
    </div>

<#--    <div class="layui-form-item layui-row layui-col-xs12">-->
<#--        <label class="layui-form-label">序号</label>-->
<#--        <div class="layui-input-block">-->
<#--            <input type="text" class="layui-input"-->
<#--                   lay-verify="required" name="number" id="number"  value=""  placeholder="请输入学生序号">-->
<#--        </div>-->

        <div class="layui-form-item layui-row layui-col-xs12">

            <label class="layui-form-label">学生编号</label>

            <div class="layui-input-block">
            <input type="text" class="layui-input"
                   lay-verify="required" name="id" id="id"  value="${(student.id)!}"  placeholder="请输入学生序号">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">学生名称</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   lay-verify="required" name="name" id="name"  value="${(student.name)!}"  placeholder="请输入学生名称">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">学生性别</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   name="gender" id="gender" placeholder="请输入学生性别" value="${(student.gender)!}">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">宿舍楼层</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   name="dormitoryId"  lay-verify="dormitoryId"
                   placeholder="请输入宿舍楼层" value="${(student.dormitoryId)!}">
        </div>
    </div>

    <#--<div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">入住信息</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   lay-verify="state" name="state" id="state" placeholder="入住信息" value="${(student.state)!}">
        </div>
    </div>-->
<#--    <div class="layui-form-item layui-row layui-col-xs12">-->
<#--        <label class="layui-form-label">入住时间</label>-->
<#--        <div class="layui-input-block">-->
<#--            <input type="text" class="layui-input"-->
<#--                   name="createDate"  id="createDate" placeholder="请输入概要" value="">-->
<#--        </div>-->
<#--    </div>-->
<#--    <div class="layui-form-item layui-row layui-col-xs12">-->
<#--        <label class="layui-form-label">干</label>-->
<#--        <div class="layui-input-block">-->
<#--            <input type="text" class="layui-input"-->
<#--                   name="cgjl" placeholder="请输入成功几率" value="${(saleChance.cgjl)!}">-->
<#--        </div>-->
<#--    </div>-->
<#--    <div class="layui-form-item layui-row layui-col-xs12">-->
<#--        <label class="layui-form-label">机会描述</label>-->
<#--        <div class="layui-input-block">-->
<#--            <textarea placeholder="请输入机会描述信息" name="description" class="layui-textarea">${(saleChance.description)!}</textarea>-->
<#--        </div>-->
<#--    </div>-->
<#--    <div class="layui-form-item layui-row layui-col-xs12">-->
<#--        <label class="layui-form-label">指派给</label>-->
<#--        <div class="layui-input-block">-->
<#--            <select name="assignMan" id="assignMan">-->
<#--                <option value="" >请选择</option>-->
<#--            </select>-->
<#--        </div>-->
<#--    </div>-->
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
<script type="text/javascript" src="${ctx}/js/noticeStu/add.update.js"></script>
</body>
</html>