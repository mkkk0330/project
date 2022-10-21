layui.use(['table','layer','echarts'],function() {
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table,
        echarts = layui.echarts
    ;
    var myecharts = echarts.init(document.getElementById('main'));
    myecharts.clear();
    var names = [];
    $.ajax({
        method: 'get',
        async: true,
        url: ctx + "/reward/reportDate",
        dataType: 'json',
        success: function (data) {
            console.log(data)
            for (var i = 0; i < data.length; i++) {
                names.push(data[i]);
            }
            // 指定图表的配置项和数据
            var option = {
                xAxis: {
                    type: 'category',
                    data: [101,102,211,212,321,322,666]

                },
                yAxis: {
                    type: 'value'
                },
                series: [
                    {
                        data:[4,3,6,4,3,8,6],
                        type: 'bar',
                        showBackground: true,
                        backgroundStyle: {
                            color: 'rgba(180, 180, 180, 0.2)'
                        }
                    }
                ]
            };
            // 使用刚指定的配置项和数据显示图表。
            myecharts.setOption(option, true);
        }
    });
});