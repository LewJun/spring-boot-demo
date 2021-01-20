$(function() {
$("#queryFdBtn").click(function() {
    $("#fdTable").bootstrapTable('refresh');
});

$("#fdTable").bootstrapTable({
    url:"fd/list/query",   //请求地址
    striped: true, //是否显示行间隔色
    pageNumber: 1, //初始化加载第一页
    pagination: true, //是否分页
    sidePagination: 'server',//server:服务器端分页|client：前端分页
    pageSize: 5, //单页记录数
    pageList: [5, 10],//可选择单页记数
    showRefresh: true,
    showToggle: true,
    showColumns: true,
    minimumCountColumns: 2,
    cardView: false,
    search: false,
    queryParams: function(params) {//上传服务器的参数
        var temp = {
            offset :params.offset + 0// SQL语句起始索引
            , pageNumber : params.limit  // 每页显示数量
            , fd002: $("#fd002").val()
        };
        return temp;
    },
    columns: [
        {field: 'fd001', title: '编码', align: 'center'},
        {field: 'fd002', title: '名称', align: 'center'},
        {field: 'fd003', title: '可食部', align: 'center'},
        {field: 'fd004', title: '一类名称', align: 'center'},
        {field: 'fd005', title: '二类名称', align: 'center'},
        {field: 'fd006', title: '能量', align: 'center'},
        {field: 'fd007', title: '蛋白质', align: 'center'},
        {field: 'fd008', title: '脂肪', align: 'center'},
        {field: 'fd009', title: '维生素A', align: 'center'},
        {field: 'fd010', title: '维生素B1', align: 'center'},
        {field: 'fd011', title: '维生素B2', align: 'center'},
        {field: 'fd012', title: '维生素C', align: 'center'},
        {field: 'fd013', title: '钙', align: 'center'},
        {field: 'fd014', title: '钠', align: 'center'},
        {field: 'fd015', title: '铁', align: 'center'},
        {field: 'fd016', title: '锌', align: 'center'},
        {field: 'fd017', title: '用量', align: 'center'},
        {field: 'yyss001', title: '是否对照', align: 'center',
            formatter: function(value, row, index) {
                var result = '';
                if (row.yyss001) {
                    var fd018 = row.fd018
                    var d = fd018.year + '/' + fd018.monthValue + '/' + fd018.dayOfMonth
                    result += '<span class="label label-success">' + d + '</span>'
                } else {
                    result += '<span class="label label-default">未对照</span>'
                }
                return result;
            }
        },
        {title: '操作', align: 'center',
            events: {
                'click #fd-detail': function(e, value, row, index) {
                    // TODO 根据fd001查询基础数据，和合并后的数据
                    // TODO 显示到fdDetailModal中的相应位置
                    // TODO 显示模态框
                    // fd/detail/"+row.fd001
                    $('#fdDetailModal').modal('show');
                },
                'click #fd-merge': function(e, value, row, index) {
                    $('#yyss007').val(row.fd002);
                    $('#yyss007').attr('data-fd001', row.fd001);
                    $("#yyssTable").bootstrapTable('refresh');
                    $('#fdMergeModal').modal('show');
                },
            },
            formatter: function(value, row, index) {
                var result = ''
                result += "<a class='btn btn-xs btn-info glyphicon glyphicon-eye-open' id='fd-detail' href='javascript:void(0)' style='margin-right: 5px;'></a>";
                result += "<a class='btn btn-xs btn-success glyphicon glyphicon-edit' id='fd-merge' href='javascript:void(0)'></a>";
                return result;
            }
        }
    ]
});

$("#queryYyssBtn").click(function() {
    $("#yyssTable").bootstrapTable('refresh');
});

$("#yyssTable").bootstrapTable({
    url:"yyss/list/query",   //请求地址
    striped: true, //是否显示行间隔色
    pageNumber: 1, //初始化加载第一页
    pagination: true, //是否分页
    sidePagination: 'server',//server:服务器端分页|client：前端分页
    pageSize: 5, //单页记录数
    pageList: [5, 10],//可选择单页记数
    showRefresh: true,
    showToggle: true,
    showColumns: true,
    minimumCountColumns: 2,
    cardView: false,
    search: false,
    queryParams: function(params) {//上传服务器的参数
        var temp = {
            offset :params.offset + 0// SQL语句起始索引
            , pageNumber : params.limit  // 每页显示数量
            , yyss007: $("#yyss007").val()
        };
        return temp;
    },
    columns: [
        {title: '操作', align: 'center',
            events: {
                'click #yyss-merge': function(e, value, row, index) {
                    $.ajax({
                        url: 'fd/merge',
                        data: {
                            yyss001: row.yyss001,
                            fd001: $('#yyss007').attr('data-fd001'),
                        },
                        type: 'post',
                        beforeSend: function() {
                            // 显示loading组件
                            $('body').mLoading("show");
                        },
                        success: function(result) {
                            $('#fdMergeModal').modal('hide');
                            $("#fdTable").bootstrapTable('refresh');
                        },
                        error: function(err) {
                            alert(err);
                        },
                        complete: function() {
                            // 隐藏loading组件
                            $('body').mLoading("hide");
                        }
                    });
                },
            },
            formatter: function(value, row, index) {
                return "<a class='btn btn-xs btn-primary glyphicon glyphicon-transfer' id='yyss-merge' href='javascript:void(0)'></a>";
            }
        },
        {field:'yyss001', title:'ID',align:'center',},
        {field:'yyss002', title:'大类编号',align:'center',},
        {field:'yyss003', title:'大类名称',align:'center',},
        {field:'yyss004', title:'亚类编号',align:'center',},
        {field:'yyss005', title:'亚类名称',align:'center',},
        {field:'yyss006', title:'食物编号',align:'center',},
        {field:'yyss007', title:'食物名称',align:'center',},
        {field:'yyss008', title:'食物大类',align:'center',},
        {field:'yyss009', title:'食物小类',align:'center',},
        {field:'yyss010', title:'标准码',align:'center',},
        {field:'yyss011', title:'可食部%',align:'center',},
        {field:'yyss012', title:'血糖指数',align:'center',},
        {field:'yyss013', title:'关联食物',align:'center',},
        {field:'yyss014', title:'食物名称',align:'center',},
        {field:'yyss015', title:'食物标准码',align:'center',},
        {field:'yyss016', title:'食物编号',align:'center',},
        {field:'yyss017', title:'大类编号',align:'center',},
        {field:'yyss018', title:'亚类编号',align:'center',},
        {field:'yyss019', title:'食物编码',align:'center',},
        {field:'yyss020', title:'食物描述',align:'center',},
        {field:'yyss021', title:'食物构成',align:'center',},
        {field:'yyss022', title:'可食部',align:'center',},
        {field:'yyss023', title:'水',align:'center',},
        {field:'yyss024', title:'能量（千卡）',align:'center',},
        {field:'yyss025', title:'能量(千焦)',align:'center',},
        {field:'yyss026', title:'蛋白质-克',align:'center',},
        {field:'yyss027', title:'异亮氨酸',align:'center',},
        {field:'yyss028', title:'亮氨酸',align:'center',},
        {field:'yyss029', title:'赖氨酸',align:'center',},
        {field:'yyss030', title:'含硫氨基酸',align:'center',},
        {field:'yyss031', title:'丝氨酸',align:'center',},
        {field:'yyss032', title:'蛋氨酸',align:'center',},
        {field:'yyss033', title:'胱氨酸',align:'center',},
        {field:'yyss034', title:'芳香族氨基酸',align:'center',},
        {field:'yyss035', title:'苯丙氨酸',align:'center',},
        {field:'yyss036', title:'苷氨酸',align:'center',},
        {field:'yyss037', title:'络氨酸',align:'center',},
        {field:'yyss038', title:'苏氨酸',align:'center',},
        {field:'yyss039', title:'色氨酸',align:'center',},
        {field:'yyss040', title:'缬氨酸',align:'center',},
        {field:'yyss041', title:'脯氨酸',align:'center',},
        {field:'yyss042', title:'精氨酸',align:'center',},
        {field:'yyss043', title:'组氨酸',align:'center',},
        {field:'yyss044', title:'丙氨酸',align:'center',},
        {field:'yyss045', title:'天冬氨酸',align:'center',},
        {field:'yyss046', title:'谷氨酸 ',align:'center',},
        {field:'yyss047', title:'脂肪-克',align:'center',},
        {field:'yyss048', title:'脂肪酸',align:'center',},
        {field:'yyss049', title:'饱和脂肪酸',align:'center',},
        {field:'yyss050', title:'单不饱和脂肪酸',align:'center',},
        {field:'yyss051', title:'多不饱和脂肪酸 ',align:'center',},
        {field:'yyss052', title:'碳水化合物-克',align:'center',},
        {field:'yyss053', title:'膳食纤维-克',align:'center',},
        {field:'yyss054', title:'可溶性膳食纤维-克',align:'center',},
        {field:'yyss055', title:'不可溶性膳食纤维-克',align:'center',},
        {field:'yyss056', title:'胆固醇-毫克',align:'center',},
        {field:'yyss057', title:'胡萝卜素-毫克',align:'center',},
        {field:'yyss058', title:'维生素A-微克',align:'center',},
        {field:'yyss059', title:'维生素B1-毫克',align:'center',},
        {field:'yyss060', title:'核黄素(VB2)毫克',align:'center',},
        {field:'yyss061', title:'维生素B6-毫克',align:'center',},
        {field:'yyss062', title:'维生素B12-微克',align:'center',},
        {field:'yyss063', title:'维生素C-毫克',align:'center',},
        {field:'yyss064', title:'维生素D-毫克',align:'center',},
        {field:'yyss065', title:'维生素E-毫克',align:'center',},
        {field:'yyss066', title:'生育酚-毫克',align:'center',},
        {field:'yyss067', title:'维生素K-毫克',align:'center',},
        {field:'yyss068', title:'视黄醇-微克',align:'center',},
        {field:'yyss069', title:'叶酸-微克',align:'center',},
        {field:'yyss070', title:'烟酸(尼克酸)-毫克',align:'center',},
        {field:'yyss071', title:'胆碱-毫克',align:'center',},
        {field:'yyss072', title:'灰分',align:'center',},
        {field:'yyss073', title:'碘-微克',align:'center',},
        {field:'yyss074', title:'生物素-微克',align:'center',},
        {field:'yyss075', title:'血糖指数',align:'center',},
        {field:'yyss076', title:'嘌呤含量',align:'center',},
        {field:'yyss077', title:'泛酸-毫克',align:'center',},
        {field:'yyss078', title:'钙-毫克',align:'center',},
        {field:'yyss079', title:'磷-毫克',align:'center',},
        {field:'yyss080', title:'钾-毫克',align:'center',},
        {field:'yyss081', title:'钠-毫克',align:'center',},
        {field:'yyss082', title:'镁-毫克',align:'center',},
        {field:'yyss083', title:'铁-毫克',align:'center',},
        {field:'yyss084', title:'锰-毫克',align:'center',},
        {field:'yyss085', title:'锌-毫克',align:'center',},
        {field:'yyss086', title:'硒-微克',align:'center',},
        {field:'yyss087', title:'铜-毫克',align:'center',},
    ]
});

});
