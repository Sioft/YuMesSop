var moveEye = 0;
//裸机装配鼠标移入
$(function () {
    //鼠标的移入移出
    $("#ljzp").mouseover(function () {
        $("#ljzp_conta").show();
        $("#ljcs_conta").hide();
        $("#lhqcs_conta").hide();
        $("#lhhcs_conta").hide();
        $("#zj_conta").hide();
        $("#mpfj_conta").hide();
        $("#ljzp_conta").mouseover(function () {
            $("#ljzp_conta").show();
            moveEye = 1;
        }).mouseout(function () {
            $("#ljzp_conta").hide();
            moveEye = 0;
        });

        $("#ljzp_ysb").mouseover(function () {
            $("#ljzp_ysb_cont1").show();
            moveEye = 1;
        }).mouseout(function () {
            $("#ljzp_conta").hide();
            $("#ljzp_ysb_cont1").hide();
            moveEye = 0;
        });
        $("#ljzp_ysb_cont1").mouseover(function () {
            $("#ljzp_ysb_cont1").show();
            $("#ljzp_conta").show();
            $(".ljzp_hid_cont").show();
            moveEye = 1;
        }).mouseout(function () {
            $("#ljzp_conta").hide();
            $("#ljzp_ysb_cont1").hide();
            moveEye = 0;
        });
        //吸附塔
        $("#ljzp_xft").mouseover(function () {
            $("#ljzp_xft_cont2").show();
            moveEye = 1;
        }).mouseout(function () {
            $("#ljzp_conta").hide();
            $("#ljzp_xft_cont2").hide();
            moveEye = 0;
        });
        $("#ljzp_xft_cont2").mouseover(function () {
            $("#ljzp_xft_cont2").show();
            $("#ljzp_conta").show();
            $(".ljzp_hid_cont").show();
            moveEye = 1;
        }).mouseout(function () {
            $("#ljzp_conta").hide();
            $("#ljzp_xft_cont2").hide();
            moveEye = 0;
        });
        //换向阀
        $("#ljzp_hxf").mouseover(function () {
            $("#ljzp_hxf_cont3").show();
            moveEye = 1;
        }).mouseout(function () {
            $("#ljzp_conta").hide();
            $("#ljzp_hxf_cont3").hide();
            moveEye = 0;
        });
        $("#ljzp_hxf_cont3").mouseover(function () {
            $("#ljzp_hxf_cont3").show();
            $("#ljzp_conta").show();
            $(".ljzp_hid_cont").show();
            moveEye = 1;
        }).mouseout(function () {
            $("#ljzp_conta").hide();
            $("#ljzp_hxf_cont3").hide();
            moveEye = 0;
        });
        //电路板
        $("#ljzp_dlb").mouseover(function () {
            $("#ljzp_dlb_cont4").show();
            moveEye = 1;
        }).mouseout(function () {
            $("#ljzp_conta").hide();
            $("#ljzp_dlb_cont4").hide();
            moveEye = 0;
        });
        $("#ljzp_dlb_cont4").mouseover(function () {
            $("#ljzp_dlb_cont4").show();
            $("#ljzp_conta").show();
            $(".ljzp_hid_cont").show();
            moveEye = 1;
        }).mouseout(function () {
            $("#ljzp_conta").hide();
            $("#ljzp_dlb_cont4").hide();
            moveEye = 0;
        });

         if (moveEye == 0) {
            $(".ljzp").mouseout(function () {
                $("#ljzp_conta").hide();
            });

        }
    });
});


