layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;


    //学生信息列表展示
    //加载数据表格
   var tableIns=table.render({
       id:'saleChanceTable'
        //容器元素的ID属性值
        ,elem: '#saleChanceList',
        //访问数据的URL（后台的数据接口）
        url : ctx+'/student/list',
        //单元格最小的宽度
        cellMinWidth : 95,
        //开启分页
        page : true,
        //容器的高度 full-差值
        height : "full-125",
        //每页页数的可选项
        limits : [10,20,30,40,50],
        //默认每页显示的数量
        limit : 10,
        // 开启头部工具栏
        toolbar: "#toolbarDemo",
        //表头
        cols : [[
            //field:要求field属性值与返回的数据中对应的属性字段名一致
            //tiele:设置列的标题
            //sort:是否允许排序（默认:false）
            //fixed:固定列
            {type: 'checkbox', fixed:'center'},
            {field: 'number', title:'序号',sort : true,fixed:'left'},
            {field: 'id', title: '学生编号',align:'center'},
            {field: 'name', title: '学生名称',  align:'center'},
            {field: 'gender', title: '学生性别', align:'center'},
            {field: 'dormitoryId', title: '宿舍楼层', align:'center'},
            //{field: 'state', title: '入住信息',  align:'center'},
            {field: 'createDate', title: '入住时间', align:'center'},
            // {field: 'state', title: '分配状态', align:'center',templet:function(d){
            //        //调用函数，返回格式化的结果
            //         return formatterState(d.state);
            //     }},
            // {field: 'devResult', title: '开发状态', align:'center',templet:function (d) {
            //         //调用函数，返回格式化的结果
            //     return formatterDevResult(d.devResult);
            //     }},
            {title: '操作', templet:'#saleChanceListBar',fixed:"right",align:"center", minWidth:150}
        ]]
    });

    //
    // //格式化分配状态
    // //0=未分配
    // //1=已分配
    // //其他=未知
    // function formatterState(state){
    //     if(state==0){
    //         return "<div style='color:yellow '>未分配</div>";
    //     }else if(state==1){
    //         return "<div style='color: green'>已分配</div>";
    //     }else{
    //         return "<div style='color: red'>未知</div>";
    //     }
    // }
    //
    // function formatterDevResult(value){
    //     /**
    //      * 0-未开发
    //      * 1-开发中
    //      * 2-开发成功
    //      * 3-开发失败
    //      */
    //     if(value==0){
    //         return "<div style='color: yellow'>未开发</div>";
    //     }else if(value==1){
    //         return "<div style='color: #00FF00;'>开发中</div>";
    //     }else if(value==2){
    //         return "<div style='color: #00B83F'>开发成功</div>";
    //     }else if(value==3){
    //         return "<div style='color: red'>开发失败</div>";
    //     }else {
    //         return "<div style='color: #af0000'>未知</div>"
    //     }
    // }



    // 多条件查询
    $(".search_btn").click (function () {
        tableIns.reload({
            //设置需要传递给后端的参数
            where:{
                name:$("[name='name']").val(),// 学生名称
                dormitoryId:$("[name='dormitoryId']").val(),// 楼层信息
                state:$("#state").val()    //入住状态
            }
            ,page:{
                curr:1
            }
        });
    });


    // 行工具栏监听事件
    table.on('tool(saleChances)',function (data) {
            console.log(data);

        //判断类型
        if (data.event=="edit"){//编辑操作
            var id = data.data.id;
            //打开修改营销机会数据的窗口
            openAddOrUpdateSaleChanceDialog(id);
        }else if (data.event=="del"){//删除操作
            //删除操作
            deleteSaleChance(data);
        }
    });

    // 行工具栏监听事件
    table.on('tool(saleChances)',function (obj) {
        var ida =obj.event;
        if(ida == "edit"){
            openAddOrUpdateSaleChanceDialog(obj.data.id);
        }else if(ida == "del"){
            layer.confirm("确认删除当前记录?",{icon: 3, title: "学生信息"},function (index) {
                $.post(ctx+"/student/delete",{studentId:obj.data.id},function (data) {
                    if(data.code==200){
                        layer.msg("删除成功",{icon : 6});
                        //刷新表格
                        tableIns.reload();
                    }else{
                        //提示失败
                        layer.msg(data.msg,{icon: 5});
                    }
                })
            })
        }

    });

    // 头工具栏事件
    table.on('toolbar(saleChances)',function (obj) {
        switch (obj.event) {
            case "add":
                openAddOrUpdateSaleChanceDialog();
                break;
            case "del":
                //console.log(table.checkStatus(obj.config.id).data);
                deleteSaleChance(table.checkStatus(obj.config.id).data);
                break;
        }
    });





    /**
     * 打开添加营销机会数据的窗口
     * 如果营销机会ID为空，则为添加操作
     * 如果营销机会ID不为空，则为修改操作
     */
    function openAddOrUpdateSaleChanceDialog(id) {
        //弹出层的标题
        var title="<h3>学生信息管理-学生信息添加</h3>";
        var url=ctx + "/student/addye";
        //判断学生ID是否为空
        if (id != null && id != ''){
            //更新操作
            title ="<h3>学生信息管理-更新学生信息</h3>";
            //请求地址学生ID
            url +='?id=' + id;
        }
        //iframe层
       layui.layer.open({
           //标题
           title:title,
           //类型
           type:2,
           //宽高
           area:['510px','550px'],
           //url地址
            content:url,
           //可以最大化与最小化
           maxmin:true
        });
    }
    //删除学生信息（删除多条记录）
    function deleteSaleChance(data){
        //获取数据表格选中的行数据  table.checkStatus('数据表格的ID属性值')
        var checkStatus = table.checkStatus("saleChanceTable");
        console.log(checkStatus);

        //获取所有被选中的记录对应的数据
        var saleChanceData =checkStatus.data;
        //判断用户是否选择的记录（选中行的数量大于0）
        if (saleChanceData.length<1){
            layer.msg("请选择要删除的记录!",{icon:5});
            return
        }

        //询问用户是否确认删除
        layer.confirm("您确认要删除选中的记录吗?",{icon:3,title:'营销机会管理'},function (index){
                //关闭确认框
            layer.close(index);
            //传递的参数是数组 ids=1&ids=2&ids=3
            var studentId="studentId=";
            //循环选中的行记录的数据
            // for (var i=0;i<saleChanceData.length;i++){
            //     studentId=studentId+saleChanceData[i].id + "&studentId="
            // }
            for (var i = 0; i < saleChanceData.length;i++) {
                if (i < saleChanceData.length-1) {
                    studentId = studentId + saleChanceData[i].id + "&studentId="
                } else {
                    studentId = studentId + saleChanceData[i].id;
                }
            }
                // console.log(ids);
            //发送ajax请求，执行删除营销机会
            $.ajax({
                type:"post",
                url: ctx + "/student/delete",
                data:studentId,//传递的参数是数组 ids=1&ids=2&ids=3
                success:function (result){
                    //判断删除结果
                    if (result.code==200){
                        //提示成功
                        layer.msg("删除成功!",{icon:6})
                        //刷新表格
                        tableIns.reload();
                    }else {
                        //提示失败
                        layer.msg(result.msg,{icon:5});
                    }
                }
            });

            });
    }

});
