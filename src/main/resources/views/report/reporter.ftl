<#--&lt;#&ndash;
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>第一个 ECharts 实例</title>
    <!-- 引入 echarts.js &ndash;&gt;
    <script src="https://cdn.staticfile.org/echarts/4.3.0/echarts.min.js"></script>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom &ndash;&gt;
<div id="main" style="width: 600px;height:400px;"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    &lt;#&ndash;var myChart = echarts.init(document.getElementById('main'));&ndash;&gt;
</script>
</body>


</html>&ndash;&gt;-->
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>指定时间节点</title>
    <#include "../common.ftl">
    <script src="https://cdn.staticfile.org/jquery/2.2.4/jquery.min.js"></script>
    <!-- 引入 echarts.js -->
    <script src="https://cdn.staticfile.org/echarts/4.3.0/echarts.min.js"></script>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>
<script type="text/javascript" src="${ctx}/js/pmanage/reportDate2.js">
</script>
</body>