//裸机装测试鼠标移入
$(function (){
    //鼠标的移入移出
    $("#ljcs").mouseover(function () {
        $("#ljcs_conta").show();
        $("#ljzp_conta").hide();
        $("#lhqcs_conta").hide();
        $("#lhhcs_conta").hide();
        $("#zj_conta").hide();
        $("#mpfj_conta").hide();
        $("#ljcs_conta").mousemove(function () {
            $("#ljcs_conta").show();
    }).mouseout(function () {
            $("#ljcs_conta").hide();
        });
        //调压阀测试
        $("#ljcs_tyfcs").mouseover(function () {
            $("#ljcs_tyf_cont1").show();
            $("#lhqcs_tyf_cont1").hide();
            moveEye = 1;
        }).mouseout(function () {
            $("#ljcs_conta").hide();
            $("#ljcs_tyf_cont1").hide();
            moveEye = 0;
        });
        $("#ljcs_tyf_cont1").mouseover(function () {
            $("#ljcs_tyf_cont1").show();
            $("#ljcs_conta").show();
            $(".ljcs_tyf_cont1").show();
            $("#lhqcs_tyf_cont1").hide();
            moveEye = 1;
        }).mouseout(function () {
            $("#ljcs_conta").hide();
            $("#ljcs_tyf_cont1").hide();
            moveEye = 0;
        });
        //吸附塔测试
        $("#ljcs_xftcs").mouseover(function () {
            $("#ljcs_xft_cont2").show();
            moveEye = 1;
        }).mouseout(function () {
            $("#ljcs_conta").hide();
            $("#ljcs_xft_cont2").hide();
            moveEye = 0;
        });
        $("#ljcs_xft_cont2").mouseover(function () {
            $("#ljcs_xft_cont2").show();
            $("#ljcs_conta").show();
            $(".ljcs_xft_cont2").show();
            moveEye = 1;
        }).mouseout(function () {
            $("#ljcs_conta").hide();
            $("#ljcs_xft_cont2").hide();
            moveEye = 0;
        });
        //氧浓度
        $("#ljcs_yndcs").mouseover(function () {
            $("#ljcs_ynd_cont3").show();
            moveEye = 1;
        }).mouseout(function () {
            $("#ljcs_conta").hide();
            $("#ljcs_ynd_cont3").hide();
            moveEye = 0;
        });
        $("#ljcs_ynd_cont3").mouseover(function () {
            $("#ljcs_ynd_cont3").show();
            $("#ljcs_conta").show();
            $(".ljcs_ynd_cont3").show();
            moveEye = 1;
        }).mouseout(function () {
            $("#ljcs_conta").hide();
            $("#ljcs_ynd_cont3").hide();
            moveEye = 0;
        });
        if (moveEye == 0) {
            $(".ljcs").mouseout(function () {
                $("#ljcs_conta").hide();
            });
        }
    });
});




//老化前测试鼠标移入
$(function (){
    //鼠标的移入移出
    $("#lhqcs").mouseover(function () {
        $("#lhqcs_conta").show();
        $("#ljzp_conta").hide();
        $("#ljcs_conta").hide();
        $("#lhhcs_conta").hide();
        $("#zj_conta").hide();
        $("#mpfj_conta").hide();
        $("#lhqcs_conta").mouseover(function () {
            $("#lhqcs_conta").show();
            moveEye = 1;
        }).mouseout(function () {
            $("#lhqcs_conta").hide();
            moveEye = 0;
        });
        //老化测试
        $("#lhqcs_tyfcs").mouseover(function () {
            $("#lhqcs_tyf_cont1").show();
            moveEye = 1;
        }).mouseout(function () {
            $("#ljcs_conta").hide();
            $("#lhqcs_tyf_cont1").hide();
            moveEye = 0;
        });
        $("#lhqcs_tyf_cont1").mouseover(function () {
            $("#lhqcs_tyf_cont1").show();
            $("#lhqcs_conta").show();

            moveEye = 1;
        }).mouseout(function () {
            $("#ljcs_conta").hide();
            $("#lhqcs_conta").hide();
            $("#lhqcs_tyf_cont1").hide();
            moveEye = 0;
        });

        if (moveEye == 0) {
            $(".lhqcs").mouseout(function () {
                $("#lhqcs_conta").hide();
            });
        }

    });
});




