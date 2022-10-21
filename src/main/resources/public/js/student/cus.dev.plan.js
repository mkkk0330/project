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
            /*{field: 'dormitoryId', title: '开发状态', align:'center',templet:function (d) {
                    return formatterDevResult(d.dormitoryId);
                }},*/
            {title: '修改信息', templet:'#op',fixed:"right",align:"center", minWidth:150},
            {title: '操作',fixed:"right",align:"center", minWidth:150,templet:"#cusDevPlanListBar"}
        ]]
    });


/*    function formatterDevResult(value){
        /!**
         * 0-未开发
         * 1-开发中
         * 2-开发成功
         * 3-开发失败
         *!/
        if(value==0){
            return "<div style='color: yellow'>未开发</div>";
        }else if(value==1){
            return "<div style='color: #00FF00;'>1号楼</div>";
        }else if(value==2){
            return "<div style='color: #00B83F'>开发成功</div>";
        }else if(value==3){
            return "<div style='color: red'>开发失败</div>";
        }else {
            return "<div style='color: #af0000'>未知</div>"
        }
    }*/


    // 多条件搜索
    $(".search_btn").on("click",function () {
        tableIns.reload({
            page:{
                curr:1
            },
            where:{
                customerName:$("[name='customerName']").val(),// 客户名
                createMan:$("[name='createMan']").val(),// 创建人
                devResult: $("#devResult").val() //开发状态
            }
        })
    });

    /**
     * 行工具监听
     */
    table.on("tool(saleChances)",function (data) {
        //var layEvent = data.event;
        if(data.event==="dev"){
            openCusDevPlanDialog("计划项数据维护",data.data.id);
        }else if(data.event ==="info"){
            openCusDevPlanDialog("计划项数据详情",data.data.id);
        }
    });


    function openCusDevPlanDialog(title,id) {
        layui.layer.open({
            title:title,
            type:2,
            area:["750px","500px"],
            content:ctx + "/cus_dev_plan/toCusDevPlanPage?id=" + id,
            maxmin:true
        })
    }


});
