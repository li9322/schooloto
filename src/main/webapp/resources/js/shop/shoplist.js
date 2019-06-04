$(function () {

    getshoplist();

    function getshoplist() {
        $.ajax({
            url: "/schooloto/shopadmin/getshoplist",
            type: "get",
            dataType: "json",
            success: function (data) {
                if (data.success) {
                    handleList(data.shopList);
                    // console.log(data.shopList);
                    handleUser(data.user);
                    // console.log(data.user);
                }
            }
        });
    }

    function handleUser(data) {
        $('#user-name').text(data.name);
        // console.log(data.name);
    }

    function handleList(data) {
        var shopListHtml = '';
        // data.map(function (item, index) {
        //     shopListHtml += '<div class="row row-shop">' +
        //         '<div class="col-40>"' + item.shopName + '</div>' +
        //         '<div class="col-40">' + shopStatus(item.enableStatus) + '</div>' +
        //         '<div class="col-20">' + goShop(item.enableStatus, item.shopId) + '</div>' +
        //         '</div>'
        // });
        data.map(function(item,index){
            shopListHtml += '<div class="row row-shop"><div class="col-40">'
                + item.shopName + '</div><div class="col-40">'
                + shopStatus(item.enableStatus) +'</div><div class="col-20">'
                + goShop(item.enableStatus,item.shopId)
                +'</div></div>'
        });
        console.log(shopListHtml);
        $('.shop-wrap').html(shopListHtml);
    }

    function shopStatus(status) {
        if (status == 0) {
            return '审核中';
        } else if (status == 1) {
            return '审核通过';
        } else {
            return '店铺非法';
        }
    }

    function goShop(status,shopId) {
        if (status==1){
            return '<a href="/schooloto/shopadmin/shopmanagement?shopId='+shopId+'">进入</a>';
        } else {
            return '';
        }
    }
});