//老化后测试鼠标移入
$(function (){
    //鼠标的移入移出
    $("#lhhcs").mouseover(function () {
        $("#lhhcs_conta").show();
        $("#ljcs_conta").hide();
        $("#ljzp_conta").hide();
        $("#lhqcs_conta").hide();
        $("#zj_conta").hide();
        $("#mpfj_conta").hide();
        $("#lhqcs_tyf_cont1").hide();
        $("#lhhcs_conta").mouseover(function () {
            $("#lhhcs_conta").show();
            moveEye = 1;
        }).mouseout(function () {
            $("#ljcs_conta").hide();
            $("#ljzp_conta").hide();
            $("#lhqcs_conta").hide();
            $("#lhhcs_conta").hide();
        });
        //老化后测试
        $("#lhhcs_cs").mouseover(function () {
            $("#lhhcs_lhh_cont1").show();
            moveEye = 1;
        }).mouseout(function () {
            $("#lhhcs_conta").hide();
            $("#lhhcs_lhh_cont1").hide();
            moveEye = 0;
        });
        $("#lhhcs_lhh_cont1").mouseover(function () {
            $("#lhhcs_lhh_cont1").show();
            $("#lhhcs_conta").show();
            $(".lhhcs_lhh_cont1").show();
            moveEye = 1;
        }).mouseout(function () {
            $("#lhhcs_conta").hide();
            $("#lhhcs_lhh_cont1").hide();
            moveEye = 0;
        });
        //低压启动测试
        $("#lhhcs_dycs").mouseover(function () {
            $("#lhhcs_dy_cont2").show();
            moveEye = 1;
        }).mouseout(function () {
            $("#lhhcs_conta").hide();
            $("#lhhcs_dy_cont2").hide();
            moveEye = 0;
        });
        $("#lhhcs_dy_cont2").mouseover(function () {
            $("#lhhcs_dy_cont2").show();
            $("#lhhcs_conta").show();
            $(".lhhcs_dy_cont2").show();
            moveEye = 1;
        }).mouseout(function () {
            $("#lhhcs_conta").hide();
            $("#lhhcs_dy_cont2").hide();
            moveEye = 0;
        });
        //拧螺丝
        $("#lhhcs_nls").mouseover(function () {
            $("#lhhcs_nls_cont3").show();
            moveEye = 1;
        }).mouseout(function () {
            $("#lhhcs_conta").hide();
            $("#lhhcs_nls_cont3").hide();
            moveEye = 0;
        });
        $("#lhhcs_nls_cont3").mouseover(function () {
            $("#lhhcs_nls_cont3").show();
            $("#lhhcs_conta").show();
            $(".lhhcs_nls_cont3").show();
            moveEye = 1;
        }).mouseout(function () {
            $("#lhhcs_conta").hide();
            $("#lhhcs_nls_cont3").hide();
            moveEye = 0;
        });
        if (moveEye == 0) {
            $(".lhhcs").mouseout(function () {
                $("#lhhcs_conta").hide();
            });
        }
    });
})




