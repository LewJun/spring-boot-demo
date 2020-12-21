$('a#search').click(function() {
// --暂时不对外开放（信息不够）。后期可将if(true) return;删除即可打开功能
if (true) return;
var s = document.getElementById("s").value
if(s) {
var a = document.createElement("a");
a.setAttribute("href", "prod/search?s=" + s);
a.setAttribute("target", "index_search");
document.body.appendChild(a);
a.click();
}
});

function submitForm() {
if(document.getElementById("s").value) return true;
return false;
}
