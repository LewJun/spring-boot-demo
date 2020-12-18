$(".area").click(function() {
    var that = $(this);
    $(".area.areaClicked").removeClass("areaClicked");
    that.addClass("areaClicked");

    var areaCode = that.attr('data-code');
    $.ajax({
        url: "prod/area/" + areaCode,
        type: "get",
        dataType: "json",
        beforeSend: function() {
             //显示loading组件
            $('body').mLoading("show");
        },
        success: function(d) {
            var html='';
            for (prod of d.prods) {
            html +=
            ('<div class="col-sm-6 col-md-3">' +
               '<div class="thumbnail">' +
                   '<a class="clearfix text-dec-none" href="prod/detail/' + prod.id + '">' +
                       '<div class="box">' +
                           '<img alt="'+ prod.title +'" src="file?filename='+ prod.pic_url + '"/>' +
                       '</div>' +
                       '<div class="caption">' +
                           '<div class="top"' +
                               'style="background:url("images/db00'+prod.db+'.jpg") no-repeat right center; background-size:contain;">' +
                               '<span class="prod-title">'+prod.title+'</span>' +
                           '</div>' +
                           '<p class="prod-desc">'+ prod.desc+'</p>' +
                       '</div>'+
                   '</a>'+
               '</div>'+
           '</div>');
            }
            $('.prod').html(html);
        },
        error: function(err) {
            console.log(err);
        },
        complete: function() {
            // 隐藏loading组件
            $('body').mLoading("hide");
        }
    });
});