//终检鼠标移入
$(function (){
    //鼠标的移入移出
    $("#zj").mouseover(function () {
        $("#zj_conta").show();
        $("#ljcs_conta").hide();
        $("#ljzp_conta").hide();
        $("#lhqcs_conta").hide();
        $("#lhhcs_conta").hide();
        $("#mpfj_conta").hide();
        $("#zj_conta").mouseover(function () {
            $("#zj_conta").show();
            moveEye = 1;
        }).mouseout(function () {
            $("#ljcs_conta").hide();
            $("#ljzp_conta").hide();
            $("#lhqcs_conta").hide();
            $("#lhhcs_conta").hide();
            $("#zj_conta").hide();
        });
        $("#zj_zp").mouseover(function () {
            $("#zj_contz").show();
            moveEye = 1;
        }).mouseout(function () {
            $("#zj_conta").hide();
            $("#zj_contz").hide();
            moveEye = 0;
        });
        $("#zj_contz").mouseover(function () {
            $("#zj_contz").show();
            $("#zj_conta").show();
            moveEye = 1;
        }).mouseout(function () {
            $("#zj_conta").hide();
            $("#zj_contz").hide();
            moveEye = 0;
        });

        $("#zj_csa").mouseover(function () {
            $("#zj_cont_cs").show();
            moveEye = 1;
        }).mouseout(function () {
            $("#zj_conta").hide();
            $("#zj_cont_cs").hide();
            moveEye = 0;
        });
        $("#zj_cont_cs").mouseover(function () {
            $("#zj_cont_cs").show();
            $("#zj_conta").show();
            moveEye = 1;
        }).mouseout(function () {
            $("#zj_conta").hide();
            $("#zj_cont_cs").hide();
            moveEye = 0;
        });
      if (moveEye == 0) {
            $(".zj").mouseout(function () {
                $("#zj_conta").hide();
            });
        }
    });
})



//铭牌附件鼠标移入
$(function (){
    //鼠标的移入移出
    $(".mpfj").mouseover(function () {
        $("#mpfj_conta").show();
        $("#ljcs_conta").hide();
        $("#ljzp_conta").hide();
        $("#lhqcs_conta").hide();
        $("#lhhcs_conta").hide();
        $("#zj_conta").hide();
        $("#mpfj_conta").mouseover(function () {
            $("#mpfj_conta").show();
            moveEye = 1;
        }).mouseout(function () {
            $("#mpfj_conta").hide();
            $("#ljcs_conta").hide();
            $("#ljzp_conta").hide();
            $("#lhqcs_conta").hide();
            $("#lhhcs_conta").hide();
            $("#zj_conta").hide();
        });
        //外壳标签
        $("#mpfj_wkbq").mouseover(function () {
            $("#mpfj_cont_wk1").show();
            moveEye = 1;
        }).mouseout(function () {
            $("#mpfj_conta").hide();
            $("#mpfj_cont_wk1").hide();
            moveEye = 0;
        });
        $("#mpfj_cont_wk1").mouseover(function () {
            $("#mpfj_cont_wk1").show();
            $("#mpfj_conta").show();
            $(".mpfj_cont_wk1").show();
            moveEye = 1;
        }).mouseout(function () {
            $("#mpfj_conta").hide();
            $("#mpfj_cont_wk1").hide();
            moveEye = 0;
        });
        //附件
        $("#mpfj_fj").mouseover(function () {
            $("#mpfj_cont_fj2").show();
            moveEye = 1;
        }).mouseout(function () {
            $("#mpfj_conta").hide();
            $("#mpfj_cont_fj2").hide();
            moveEye = 0;
        });
        $("#mpfj_cont_fj2").mouseover(function () {
            $("#mpfj_cont_fj2").show();
            $("#mpfj_conta").show();
            $(".mpfj_cont_fj2").show();
            moveEye = 1;
        }).mouseout(function () {
            $("#mpfj_conta").hide();
            $("#mpfj_cont_fj2").hide();
            moveEye = 0;
        });
        //包装
        $("#mpfj_bz").mouseover(function () {
            $("#mpfj_cont_bz3").show();
            moveEye = 1;
        }).mouseout(function () {
            $("#mpfj_conta").hide();
            $("#mpfj_cont_bz3").hide();
            moveEye = 0;
        });
        $("#mpfj_cont_bz3").mouseover(function () {
            $("#mpfj_cont_bz3").show();
            $("#mpfj_conta").show();
            $(".mpfj_cont_bz3").show();
            moveEye = 1;
        }).mouseout(function () {
            $("#mpfj_conta").hide();
            $("#mpfj_cont_bz3").hide();
            moveEye = 0;
        });
        if (moveEye == 0) {
            $(".mpfj").mouseout(function () {
                $("#mpfj_conta").hide();
            });
        }
    });
})
















