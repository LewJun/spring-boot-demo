$(function() {
    $("#queryBtn").click(function() {
        $("#prodTable").bootstrapTable('refresh');
    });

    $("#prodTable").bootstrapTable({
        url:"prod/list/query",   //请求地址
        striped: true, //是否显示行间隔色
        pageNumber: 1, //初始化加载第一页
        pagination: true, //是否分页
        sidePagination: 'server',//server:服务器端分页|client：前端分页
        pageSize: 5, //单页记录数
        pageList: [5, 10, 20, 30],//可选择单页记数
        queryParams: function(params) {//上传服务器的参数
            var areaObj = areaSelector.getAreaObj();
            var temp = {
                offset :params.offset + 0,// SQL语句起始索引
                pageNumber : params.limit  // 每页显示数量
                , title: $("#title").val()
                , desc: $("#desc").val()
                , level: $("#level").val()
                , province_code: areaObj.province_code
                , city_code: areaObj.city_code
                , area_code: areaObj.area_code
                , status: $("#status").find('option:selected').val()
                , level_prov: $('#level_prov').val()
                , level_city: $('#level_city').val()
                , level_area: $('#level_area').val()
                , keywords: $('#keywords').val()
                , show_prov: $("[name=show_prov]:checked").val()
                , show_city: $("[name=show_city]:checked").val()
                , show_area: $("[name=show_area]:checked").val()
            };
            return temp;
        },
        columns: [
        {
            title: '名称',
            field: 'title',
            align: 'center',
            width: 102,
        },
        {
            title: '描述',
            field: 'desc',
            align: "center",
            formatter: function(value, row, index) {
                var result = '<span title='+value+'>'+value+'</span>';
                return result;
            }
        },
        {
            title: '关键字',
            field: 'keywords',
            align: "center",    formatter: function(value, row, index) {
                var result = '<span title="'+value+'">'+value+'</span>';
                return result;
            }
        },
        {
            title: '所属区域',
            field: 'region',
            align: "center",    formatter: function(value, row, index) {
                var result = '<span title="'+value+'">'+value+'</span>';
                return result;
            }
        },
        {
            title: '优先级',
            align: "center",
            width: 105,
            formatter: function(value, row, index) {
                var result = '';
                result += row['level_prov'];
                result += ', ' + row['level_city'];
                result += ', ' + row['level_area'];

                return result;
            }
        },
        {
            title: '显示',
            align: 'center',
            width: 90,
            formatter: function(value, row, index) {
                var result = '';
                result += (row['show_prov'] ? '是' : '否')
                result += ', ' + (row['show_city'] ? '是' : '否')
                result += ', ' + (row['show_area'] ? '是' : '否')
                return result;
            }
        },
        {
            title: '创建时间',
            field: 'create_time',
            align: "center",
            width: 91,
        },
        {
            title: '状态',
            field: 'status',
            align: 'center',
            width: 75,
            formatter: function(value, row, index) {
                var result='';
                if (row.status === 2) {
                    result += '<span class="label label-default">已禁用</span>'
                } else {
                    result += '<span class="label label-success">已启用</span>'
                }
                return result;
            }
        },
        {
            title: '操作',
            field: 'opr',
            align: 'center',
            width: 216,
            events: {
                'click #enable': function(e, value, row, index) {
                    changeStatus(row.id, 1);
                },
                'click #disable': function(e, value, row, index) {
                    changeStatus(row.id, 2);
                },
                'click #del': function(e, value, row, index) {
                    del(row.id);
                },
            },
            formatter: function(value, row, index) {
                var result="";
                result += "<a class='btn btn-sm btn-info' target='prod_detail_"+row.id+"' href='prod/detail/"+row.id+"' style='margin-right: 5px;'>详情</a>";
                result += "<a class='btn btn-sm btn-warning' target='prod_edit_"+row.id+"' href='prod/edit/"+row.id+"' style='margin-right: 5px;'>修改</a>";
                result += "<a class='btn btn-sm btn-danger' href='javascript:void(0)' style='margin-right: 5px;' id='del'>删除</a>";
                if (row.status === 2) {
                    result += "<a class='btn btn-sm btn-success' href='javascript:void(0)' id='enable'>启用</a>";
                } else {
                    result += "<a class='btn btn-sm btn-default' href='javascript:void(0)' id='disable'>禁用</a>";
                }
                return result;
            }
        }
        ]
    })
});

function del(id) {
    var ret = confirm("确定永久删除吗？");
    if (!ret) {
        return;
    }
    $.ajax({
        url: 'prod/delete/'+id,
        type: 'post',
        beforeSend: function() {
            //显示loading组件
            $('body').mLoading("show");
        },
        success: function(result) {
            // 状态修改成功后刷新
            $("#prodTable").bootstrapTable('refresh');
        },
        error: function(err) {
            alert(err);
        },
        complete: function() {
            // 隐藏loading组件
            $('body').mLoading("hide");
        }
    });
}
function changeStatus(id, status) {
    $.ajax({
        url: 'prod/changeStatus',
        type: 'post',
        data: {id: id, status: status},
        beforeSend: function() {
            //显示loading组件
            $('body').mLoading("show");
        },
        success: function(result) {
            // 状态修改成功后刷新
            // window.location.reload();
            $("#prodTable").bootstrapTable('refresh');
        },
        error: function(err) {
            alert(err);
        },
        complete: function() {
            // 隐藏loading组件
            $('body').mLoading("hide");
        }
    });
}

function initAreaSelector() {
    var area = $('#region-select-box').JAreaSelect({
      prov: '',
    });
    return area;
}

var areaSelector = initAreaSelector();

/**
* 页面加载完成后，执行
*/
function onload() {
console.log('onload');
if (!localStorage) {
    alert('浏览器版本太低了。不能刷新表格数据。');
    return;
}

if (localStorage && localStorage.getItem('prod-edit')) {
    $("#prodTable").bootstrapTable('refresh');
    localStorage.removeItem('prod-edit');
}
}