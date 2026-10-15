$(function () {
    //页面一加载，idx=0 获取全部数据
    loadData(0);
})
//封装加载数据函数
function loadData(idx) {
    $.ajax({
        url: "/Jsp_insure/CommodityServlet?op=findByType",
        type: "post",
        data: {
            idx: idx
        },
        dataType: "json",
        success: function (res) {
            $("#context_text").html("");
            for(let i = 0 ; i < res.length ; i++){
                let item = res[i];
                let htmlStr =
                    "<div class='div_context'>"
                    +"<div class='div_context_l'>"
                        +"<img src='"+item.commodity_img+"' width='350px' height='260px'/>"
                    +"</div>"
                    +"<div class='div_context_r'>"
                        +"<div class='div_context_r_1'>"
                            +"<h2>"+item.commodity_name+"</h2>"
                            +"<p class='p1'>"+item.commodity_introduce+"</p >"
                        +"</div>"
                        +"<div class='div_context_r_2'>"
                            +"<div class='price'>"
                                +"<span class='fh'><b>¥</b></span>"
                                +"<span class='price_a'>"+item.commodity_price+"</span>"
                                +"<span class='fh'>起</span>"
                            +"</div>"
                            +"<a href='CommodityServlet?op=commodityQueryById&commodity_id="+item.commodity_id+"'>"
                                +"<div class='img_a'>詳細はこちら</div>"
                            +"</a >"
                        +"</div>"
                    +"</div>"
                    +"</div>";
                $("#context_text").append(htmlStr);
            }
        }
    })
}

//鼠标悬浮切换分类
$(document).on("click", "#top2_text_ul > li", function () {
    let num = $(this).index();
    console.log("点击下标 =", num);
    loadData(num);
});