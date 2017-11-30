/**
 * Created by Administrator on 2017/7/13.
 */
$(function(){

//饼状图
    var categories = ['上线率', '未上线率'],
        data = [{
            drilldown: {
                name: '',
                categories: ['上线率', '未上线率'],
                data: [30, 70],   //数据，即this.y
            }
        }];

    // 创建数组
    var fuhuiData = [];
    var percentData = [];
    for (var i = 0; i < data.length; i++) {
        // 添加名称
        fuhuiData.push({
            name: categories[i],
            y: data[i].y,
        });

        // 添加百分比
        for (var j = 0; j < data[i].drilldown.data.length; j++) {
            var brightness = 0.2 - (j / data[i].drilldown.data.length) / 5 ;
            percentData.push({
                name: data[i].drilldown.categories[j],
                y: data[i].drilldown.data[j],
            });
        }
    }

    // 创建图表
    $('#container2').highcharts({
        chart: {
            type: 'pie'   //图表的类型
        },
        title: {  //设置标题并将标题置于环形图表中间
            text:'<span style="font-size:42px;color:#000000;">FN1231235287456321478</span>',
            verticalAlign: 'middle',
        },
        yAxis: {
            title: {
                text: ''
            }
        },
        plotOptions: {
            pie: {
                size: '100%',
                innerSize: '70%',   //配置环形大小
                shadow: false,
                center: ['50%','52%'],  //水平和垂直方向居中
                colors: [            //设置饼状图的颜色
                    '#00CC33',  //第一个颜色
                    '#FF6633',  //第二个颜色
                ],
                dataLabels: {
                    /*connectorColor: '#000000',*/  //设置连接线的颜色
                    style: {  //设置标识文字的样式
                        color: '#000000',
                        fontSize: '65px',
                        fontWeight: 'normal',   //字体不加粗
                    },
                },
                /*showInLegend: true*/
            }
        },
        tooltip: {
            valueSuffix: '%'
        },
        series: [{
            name: '百分比',  //数据列名
            data: percentData,
            dataLabels: {
                formatter: function() {
                    // display only if larger than 1
                    //return this.y > 1 ? '<b>'+ this.point.name +':</b> '+ this.y +'%'  : null;    //这串代码设置了加粗
                    return this.y > 1 ? ''+ this.point.name +': '+ this.y +'%'  : null;
                }
            }
        }],
        credits: {
            enabled:false,                    // 默认值，如果想去掉版权信息Highcharts.com，设置为false即可
        }
    });

    //设置饼状图中间文字的上下间隔
    $(".highcharts-title").find("tspan").last().attr("dy",50);

})