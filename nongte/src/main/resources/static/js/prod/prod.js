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
        success: function(rets) {
            var html='';
            for (data of rets) {
                html += '<div class="col-sm-6 col-md-3"><div class="thumbnail"><a class="prod-a clearfix text-dec-none" href="prod/detail/' + data.id + '"><div class="box"> <img alt="' + data.title + '" src="file?filename=' + data.pic_url + '"> </div> <div class="caption"> <div class="top clearfix"> <span>' + data.title + '</span> <img src="images/db00' + data.db + '.jpg"> </div> <p class="desc">' + data.desc + '</p> </div> </a> </div> </div> ';
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