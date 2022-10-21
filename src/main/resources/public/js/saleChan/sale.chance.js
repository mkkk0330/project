layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;


    //营销机会列表展示
    var tableIns = table.render({
        id:'saleChanceTable',
        elem: '#saleChanceList',
        url : ctx+'/sal/list',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        cols : [[
            {type: "checkbox", fixed:"center"},
            {field: "id", title:'学号',fixed:"true",align:"center"},
            {field: 'name', title: '姓名',align:"center"},
            {field: 'gender', title: '性别',  align:'center'},
            {field: 'building', title: '宿舍楼', align:'center'},
            {field: 'dormitoryId', title: '宿舍号', align:'center'},
            {field: 'state', title: '入住情况',  align:'center'},
            {field: 'createDate', title: '登记时间',  align:'center'},

 /*           {field: 'state', title: '分配状态', align:'center',templet:function(d){
                    return formatterState(d.state);
                }},
            {field: 'devResult', title: '开发状态', align:'center',templet:function (d) {
                    return formatterDevResult(d.devResult);
                }},*/
            {title: '操作', templet:'#saleChanceListBar',fixed:"right",align:"center", minWidth:150}
        ]]
    });

/*    function formatterState(state){
        if(state==0){
            return "<div style='color:yellow '>未分配</div>";
        }else if(state==1){
            return "<div style='color: green'>已分配</div>";
        }else{
            return "<div style='color: red'>未知</div>";
        }
    }*/

/*
    function formatterDevResult(value){
        /!**
         * 0-未开发
         * 1-开发中
         * 2-开发成功
         * 3-开发失败
         *!/
        if(value==0){
            return "<div style='color: yellow'>未开发</div>";
        }else if(value==1){
            return "<div style='color: #00FF00;'>开发中</div>";
        }else if(value==2){
            return "<div style='color: #00B83F'>开发成功</div>";
        }else if(value==3){
            return "<div style='color: red'>开发失败</div>";
        }else {
            return "<div style='color: #af0000'>未知</div>"
        }
    }
*/

// 多条件搜索
    $(".search_btn").on("click",function () {
        tableIns.reload({
            page:{
                curr:1
            },
            where:{
                customerName:$("[name='customerName']").val(),// 客户名
                createMan:$("[name='createMan']").val(),// 创建人
                state:$("#state").val()    //分配状态
            }
        })
    });

    // 头工具栏事件
    table.on('toolbar(saleChances)',function (data) {

        if (data.event == "add") {
            //打开添加
            openSaleChanceDialog();
        } else if (data.event == "del") {
            //删除
            //console.log(data.event);
            deleteSaleChance(data);
        }
    });






    //打开 添加/修改 营销机会的窗口
    function openSaleChanceDialog(saleChanceId){
        var title = "<h3>营销机会管理-添加营销机会<h3/>";
        var url = ctx + "/student/toSaleChancePage";

        //判断是否存在saleChance
        if (saleChanceId != null && saleChanceId!= ''){
            //更新操作
            title = "<h3>营销机会管理-更新营销机会<h3/>";
            //请求地址传递营销机会的id
            url += '?saleChanceId=' + saleChanceId;
        }

        layui.layer.open({
            title:title,
            type:2,
            area:["700px","500px"],
            content:url,
            maxmin:true
        })
    }

    /**
     * 删除营销机会（多条）
     * @param data
     */
    function deleteSaleChance(data){
        var checkStatus = table.checkStatus("saleChanceTable")
        console.log(checkStatus)
        //获取所以被选中的记录对应的数据
        var saleChanceData = checkStatus.data;
        //判断用户是否选择的记录(选中行的数量大于0)
        if (saleChanceData.length <1) {
            layer.msg("请选择要删除的记录",{icon:5});
            return;
        }

        //询问是否确认删除
        layer.confirm('您确认删除选择的记录吗?',{icon:3,title:"营销机会管理"},function (index){
            layer.close(index);
            //传递的参数是数组
            var ids = "ids=";
            //循环选中的行记录的数据
            for (var i = 0; i < saleChanceData.length;i++) {
                if (i < saleChanceData.length-1) {
                    ids = ids + saleChanceData[i].id + "&ids="
                } else {
                    ids = ids + saleChanceData[i].id;
                }
            }

            //发送ajax
            $.ajax({
                type:"post",
                url:ctx + "/add/delete",
                data:ids,
                success:function (result) {
                    if (result.code == 200) {
                        //删除成功
                        layer.msg("删除成功",{icon:6});
                        tableIns.reload();
                    } else {
                        //删除失败
                        layer.msg(result.msg,{icon:5});
                    }

                }
            })

        })
    }






    //行工具栏事件
    table.on('tool(saleChances)',function (data) {

        if (data.event == "edit") {
            //得到营销机会的id
            var saleChanceId = data.data.id;
            //打开修改
            openSaleChanceDialog(saleChanceId);
        } else if (data.event == "del") {
            //删除
            //弹出确认框，是否确认删除
            layer.confirm("确认删除该记录吗",{icon:3,title:"营销机会管理"},function (index){
               //关闭确认框
               layer.close(index);

               $.ajax({
                   type:"post",
                   url:ctx + "/add/delete",
                   data:{
                       ids:data.data.id
                   },
                   success:function (result) {
                       if (result.code == 200) {
                           //删除成功
                           layer.msg("删除成功",{icon:6});
                           tableIns.reload();
                       } else {
                           //删除失败
                           layer.msg("删除失败",{icon:5});
                       }
                   }
               })
            });

        }
    });




});
