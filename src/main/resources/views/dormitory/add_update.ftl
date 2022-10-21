<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <input name="id" type="hidden" value="${(dormitory.id)!}"/>
    <div class="layui-form1-item layui-row layui-col-xs12">
        <label class="layui-form-label">楼号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   lay-verify="required" name="buildingId" id="customerName"  value="${(dormitory.buildingId)!}" placeholder="请输入楼号">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">宿舍门牌号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                    name="name" id="chanceSource" value="${(dormitory.name)!}" placeholder="请输入宿舍门牌号">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">宿舍床位</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                    name="type"  lay-verify="required"  value="${(dormitory.type)!}"
                   placeholder="请输入宿舍床位">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">剩余床位</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   name="available"  lay-verify="required"  value="${(dormitory.available)!}"
                   placeholder="请输入宿舍剩余床位">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">宿舍电话</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   name="telephone"  lay-verify=""  value="${(dormitory.telephone)!}"
                   placeholder="请输入宿舍电话">
        </div>
    </div>

    <#--<div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">联系电话</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   lay-verify="phone" name="linkPhone" value="${(saleChance.linkPhone)!}" id="phone" placeholder="请输入联系电话">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">概要</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                    name="overview" value="${(saleChance.overview)!}" id="phone" placeholder="请输入概要">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">成功几率(%)</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                    name="cgjl" value="${(saleChance.cgjl)!}" placeholder="请输入成功几率">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">机会描述</label>
        <div class="layui-input-block">
            <textarea placeholder="请输入机会描述信息" name="description" class="layui-textarea">${(saleChance.description)!}</textarea>
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">指派给</label>
        <div class="layui-input-block">
            <select name="assignMan" id="assignMan">
                <option value="">请选择</option>
            </select>
        </div>
    </div>-->
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
<script type="text/javascript" src="${ctx}/js/dormitory/add.update.js"></script>
</body>
</html>