/**
 *
 */
$(function () {
    var initUrl = "/schooloto/shopadmin/getshopinitinfo"

    var registerShopUrl = "/schooloto/shopadmin/registshop"
    // 调用函数，加载数据
    getShopInitInfo()

    /**
     * 从后台加载获取下拉菜单的值
     */
    function getShopInitInfo() {
        $.getJSON(initUrl, function (data) {
            if (data.success) {
                var tempShopCategoryHtml = ""
                var tempShopAreaHtml = ""
                data.shopCategoryList.map(function (item, index) {
                    tempShopCategoryHtml += "<option data-id='" + item.shopCategoryId + "'>" + item.shopCategoryName + "</option>";
                });
                data.areaList.map(function (item, index) {
                    tempShopAreaHtml += "<option data-id='" + item.areaId + "'>" + item.areaName + "</option>"
                });
                $('#shop-category').html(tempShopCategoryHtml);
                $('#shop-area').html(tempShopAreaHtml)
            } else {
                $.total(data.errMsg)
            }
        });
    };

    $('#submit').click(function () {
        var shop = {}
        shop.shopName = $("#shop-name").val()
        shop.shopAddr = $("#shop-addr").val()
        shop.phone = $("#shop-phone").val()
        shop.shopDesc = $("#shop-desc").val()

        shop.shopCategory = {
            shopCategoryId: $('#shop-category').find('option').not(function () {
                return !this.selected;
            }).data('id')
        };
        shop.area = {

            areaId: $('#shop-area').find('option').not(function () {
                return !this.selected;
            }).data('id')
        };

        var shopImg = $('#shop-img')[0].files[0];

        var verifyCodeActual = $('#j_kaptcha').val()
        console.log('verifyCodeActual:' + verifyCodeActual);
        if (!verifyCodeActual) {
            $.toast('请输入验证码');
            return;
        }

        var formData = new FormData();
        formData.append('shopImg', shopImg);
        formData.append('shopStr', JSON.stringify(shop));

        formData.append('verufyCodeActual', verifyCodeActual);

        $.ajax({
            url: registerShopUrl,
            type: 'POST',
            data: formData,
            contentType: false,
            processData: false,
            cache: false,
            success: function (data) {
                if (data.success()) {
                    $.toast('提示信息' + data.errMsg);
                } else {
                    $.toast('提示信息' + data.errMsg);
                }
                $('#kaptcha_img').click();
            }
        })
    })
})