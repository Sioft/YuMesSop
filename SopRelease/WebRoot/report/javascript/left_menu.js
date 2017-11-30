/**
 * Created by Administrator on 2017/7/7.
 */
/*li点击背景改变*/
$(function(){
    $("ul.navbar-nav li").click(function(){
        $(this).addClass("nav_first_li").siblings().removeClass("nav_first_li");
    });
});
/*页面切换*/

/*基础信息左侧菜单*/
$(function(){
    $.get("html_page/basic_msg/msg1.html",function(data){
        $("#product_basic_msg").html(data);//初始化加载界面
    });

    $('#indexMenu_basic_msg li').click(function(){//点击li加载界面
        var current = $(this),
            target = current.find('a').attr('target'); // 找到链接a中的targer的值
        $.get(target,function(data){
            $("#product_basic_msg").html(data);
        });
    });
});

/*生产任务单左侧菜单*/
$(function(){
    $.get("html_page/production_scrwd/task_list_entry.html",function(data){
        $("#product_right").html(data);//初始化加载界面
    });

    $('#indexMenu li').click(function(){//点击li加载界面
        var current = $(this),
            target = current.find('a').attr('target'); // 找到链接a中的targer的值
        $.get(target,function(data){
            $("#product_right").html(data);
        });
    });
});

/*生产工艺信息左侧菜单*/
$(function(){
    $.get("html_page/production_technology/SOP_upload.html",function(data){
        $("#product_right_technology").html(data);//初始化加载界面
    });

    $('#indexMenu_technology li').click(function(){//点击li加载界面
        var current = $(this),
            target = current.find('a').attr('target'); // 找到链接a中的targer的值
        $.get(target,function(data){
            $("#product_right_technology").html(data);
        });
    });
});

/*品质追溯左侧菜单*/
$(function(){
    $.get("html_page/quality_traceability/search_quality.html",function(data){
        $("#product_right_quality").html(data);//初始化加载界面
    });

    $('#indexMenu_quality li').click(function(){//点击li加载界面
        var current = $(this),
            target = current.find('a').attr('target'); // 找到链接a中的targer的值
        $.get(target,function(data){
            $("#product_right_quality").html(data);
        });
    });
});

/*维修管理左侧菜单*/
$(function(){
    $.get("html_page/requir/requir_msg_input.html",function(data){
        $("#product_right_requir").html(data);//初始化加载界面
    });

    $('#indexMenu_requir li').click(function(){//点击li加载界面
        var current = $(this),
            target = current.find('a').attr('target'); // 找到链接a中的targer的值
        $.get(target,function(data){
            $("#product_right_requir").html(data);
        });
    });
});
/*报表管理左侧菜单*/
$(function(){
    $.get("html_page/report_form/form1.html",function(data){
        $("#product_right_report").html(data);//初始化加载界面
    });

    $('#indexMenu_report li').click(function(){//点击li加载界面
        var current = $(this),
            target = current.find('a').attr('target'); // 找到链接a中的targer的值
        $.get(target,function(data){
            $("#product_right_report").html(data);
        });
    });
});