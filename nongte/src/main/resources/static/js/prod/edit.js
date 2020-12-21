$('input[name="db"]').click(function() {
var that = $(this)
if(that.data('checked') || that.attr('checked')) {
that.removeAttr('checked')
that.prop('checked', false)
that.data('checked', false)
} else {
that.attr('checked', 'checked')
that.prop('checked', true)
that.data('checked', true)
}
});
$("#pic_x").click(function() {
    $('#pic-container').remove();
    $('#pic_url').remove();
    $('<input type="file" class="form-control" id="pic" name="pic" accept="image/*">').appendTo(".pic-div")
});
function initAreaSelector() {
    var area = $('#region-select-box').JAreaSelect(jAreaObj);
    return area;
}

function initWangEditor(ele, placeholder) {
    var E = window.wangEditor
    var editor = new E(ele)
    // 或者 const editor = new E( document.getElementById('div1') )
    // 设置编辑区域高度为 500px
    // editor.config.height = 500
    // 编辑器 z-index 默认为 10000，可以自行调整
    editor.config.zIndex = 1;
    // 配置 server 接口地址
    editor.config.uploadImgServer = 'wangEditor/uploadFile'
    editor.config.uploadFileName = 'file'
    editor.config.uploadImgTimeout = 5 * 1000
    editor.config.uploadImgMaxLength = 1 // 一次最多上传 5 个图片
    // 隐藏插入网络图片的功能
    editor.config.showLinkImg = false
    // 限制图片大小
    editor.config.uploadImgMaxSize = 2 * 1024 * 1024 // 2M
    // 限制图片类型
    editor.config.uploadImgAccept = ['jpg', 'jpeg', 'png', 'gif', 'bmp']

    editor.config.placeholder = placeholder

    editor.config.focus = false


    editor.config.uploadImgHooks = {
        // 图片上传并返回了结果，但图片插入时出错了
        fail: function(xhr, editor, resData) {
            // console.log('fail', resData)
            alert(resData.msg);
        },
        // 上传图片出错，一般为 http 请求的错误
        error: function(xhr, editor, resData) {
            console.log('error', xhr, resData)
            alert("图片上传失败");
        },
        // 上传图片超时
        timeout: function(xhr) {
            console.log('timeout')
            alert("上传图片超时");
        },
        // 图片上传并返回了结果，想要自己把图片插入到编辑器中
        // 例如服务器端返回的不是 { errno: 0, data: [...] } 这种格式，可使用 customInsert
        // customInsert: function(insertImgFn, result) {
            // result 即服务端返回的接口
            // console.log('customInsert', result)

            // insertImgFn 可把图片插入到编辑器，传入图片 src ，执行函数即可
            // insertImgFn(result.data[0])
        //}
    }

    editor.create()

    return editor;
}

var selector = initAreaSelector();
var prodHtmlWangEditor = initWangEditor('#prod-html', '请输入正文');
var prodHtml2WangEditor = initWangEditor('#prod-html2', '请输入额外信息');

function submitForm() {

    var title = $("#title").val();
    // 数据校验
    if (!title) {
        alert("请输入产品名称");
        $("#title").focus();
        return;
    }

    var desc = $("#desc").val();
    if (!desc) {
        alert("请输入产品描述");
        $('#desc').focus();
        return;
    }

// 判断pic是否为空
    if ($('#pic') && $('#pic')[0] && !$("#pic")[0].files[0]) {
        alert("请选择产品封面图片");
        $('#pic').focus();
        return;
    }

    if ($('#pic') && $('#pic').val()) {
        if (!$('#pic')[0].files[0].type.startsWith('image/')) {
            alert("请选择合适的封面图片");
            $('#pic').focus();
            return;
        }
    }


    var areaObj = selector.getAreaObj();
    if (!areaObj.province_code) {
        alert("请完善所属区域");
        $('[name="province_code"]').focus();
        return;
    }

    var html = prodHtmlWangEditor.txt.html();
    if (!html) {
        alert("请输入正文");
        $('#product-content').focus();
        return;
    }

// 判断pic是否为空
    if ($('#pic') && $('#pic')[0] && $('#pic')[0].files[0]) {
        var picFrm = new FormData();
        picFrm.append('file', $('#pic')[0].files[0]);
        uploadPic(picFrm);
    } else {
        var frm = new FormData(document.getElementById("frm"))
        frm.append("html", prodHtmlWangEditor.txt.html());
        frm.append("html2", prodHtml2WangEditor.txt.html());
        uploadForm(frm);
    }
}

function uploadPic(picFrm) {
    $.ajax({
        url: "uploadFile",
        type: "post",
        data: picFrm,
        processData: false,
        contentType: false,
        beforeSend: function() {
            // 显示loading组件
            $('body').mLoading("show");
        },
        success: function(result) {
            var frm = new FormData(document.getElementById("frm"))
            frm.append("html", prodHtmlWangEditor.txt.html());
            frm.append("html2", prodHtml2WangEditor.txt.html());
            frm.delete("pic");
            frm.append("pic_url", result);
            uploadForm(frm);
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

function uploadForm(frm) {
    var areaStr = selector.getAreaString();
    frm.append('province_name', areaStr.province_name||'');
    frm.append('city_name', areaStr.city_name||'');
    frm.append('area_name', areaStr.area_name||'');

    $.ajax({
        url: "prod/save",
        type: "post",
        data: frm,
        processData: false,
        contentType: false,
        beforeSend: function() {
            // 显示loading组件
            $('body').mLoading("show");
        },
        success: function(id) {
            localStorage && localStorage.setItem('prod-edit', Date.now());
            // history.go(-1);
            location.replace('prod/detail/' + id);
        },
        error: function(err) {
            alert(err);
        },
        complete: function() {
            $('body').mLoading("hide");//隐藏loading组件
        }
    });
}
