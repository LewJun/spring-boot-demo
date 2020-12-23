var __AREADATA__ = {}
$.ajax({
url: 'regions/select',
dataType: 'json',
async: false,
success: function(data) {
__AREADATA__ = data;
},
error: function(err) {
alert("获取省市区异常")
}
});
