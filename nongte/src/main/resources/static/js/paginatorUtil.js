if(totalPages > 1) {
var options = {
//根据后台返回的分页相关信息，设置插件参数
bootstrapMajorVersion: 3, //如果是bootstrap3版本需要加此标识，并且设置包含分页内容的DOM元素为UL,如果是bootstrap2版本，则DOM包含元素是DIV
currentPage: currentPage, //当前页数
totalPages: totalPages, //总页数
numberOfPages: limit, //每页记录数
pageUrl: function(type, page, current) {
if (page != currentPage) {
return url + page;
}
},
shouldShowPage: function (type, page, current) {
switch(type) {
case 'first':
return false;
case 'page':
return true;
case 'prev':
return currentPage > 1;
case 'next':
return currentPage < totalPages;
case 'last':
return false;
}
},
onPageClicked: function (type, page, current) {},
itemTexts: function (type, page, current) {
//设置分页按钮显示字体样式
switch (type) {
case 'first':
return '‹‹';
case 'prev':
return '‹';
case 'page':
return page;
case 'next':
return '›';
case 'last':
return '››';
}
}
};
$('#paginator').bootstrapPaginator(options); //设置分页
}