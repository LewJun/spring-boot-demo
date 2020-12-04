/**
 * 地区选择器
 * @version 1.1.0
 * @author <yangjian102621@gmail.com>
 */
(function ($) {
  $.fn.JAreaSelect = function (options) {
    var obj = {};
    var $container = $(this);
    var areaData = __AREADATA__; //地区数据
    //初始化参数
    var defaults = {
      prov: 0, //省
      city: 0, //市
      area: 0, //区
      name: {
        prov: 'province_code',
        city: 'city_code',
        area: 'area_code',
      },

      selectClassName: 'form-control', //select class名称
    };

    var chooseTxt = "请选择";

    /* 合并参数 */
    options = $.extend(defaults, options);

    //创建元素
    obj.create = function () {
      obj.province = $(
        '<select class="' +
          options.selectClassName +
          '" name="' +
          options.name.prov +
          '"></select>'
      );
      obj.province.append('<option value="">'+chooseTxt+'</option>');
      //加载所有省级
      $.each(areaData.prov, function (id, name) {
        if (id == options.prov) {
          obj.province.append(
            '<option value="' + id + '" selected>' + name + '</option>'
          );
        } else {
          obj.province.append(
            '<option value="' + id + '">' + name + '</option>'
          );
        }
      });

      //绑定选中省级事件
      obj.province.on('change', function () {
        //删除元素
        try {
          obj.city.remove();
          obj.area.remove();

          obj.city = null;
          obj.area = null;
        } catch (e) {
          // console.log(e)
        }

        var pid = $(this).val(); //获取省份id

        if (areaData.city[pid] && areaData.city[pid].length > 0) {
          obj.city = $(
            '<select class="' +
              options.selectClassName +
              '" name="' +
              options.name.city +
              '"></select>'
          );
          obj.city.append('<option value="">'+chooseTxt+'</option>');
          $.each(areaData.city[pid], function (i, item) {
            if (item.id == options.city) {
              obj.city.append(
                '<option value="' + item.id + '" selected>' + item.name + '</option>'
              );
            } else {
              obj.city.append(
                '<option value="' + item.id + '">' + item.name + '</option>'
              );
            }
          });

          //切换城市的时候加载地区
          obj.city.on('change', function () {
            try {
              obj.area.remove();
              obj.area = null;
            } catch (e) {
              //console.log(e)
            }

            var cid = $(this).val();
            if (areaData.area[cid] && areaData.area[cid].length > 0) {
              obj.area = $(
                '<select class="' +
                  options.selectClassName +
                  '" name="' +
                  options.name.area +
                  '"></select>'
              );
              obj.area.append('<option value="">'+chooseTxt+'</option>');
              $.each(areaData.area[cid], function (i, item) {
                if (item.id == options.area) {
                  obj.area.append(
                    '<option value="' +
                      item.id +
                      '" selected>' +
                      item.name +
                      '</option>'
                  );
                } else {
                  obj.area.append(
                    '<option value="' + item.id + '">' + item.name + '</option>'
                  );
                }
              });
              $container.append(obj.area);
            }
          });
          $container.append(obj.city);
          obj.city.trigger('change'); //自动触发事件
        }
      });
      $container.append(obj.province);
      obj.province.trigger('change'); //自动触发事件
    };

    //获取区域id
    obj.getAreaObj = function () {
      return {
        province_code: obj.province && obj.province.val(),
        city_code: obj.city && obj.city.val(),
        area_code: obj.area && obj.area.val(),
      };
    };

    //获取区域字符串
    obj.getAreaString = function () {
      var provinceObj = obj.province;
      var cityObj = obj.city;
      var areaObj = obj.area;

      var optionSelected = 'option:selected';

      var provinceHtml = provinceObj && provinceObj.find(optionSelected).html();
      if(provinceHtml == chooseTxt) provinceHtml = '';

      var cityHtml = cityObj && cityObj.find(optionSelected).html();
      if(cityHtml == chooseTxt) cityHtml = '';

      var areaHtml = areaObj && areaObj.find(optionSelected).html();
      if(areaHtml == chooseTxt) areaHtml = '';

      return {
        province_name: provinceHtml,
        city_name: cityHtml,
        area_name: areaHtml,
      };
    };

    obj.create();
    return obj;
  };
})(jQuery);
