<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>宿舍管理系统</title>
    <#include "common.ftl">
</head>
<body class="layui-layout-body layuimini-all">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header header">
        <div class="layui-logo">
            <a href="">
                <img src="images/logo.png" alt="logo">
                <h1>宿舍管理系统</h1>
            </a>
        </div>
        <a>
            <div class="layuimini-tool"><i title="展开" class="fa fa-outdent" data-side-fold="1"></i></div>
        </a>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item mobile layui-hide-xs" lay-unselect>
                <a href="javascript:;" data-check-screen="full"><i class="fa fa-arrows-alt"></i></a>
            </li>
            <li class="layui-nav-item layuimini-setting">
                <a href="javascript:;">${(systemAdmin.name)!""}</a>
                <dl class="layui-nav-child">
                    <dd>
                        <a href="javascript:;" data-iframe-tab="${ctx}/user/toSettingPage" data-title="基本资料" data-icon="fa fa-gears">基本资料</a>
                    </dd>
                    <dd>
                        <a href="javascript:;" data-iframe-tab="${ctx}/user/toPasswordPage" data-title="修改密码" data-icon="fa fa-gears">修改密码</a>
                    </dd>
                    <dd>
                        <a href="javascript:;" class="login-out">退出登录</a>
                    </dd>
                </dl>
            </li>
            <li class="layui-nav-item layuimini-select-bgcolor mobile layui-hide-xs" lay-unselect>
                <a href="javascript:;"></a>
            </li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll layui-left-menu">

                <ul class="layui-nav layui-nav-tree layui-left-nav-tree layui-this" id="currency">

                    <li class="layui-nav-item">
                        <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-street-view"></i><span class="layui-left-nav"> 学生信息管理</span> <span class="layui-nav-more"></span></a>
                        <dl class="layui-nav-child">

                            <dd>
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-1" data-tab="student/index" target="_self"><i class="fa fa-tty"></i><span class="layui-left-nav"> 学生信息</span></a>
                            </dd>


                            <dd>
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-2" data-tab="studentf/index" target="_self"><i class="fa fa-ellipsis-h"></i><span class="layui-left-nav"> 学生入住信息</span></a>
                            </dd>

                        </dl>
                    </li>


                    <li class="layui-nav-item">
                        <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-flag"></i><span class="layui-left-nav"> 楼层管理</span> <span class="layui-nav-more"></span></a><dl class="layui-nav-child">
                                <dd>
                                    <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-3" data-tab="louceng/index" target="_self"><i class="fa fa-exchange"></i><span class="layui-left-nav"> 楼层信息管理</span></a>
                                </dd>


                        </dl>
                    </li>

                    <li class="layui-nav-item">
                        <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-desktop"></i><span class="layui-left-nav"> 宿舍管理</span> <span class="layui-nav-more"></span></a>
                        <dl class="layui-nav-child">
                                <dd>
                                    <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-5" data-tab="dormitory/index" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 宿舍信息管理</span></a>
                                </dd>

                        </dl>
                    </li>


                    <li class="layui-nav-item">
                    <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-home"></i><span class="layui-left-nav"> 报修管理</span> <span class="layui-nav-more"></span></a><dl class="layui-nav-child">
                        <dd>
                            <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-10" data-tab="repair/index" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 宿舍报修管理</span></a>
                        </dd>

                    </dl>
                </li>
                    <li class="layui-nav-item">
                        <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-gears"></i><span class="layui-left-nav"> 宿管信息</span> <span class="layui-nav-more"></span></a>
                        <dl class="layui-nav-child">

                                <dd>
                                    <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-11" data-tab="house/index" target="_self"><i class="fa fa-user"></i><span class="layui-left-nav"> 宿舍管理员信息</span></a>
                                </dd>

                        </dl>
                    </li>

                    <li class="layui-nav-item">
                        <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-gears"></i><span class="layui-left-nav"> 宿舍通知信息</span> <span class="layui-nav-more"></span></a>
                        <dl class="layui-nav-child">

                            <dd>
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-11" data-tab="notice/index" target="_self"><i class="fa fa-user"></i><span class="layui-left-nav"> 通知管理</span></a>
                            </dd>

                        </dl>
                    </li>

                    <li class="layui-nav-item">
                        <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-home"></i><span class="layui-left-nav"> 统计报表</span> <span class="layui-nav-more"></span></a><dl class="layui-nav-child">
                            <dd>
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-10" data-tab="report/index" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav">宿舍人数分布</span></a>
                            </dd>
                        </dl>
                    </li>

                    <span class="layui-nav-bar" style="top: 201px; height: 0px; opacity: 0;"></span>
                </ul>
        </div>
    </div>

    <div class="layui-body">
        <div class="layui-tab" lay-filter="layuiminiTab" id="top_tabs_box">
            <ul class="layui-tab-title" id="top_tabs">
                <li class="layui-this" id="layuiminiHomeTabId" lay-id="welcome"><i class="fa fa-home"></i> <span>首页</span></li>
            </ul>

            <ul class="layui-nav closeBox">
                <li class="layui-nav-item">
                    <a href="javascript:;"> <i class="fa fa-dot-circle-o"></i> 页面操作</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" data-page-close="other"><i class="fa fa-window-close"></i> 关闭其他</a></dd>
                        <dd><a href="javascript:;" data-page-close="all"><i class="fa fa-window-close-o"></i> 关闭全部</a></dd>
                    </dl>
                </li>
            </ul>
            <div class="layui-tab-content clildFrame">
                <div id="layuiminiHomeTabIframe" class="layui-tab-item layui-show">
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="${ctx}/js/main.js"></script>
</body>
</html>
