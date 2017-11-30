//jquery写法Tab导航栏切换
function tab_switch1(ele_id,activeclass){
    $("#"+ele_id+">.tab1").children().each(function() {
        var xh=$(this).index();
        $(this).find("*").click(function(){
            $(this).addClass(activeclass);
            $(this).parent().siblings().find("*").removeClass(activeclass);
            $("#"+ele_id+">.tab_con").find("*").addClass("hidden");
            //alert(xh);
            $("#"+ele_id+">.tab_con").find("*").eq(xh).removeClass("hidden");
        })
    });
}
//根据ID调用菜单切换函数
tab_switch1("tab_a","active");
