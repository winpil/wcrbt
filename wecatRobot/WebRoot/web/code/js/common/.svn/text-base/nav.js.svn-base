/**
 * Created by Administrator on 2017/6/13.
 */
(function () {
    var navItem = $(".top_nav ul.oneMenu>li");
    navItem.on("click",function () {
        var that = $(this);
        var index = that.index();
        that.addClass("active").siblings().removeClass("active");
        that.siblings().children("ol").hide();
        that.children("ol").slideToggle(150);
    });
})();