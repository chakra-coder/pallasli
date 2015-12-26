<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<html>
<head>


	<script type="text/javascript" src="../pallasli-web-js-css/scripts/include-ext.js?theme=classic"></script>
	  
    <script src="../pallasli-web-js-css/scripts/echarts-2.0.0/asset/js/esl/esl.js"></script>
    <script src="../pallasli-web-js-css/scripts/echarts-2.0.0/asset/js/codemirror.js"></script>
    
	<script type="text/javascript" src="../pallasli-web-js-css/scripts/echart/echartsConfig.js"></script>
	<script type="text/javascript" src="../pallasli-web-js-css/scripts/echart/Atwasoft.chart.js"></script>
 
    
    
    <script>
			Ext.Loader.setPath({
		        'Pallas': '/pallasli-web-js-css/scripts/echart'
		    }); 
			Ext.onReady(function(){ 
				var pieChart=Ext.create("Pallas.Chart",{
					type:'pie',
					title : {
				        text: "市场比例",
				        subtext: "饼图",
				        x:'center'
				    },
					legend: {
					    orient : 'vertical',
					    x : 'left',
					    data:['直达','营销广告','搜索引擎','邮件营销','联盟广告','视频广告','百度','谷歌','必应','其他']
					},
					
					    series : [
					        {
					            name:'访问来源',
					            type:'pie',
					            center : ['35%', 200],
					            radius : 80,
					            itemStyle : {
					                normal : {
					                    label : {
					                        position : 'inner',
					                        formatter : function (a,b,c,d) {
					                        	return ((d - 0).toFixed(0) + '%');
					                        }
					                    },
					                    labelLine : {
					                        show : false
					                    }
					                },
					                emphasis : {
					                    label : {
					                        show : true,
					                        formatter : "{b}\n{d}%"
					                    }
					                }
					                
					            },
					            data:[
					                {value:335, name:'直达'},
					                {value:679, name:'营销广告'},
					                {value:1548, name:'搜索引擎'}
					            ]
					        },
					        {
					            name:'访问来源',
					            type:'pie',
					            center : ['35%', 200],
					            radius : [110, 140],
					            data:[
					                {value:335, name:'直达'},
					                {value:310, name:'邮件营销'},
					                {value:234, name:'联盟广告'},
					                {value:135, name:'视频广告'},
					                {
					                    value:1048,
					                    name:'百度',
					                    itemStyle : {
					                        normal : {
					                            color : (function (){
					                             //   var zrColor = require('zrender/tool/color');
					                             //   return zrColor.getRadialGradient(
					                             //       300, 200, 110, 300, 200, 140,
					                              //      [[0, 'rgba(255,255,0,1)'],[1, 'rgba(30,144,250,1)']]
					                              //  )
					                            })(),
					                            label : {
					                                textStyle : {
					                                    color : 'rgba(30,144,255,0.8)',
					                                    align : 'center',
					                                    baseline : 'middle',
					                                    fontFamily : '微软雅黑',
					                                    fontSize : 30,
					                                    fontWeight : 'bolder'
					                                }
					                            },
					                            labelLine : {
					                                length : 40,
					                                lineStyle : {
					                                    color : '#f0f',
					                                    width : 3,
					                                    type : 'dotted'
					                                }
					                            }
					                        }
					                    }
					                },
					                {value:251, name:'谷歌'},
					                {
					                    value:102,
					                    name:'必应',
					                    itemStyle : {
					                        normal : {
					                            label : {
					                                show : false
					                            },
					                            labelLine : {
					                                show : false
					                            }
					                        },
					                        emphasis : {
					                            label : {
					                                show : true
					                            },
					                            labelLine : {
					                                show : true,
					                                length : 50
					                            }
					                        }
					                    }
					                },
					                {value:147, name:'其他'}
					            ]
					        },
					        {
					            name:'访问来源',
					            type:'pie',
					            startAngle: 90,
					            center : ['75%', 200],
					            radius : [80, 120],
					            itemStyle : {
					                normal : {
					                    label : {
					                        show : false
					                    },
					                    labelLine : {
					                        show : false
					                    }
					                },
					                emphasis : {
					                    color: (function (){
					                      //  var zrColor = require('zrender/tool/color');
					                      //  return zrColor.getRadialGradient(
					                       //     650, 200, 80, 650, 200, 120,
					                       //     [[0, 'rgba(255,255,0,1)'],[1, 'rgba(255,0,0,1)']]
					                      //  )
					                    })(),
					                    label : {
					                        show : true,
					                        position : 'center',
					                        formatter : "{d}%",
					                        textStyle : {
					                            color : 'red',
					                            fontSize : '30',
					                            fontFamily : '微软雅黑',
					                            fontWeight : 'bold'
					                        }
					                    }
					                }
					            },
					            data:[
					                {value:335, name:'直达'},
					                {value:310, name:'邮件营销'},
					                {value:234, name:'联盟广告'},
					                {value:135, name:'视频广告'},
					                {value:1548, name:'搜索引擎'}
					            ],
					            markPoint : {
					                symbol: 'emptyHeart',
					                data : [
					                    {name : '打酱油的标注', value : 100, x:'50%', y:'15%', symbolSize:32}
					                ]
					            }
					        }
					    ]
				});
				var lineChart=Ext.create("Pallas.Chart",{type:'line',
					title : {
				        text: '市场比例',
				        subtext: '线图'
				    },
				    tooltip : {
				        trigger: 'axis'
				    },
				    legend: {
				        data:['邮件营销','联盟广告','直接访问','搜索引擎']
				    },
				    toolbox: {
				        show : true,
				        feature : {
				            mark : {show: true},
				            dataView : {show: true, readOnly: false},
				            magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
				            restore : {show: true},
				            saveAsImage : {show: true}
				        }
				    },
				    calculable : true,
				    xAxis : [
				        {
				            type : 'category',
				            boundaryGap : false,
				            data : ['周一','周二','周三','周四','周五','周六','周日']
				        }
				    ],
				    yAxis : [
				        {
				            type : 'value',
				            splitArea : {show : true}
				        }
				    ],
				    series : [
				        {
				            name:'邮件营销',
				            type:'line',
				            stack: '总量',
				            symbol: 'none',
				            itemStyle: {
				                normal: {
				                    areaStyle: {
				                        // 区域图，纵向渐变填充
				                        color : (function (){
				                           // var zrColor = require('zrender/tool/color');
				                          //  return zrColor.getLinearGradient(
				                           //     0, 200, 0, 400,
				                           //     [[0, 'rgba(255,0,0,0.8)'],[0.8, 'rgba(255,255,255,0.1)']]
				                           // )
				                        })()
				                    }
				                }
				            },
				            data:[
				                120, 132, 301, 134, 
				                {value:90, symbol:'droplet',symbolSize:5},
				                230, 210
				            ]
				        },
				        {
				            name:'联盟广告',
				            type:'line',
				            stack: '总量',
				            smooth: true,
				            symbol: 'image://../pallasli-web-js-css/scripts/echarts-2.0.0/asset/ico/favicon.png',     // 系列级个性化拐点图形
				            symbolSize: 8,
				            data:[
				                120, 82,
				                {
				                    value:201,
				                    symbol: 'star',  // 数据级个性化拐点图形
				                    symbolSize : 15,
				                    itemStyle : { normal: {label : {
				                        show: true,
				                        textStyle : {
				                            fontSize : '20',
				                            fontFamily : '微软雅黑',
				                            fontWeight : 'bold'
				                        }
				                    }}}
				                },
				                {
				                    value:134,
				                    symbol: 'none'
				                },
				                190, 
				                {
				                    value : 230,
				                    symbol: 'emptypin',
				                    symbolSize: 8
				                },
				                110
				            ]
				        },
				        {
				            name:'直接访问',
				            type:'line',
				            stack: '总量',
				            symbol: 'arrow',
				            symbolSize: 6,
				            symbolRotate: -45,
				            itemStyle: {
				                normal: {
				                    color: 'red',
				                    lineStyle: {        // 系列级个性化折线样式
				                        width: 2,
				                        type: 'dashed'
				                    }
				                },
				                emphasis: {
				                    color: 'blue'
				                }
				            },
				            data:[
				                320, 332, '-', 334,
				                {
				                    value: 390,
				                    symbol: 'star6',
				                    symbolSize : 20,
				                    symbolRotate : 10,
				                    itemStyle: {        // 数据级个性化折线样式
				                        normal: {
				                            color: 'yellowgreen'
				                        },
				                        emphasis: {
				                            color: 'orange',
				                            label : {
				                                show: true,
				                                position: 'inside',
				                                textStyle : {
				                                    fontSize : '20'
				                                }
				                            }
				                        }
				                    }
				                },
				                330, 320
				            ]
				        },
				        {
				            name:'搜索引擎',
				            type:'line',
				            symbol:'emptyCircle',
				            itemStyle: {
				                normal: {
				                    lineStyle: {            // 系列级个性化折线样式，横向渐变描边
				                        width: 2,
				                        color: (function (){
				                            //var zrColor = require('zrender/tool/color');
				                           // return zrColor.getLinearGradient(
				                           //     0, 0, 1000, 0,
				                           //     [[0, 'rgba(255,0,0,0.8)'],[0.8, 'rgba(255,255,0,0.8)']]
				                           // )
				                        })(),
				                        shadowColor : 'rgba(0,0,0,0.5)',
				                        shadowBlur: 10,
				                        shadowOffsetX: 8,
				                        shadowOffsetY: 8
				                    }
				                },
				                emphasis : {
				                    label : {show: true}
				                }
				            },
				            data:[
				                620, 732, 791, 
				                {value:734, symbol:'emptyHeart',symbolSize:10},
				                890, 930, 820
				            ],
				            markPoint : {
				                data : [
				                    {name : '周最高', value : 930, xAxis: '周六', yAxis: 935}
				                ]
				            }
				        }
				    ]
				    });
				var barChart=Ext.create("Pallas.Chart",{type:'bar',
					title : {
				        text: '市场比例',
				        subtext: '饼图'
				    },
					tooltip : {
				        show: true,
				        trigger: 'item'
				    },
				    legend: {
				        data:['邮件营销','联盟广告','直接访问','搜索引擎']
				    },
				    toolbox: {
				        show : true,
				        feature : {
				            mark : {show: true},
				            dataView : {show: true, readOnly: false},
				            magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
				            restore : {show: true},
				            saveAsImage : {show: true}
				        }
				    },
				    calculable : true,
				    xAxis : [
				        {
				            type : 'category',
				            data : ['周一','周二','周三','周四','周五','周六','周日']
				        }
				    ],
				    yAxis : [
				        {
				            type : 'value',
				            splitArea : {show : true}
				        }
				    ],
				    series : [
				        {
				            name:'邮件营销',
				            type:'bar',
				            itemStyle: {        // 系列级个性化样式，纵向渐变填充
				                normal: {
				                    borderColor:'red',
				                    color : (function (){
				                      //  var zrColor = require('zrender/tool/color');
				                      //  return zrColor.getLinearGradient(
				                      //      0, 400, 0, 300,
				                      //      [[0, 'green'],[1, 'yellow']]
				                      //  )
				                    })()
				                },
				                emphasis: {
				                    borderWidth: 5,
				                    borderColor:'green',
				                    color: (function (){
				                       // var zrColor = require('zrender/tool/color');
				                       // return zrColor.getLinearGradient(
				                       //     0, 400, 0, 300,
				                       //     [[0, 'red'],[1, 'orange']]
				                       // )
				                    })(),
				                    label : {
				                        show : true,
				                        position : 'top',
				                        formatter : "{a} {b} {c}",
				                        textStyle : {
				                            color: 'blue'
				                        }
				                    }
				                }
				            },
				            data:[220, 232, 101, 234, 190, 330, 210]
				        },
				        {
				            name:'联盟广告',
				            type:'bar',
				            stack: '总量',
				            data:[120, '-', 451, 134, 190, 230, 110]
				        },
				        {
				            name:'直接访问',
				            type:'bar',
				            stack: '总量',
				            itemStyle: {                // 系列级个性化
				                normal: {
				                    borderWidth: 6,
				                    borderColor:'tomato',
				                    color: 'red'
				                },
				                emphasis: {
				                    borderColor:'red',
				                    color: 'blue'
				                }
				            },
				            data:[
				                320, 332, 100, 334,
				                {
				                    value: 390,
				                    symbolSize : 10,   // 数据级个性化
				                    itemStyle: {
				                        normal: {
				                            color :'lime'
				                        },
				                        emphasis: {
				                            color: 'skyBlue'
				                        }
				                    }
				                },
				                330, 320
				            ]
				        },
				        {
				            name:'搜索引擎',
				            type:'bar',
				            barWidth: 40,                   // 系列级个性化，柱形宽度
				            itemStyle: {
				                normal: {                   // 系列级个性化，横向渐变填充
				                    borderRadius: 5,
				                    color : (function (){
				                      //  var zrColor = require('zrender/tool/color');
				                      //  return zrColor.getLinearGradient(
				                     //       0, 0, 1000, 0,
				                      //      [[0, 'rgba(30,144,255,0.8)'],[1, 'rgba(138,43,226,0.8)']]
				                       // )
				                    })(),
				                    label : {
				                        show : true,
				                        textStyle : {
				                            fontSize : '20',
				                            fontFamily : '微软雅黑',
				                            fontWeight : 'bold'
				                        }
				                    }
				                }
				            },
				            data:[
				                620, 732, 
				                {
				                    value: 701,
				                    itemStyle : { normal: {label : {position: 'inside'}}}
				                },
				                734, 890, 
				                {
				                    value: 930,
				                    itemStyle : { normal: {label : {show: false}}}
				                },
				                820
				            ],
				            markPoint : {
				                data : [
				                    {name : '最高', value : 930, xAxis: '周六', yAxis: 930, symbolSize:14}
				                ]
				            },
				            markLine : {
				                data : [
				                    [
				                        {type : 'average', name : '平均值'},
				                        {type : 'max'},
				                        {type : 'min'}
				                    ]
				                ]
				            }
				        }
				    ]
				    });
				var mapChart=Ext.create("Pallas.Chart",{type:'map',
					title : {
				        text: 'iphone销量',
				        subtext: '纯属虚构',
				        x:'center'
				    },
				    tooltip : {
				        trigger: 'item'
				    },
				    legend: {
				        orient: 'vertical',
				        x:'left',
				        data:['iphone3','iphone4','iphone5']
				    },
				    dataRange: {
				        min: 0,
				        max: 2500,
				        text:['高','低'],           // 文本，默认为数值文本
				        calculable : true
				    },
				    toolbox: {
				        show : true,
				        orient : 'vertical',
				        x: 'right',
				        y: 'center',
				        feature : {
				            mark : {show: true},
				            dataView : {show: true, readOnly: false},
				            restore : {show: true},
				            saveAsImage : {show: true}
				        }
				    },
				    series : [
				              {
				                  name: 'Map',
				                  type: 'map',
				                  mapLocation: {
				                      x : 'left',
				                      y : 'top'
				                  },
				                  selectedMode: 'multiple',
				                  itemStyle: {
				                      normal: {
				                          borderWidth:2,
				                          borderColor:'lightgreen',
				                          color: 'orange',
				                          label: {
				                              show: false
				                          }
				                      },
				                      emphasis: {                 // 也是选中样式
				                          borderWidth:2,
				                          borderColor:'#fff',
				                          color: '#32cd32',
				                          label: {
				                              show: true,
				                              textStyle: {
				                                  color: '#fff'
				                              }
				                          }
				                      }
				                  },
				                  data:[
				                       {
				                           name: '广东',
				                           value: Math.round(Math.random()*1000),
				                           itemStyle: {
				                              normal: {
				                                  color: '#32cd32',
				                                  label: {
				                                      show: true,
				                                      textStyle: {
				                                          color: '#fff',
				                                          fontSize: 15
				                                      }
				                                  }
				                              },
				                              emphasis: {                 // 也是选中样式
				                                  borderWidth:5,
				                                  borderColor:'yellow',
				                                  color: '#cd5c5c',
				                                  label: {
				                                      show: false,
				                                      textStyle: {
				                                          color: 'blue'
				                                      }
				                                  }
				                              }
				                          }
				                      }
				                  ],
				                  markPoint : {
				                      itemStyle : {
				                          normal:{
				                              color:'skyblue'
				                          }
				                      },
				                      data : [
				                          {name : '天津', value : 350},
				                          {name : '上海', value : 103}
				                      ]
				                  },
				                  geoCoord: {
				                      '上海': [121.4648,31.2891],
				                      '天津': [117.4219,39.4189]
				                  }
				              }
				          ]
				    });

				var chordChart=Ext.create("Pallas.Chart",{
					type:"chord",
					 title : {
					        text: '测试数据'
					    },
					    tooltip : {
					        trigger: 'item'
					    },
					    legend: {
					        x: 'right',
					        data:['g1','g2', 'g3', 'g4']
					    },
					    series : [
					        {
					            type:'chord',
					            radius : ['55%', '75%'],
					            center : ['50%', '50%'],
					            padding : 2,
					            sort : 'descending', // can be 'none', 'ascending', 'descending'
					            sortSub : 'descending', // can be 'none', 'ascending', 'descending'
					            startAngle : 90,
					            clockWise : false,
					            showScale : true,
					            showScaleText : true,
					            itemStyle : {
					                normal : {
					                    lineStyle : {
					                        width : 0,
					                        color : '#000'
					                    },
					                    chordStyle : {
					                        lineStyle : {
					                            width : 1,
					                            color : '#333'
					                        }
					                    },
					                    label: {
					                        show: true,
					                        color: 'red'
					                    }
					                },
					                emphasis : {
					                    lineStyle : {
					                        width : 0,
					                        color : '#000'
					                    },
					                    chordStyle : {
					                        lineStyle : {
					                            width : 2,
					                            color : 'black'
					                        }
					                    }
					                }
					            },
					            data : [
					                {
					                    name : 'g1',
					                    itemStyle : {
					                        normal: {
					                            color: 'rgba(255,30,30,0.5)',
					                            lineStyle : {
					                                width: 1,
					                                color: 'green'
					                            }
					                        },
					                        emphasis: {
					                            color: 'yellow',
					                            lineStyle : {
					                                width: 2,
					                                color: 'blue'
					                            }
					                        }
					                    }
					                },
					                {name : 'g2'},
					                {name : 'g3'},
					                {name : 'g4'}
					            ],
					            matrix : [
					                [11975,  5871, 8916, 2868],
					                [ 1951, 10048, 2060, 6171],
					                [ 8010, 16145, 8090, 8045],
					                [ 1013,   990,  940, 6907]
					            ],
					            markPoint : {
					                symbol: 'star',
					                data : [
					                    {name : '打酱油的标注', value : 100, x:'5%', y:'50%', symbolSize:32},
					                    {name : '打酱油的标注', value : 100, x:'95%', y:'50%', symbolSize:32}
					                ]
					            }
					        }
					    ]
				});
				var scatterChart=Ext.create("Pallas.Chart",{type:'scatter',
					 tooltip : {
					        trigger: 'item'
					    },
					    legend: {
					        data:['scatter1','scatter2']
					    },
					    toolbox: {
					        show : true,
					        feature : {
					            mark : {show: true},
					            dataView : {show: true, readOnly: false},
					            restore : {show: true},
					            saveAsImage : {show: true}
					        }
					    },
					    calculable : true,
					    xAxis : [
					        {
					            type : 'value'
					        }
					    ],
					    yAxis : [
					        {
					            type : 'value',
					            splitArea : {show : true}
					        }
					    ],
					    series : [
					        {
					            name:'scatter1',
					            type:'scatter',
					            symbol: 'emptyCircle', //'circle', 'rectangle', 'triangle', 'diamond', 'emptyCircle', 'emptyRectangle', 'emptyTriangle', 'emptyDiamond'
					            symbolSize: function (value){
					                if (value[0] < 2) {
					                    return 2;
					                }
					                else if (value[0] < 8) {
					                    return Math.round(value[2] * 3);
					                }
					                else {
					                    return 20;
					                }
					            },
					            itemStyle: {
					                normal: {
					                    color: 'lightblue',
					                    borderWidth: 4,
					                    label : {show: true}
					                },
					                emphasis: {
					                    color: 'lightgreen'
					                }
					            },
					            data: (function () {
					                var d = [];
					                var len = 20;
					                while (len--) {
					                    d.push([
					                        (Math.random()*10).toFixed(2) - 0,
					                        (Math.random()*10).toFixed(2) - 0,
					                        (Math.random()*10).toFixed(2) - 0
					                    ]);
					                }
					                return d;
					            })()
					        },
					        {
					            name:'scatter2',
					            type:'scatter',
					            symbol: 'image://../pallasli-web-js-css/scripts/echarts-2.0.0/asset/ico/favicon.png',     // 系列级个性化拐点图形
					            symbolSize: function (value){
					                return Math.round(value[2] * 3);
					            },
					            itemStyle: {
					                emphasis : {
					                    label : {show: true}
					                }
					            },
					            data: (function () {
					                var d = [];
					                var len = 20;
					                while (len--) {
					                    d.push([
					                        (Math.random()*10).toFixed(2) - 0,
					                        (Math.random()*10).toFixed(2) - 0,
					                        (Math.random()*10).toFixed(2) - 0
					                    ]);
					                }
					                d.push({
					                    value : [5,5,1000],
					                    itemStyle: {
					                        normal: {
					                            borderWidth: 8,
					                            color: 'orange'
					                        },
					                        emphasis: {
					                            borderWidth: 10,
					                            color: '#ff4500'
					                        }
					                    },
					                    symbol: 'emptyTriangle',
					                    symbolRotate:90,
					                    symbolSize:30
					                });
					                return d;
					            })(),
					            markPoint : {
					                symbol: 'emptyCircle',
					                itemStyle:{
					                    normal:{label:{position:'top'}}
					                },
					                data : [
					                    {name : '有个东西', value : 1000, xAxis: 5, yAxis: 5, symbolSize:80}
					                ]
					            }
					        }
					    ]
				});
				var kChart=Ext.create("Pallas.Chart",{type:'k',
					tooltip : {
				        trigger: 'axis',
				        formatter: function (params) {
				            var res = params[0][1];
				            res += '<br/>' + params[0][0];
				            res += '<br/>  开盘 : ' + params[0][2][0] + '  最高 : ' + params[0][2][3];
				            res += '<br/>  收盘 : ' + params[0][2][1] + '  最低 : ' + params[0][2][2];
				            return res;
				        }
				    },
				    legend: {
				        data:['上证指数']
				    },
				    toolbox: {
				        show : true,
				        feature : {
				            mark : {show: true},
				            dataZoom : {show: true},
				            dataView : {show: true, readOnly: false},
				            restore : {show: true},
				            saveAsImage : {show: true}
				        }
				    },
				    dataZoom : {
				        show : true,
				        realtime: true,
				        start : 0,
				        end : 50
				    },
				    xAxis : [
				        {
				            type : 'category',
				            boundaryGap : true,
				            axisTick: {onGap:false},
				            splitLine: {show:false},
				            data : [
				                "2013/1/24", "2013/1/25", "2013/1/28", "2013/1/29", "2013/1/30",
				                "2013/1/31", "2013/2/1", "2013/2/4", "2013/2/5", "2013/2/6", 
				                "2013/2/7", "2013/2/8", "2013/2/18", "2013/2/19", "2013/2/20", 
				                "2013/2/21", "2013/2/22", "2013/2/25", "2013/2/26", "2013/2/27", 
				                "2013/2/28", "2013/3/1", "2013/3/4", "2013/3/5", "2013/3/6", 
				                "2013/3/7", "2013/3/8", "2013/3/11", "2013/3/12", "2013/3/13", 
				                "2013/3/14", "2013/3/15", "2013/3/18", "2013/3/19", "2013/3/20", 
				                "2013/3/21", "2013/3/22", "2013/3/25", "2013/3/26", "2013/3/27", 
				                "2013/3/28", "2013/3/29", "2013/4/1", "2013/4/2", "2013/4/3", 
				                "2013/4/8", "2013/4/9", "2013/4/10", "2013/4/11", "2013/4/12", 
				                "2013/4/15", "2013/4/16", "2013/4/17", "2013/4/18", "2013/4/19", 
				                "2013/4/22", "2013/4/23", "2013/4/24", "2013/4/25", "2013/4/26", 
				                "2013/5/2", "2013/5/3", "2013/5/6", "2013/5/7", "2013/5/8", 
				                "2013/5/9", "2013/5/10", "2013/5/13", "2013/5/14", "2013/5/15", 
				                "2013/5/16", "2013/5/17", "2013/5/20", "2013/5/21", "2013/5/22", 
				                "2013/5/23", "2013/5/24", "2013/5/27", "2013/5/28", "2013/5/29", 
				                "2013/5/30", "2013/5/31", "2013/6/3", "2013/6/4", "2013/6/5", 
				                "2013/6/6", "2013/6/7", "2013/6/13"
				            ]
				        }
				    ],
				    yAxis : [
				        {
				            type : 'value',
				            scale:true,
				            precision: 2,
				            splitNumber: 9,
				            boundaryGap: [0.05, 0.05],
				            splitArea : {show : true}
				        }
				    ],
				    series : [
				        {
				            name:'上证指数',
				            type:'k',
				            barMaxWidth: 20,
				            itemStyle: {
				                normal: {
				                    color: 'red',           // 阳线填充颜色
				                    color0: 'lightgreen',   // 阴线填充颜色
				                    lineStyle: {
				                        width: 2,
				                        color: 'orange',    // 阳线边框颜色
				                        color0: 'green'     // 阴线边框颜色
				                    }
				                },
				                emphasis: {
				                    color: 'black',         // 阳线填充颜色
				                    color0: 'white'         // 阴线填充颜色
				                }
				            },
				            data:[ // 开盘，收盘，最低，最高
				                {
				                    value:[2320.26,2302.6,2287.3,2362.94],
				                    itemStyle: {
				                        normal: {
				                            color0: 'blue',         // 阴线填充颜色
				                            lineStyle: {
				                                width: 3,
				                                color0: 'blue'      // 阴线边框颜色
				                            }
				                        },
				                        emphasis: {
				                            color0: 'blue'          // 阴线填充颜色
				                        }
				                    }
				                },
				                [2300,2291.3,2288.26,2308.38],
				                [2295.35,2346.5,2295.35,2346.92],
				                [2347.22,2358.98,2337.35,2363.8],
				                [2360.75,2382.48,2347.89,2383.76],
				                [2383.43,2385.42,2371.23,2391.82],
				                [2377.41,2419.02,2369.57,2421.15],
				                [2425.92,2428.15,2417.58,2440.38],
				                [2411,2433.13,2403.3,2437.42],
				                [2432.68,2434.48,2427.7,2441.73],
				                [2430.69,2418.53,2394.22,2433.89],
				                [2416.62,2432.4,2414.4,2443.03],
				                [2441.91,2421.56,2415.43,2444.8],
				                [2420.26,2382.91,2373.53,2427.07],
				                [2383.49,2397.18,2370.61,2397.94],
				                [2378.82,2325.95,2309.17,2378.82],
				                [2322.94,2314.16,2308.76,2330.88],
				                [2320.62,2325.82,2315.01,2338.78],
				                [2313.74,2293.34,2289.89,2340.71],
				                [2297.77,2313.22,2292.03,2324.63],
				                [2322.32,2365.59,2308.92,2366.16],
				                [2364.54,2359.51,2330.86,2369.65],
				                [2332.08,2273.4,2259.25,2333.54],
				                [2274.81,2326.31,2270.1,2328.14],
				                [2333.61,2347.18,2321.6,2351.44],
				                [2340.44,2324.29,2304.27,2352.02],
				                [2326.42,2318.61,2314.59,2333.67],
				                [2314.68,2310.59,2296.58,2320.96],
				                [2309.16,2286.6,2264.83,2333.29],
				                [2282.17,2263.97,2253.25,2286.33],
				                [2255.77,2270.28,2253.31,2276.22],
				                [2269.31,2278.4,2250,2312.08],
				                [2267.29,2240.02,2239.21,2276.05],
				                [2244.26,2257.43,2232.02,2261.31],
				                [2257.74,2317.37,2257.42,2317.86],
				                [2318.21,2324.24,2311.6,2330.81],
				                [2321.4,2328.28,2314.97,2332],
				                [2334.74,2326.72,2319.91,2344.89],
				                [2318.58,2297.67,2281.12,2319.99],
				                [2299.38,2301.26,2289,2323.48],
				                [2273.55,2236.3,2232.91,2273.55],
				                [2238.49,2236.62,2228.81,2246.87],
				                [2229.46,2234.4,2227.31,2243.95],
				                [2234.9,2227.74,2220.44,2253.42],
				                [2232.69,2225.29,2217.25,2241.34],
				                [2196.24,2211.59,2180.67,2212.59],
				                [2215.47,2225.77,2215.47,2234.73],
				                [2224.93,2226.13,2212.56,2233.04],
				                [2236.98,2219.55,2217.26,2242.48],
				                [2218.09,2206.78,2204.44,2226.26],
				                [2199.91,2181.94,2177.39,2204.99],
				                [2169.63,2194.85,2165.78,2196.43],
				                [2195.03,2193.8,2178.47,2197.51],
				                [2181.82,2197.6,2175.44,2206.03],
				                [2201.12,2244.64,2200.58,2250.11],
				                [2236.4,2242.17,2232.26,2245.12],
				                [2242.62,2184.54,2182.81,2242.62],
				                [2187.35,2218.32,2184.11,2226.12],
				                [2213.19,2199.31,2191.85,2224.63],
				                [2203.89,2177.91,2173.86,2210.58],
				                [2170.78,2174.12,2161.14,2179.65],
				                [2179.05,2205.5,2179.05,2222.81],
				                [2212.5,2231.17,2212.5,2236.07],
				                [2227.86,2235.57,2219.44,2240.26],
				                [2242.39,2246.3,2235.42,2255.21],
				                [2246.96,2232.97,2221.38,2247.86],
				                [2228.82,2246.83,2225.81,2247.67],
				                [2247.68,2241.92,2231.36,2250.85],
				                [2238.9,2217.01,2205.87,2239.93],
				                [2217.09,2224.8,2213.58,2225.19],
				                [2221.34,2251.81,2210.77,2252.87],
				                [2249.81,2282.87,2248.41,2288.09],
				                [2286.33,2299.99,2281.9,2309.39],
				                [2297.11,2305.11,2290.12,2305.3],
				                [2303.75,2302.4,2292.43,2314.18],
				                [2293.81,2275.67,2274.1,2304.95],
				                [2281.45,2288.53,2270.25,2292.59],
				                [2286.66,2293.08,2283.94,2301.7],
				                [2293.4,2321.32,2281.47,2322.1],
				                [2323.54,2324.02,2321.17,2334.33],
				                [2316.25,2317.75,2310.49,2325.72],
				                [2320.74,2300.59,2299.37,2325.53],
				                [2300.21,2299.25,2294.11,2313.43],
				                [2297.1,2272.42,2264.76,2297.1],
				                [2270.71,2270.93,2260.87,2276.86],
				                [2264.43,2242.11,2240.07,2266.69],
				                [2242.26,2210.9,2205.07,2250.63],
				                [2190.1,2148.35,2126.22,2190.1]
				            ],
				            markPoint : {
				                symbol: 'star',
				                //symbolSize:20,
				                itemStyle:{
				                    normal:{label:{position:'top'}}
				                },
				                data : [
				                    {name : '最高', value : 2444.8, xAxis: '2013/2/18', yAxis: 2466}
				                ]
				            }
				        }
				    ]
				
				});
				var forceChart=Ext.create("Pallas.Chart",{type:'force',
					title : {
				        text: '人物关系：乔布斯',
				        subtext: '数据来自人立方',
				        x:'right',
				        y:'bottom'
				    },
				    tooltip : {
				        trigger: 'item',
				        formatter: '{a} : {b}'
				    },
				    legend: {
				        x: 'left',
				        data:['家人','朋友']
				    },
				    series : [
				        {
				            type:'force',
				            name : "人物关系",
				            categories : [
				                {
				                    name: '人物',
				                    itemStyle: {
				                        normal: {
				                            color : '#ff7f50'
				                        }
				                    }
				                },
				                {
				                    name: '家人',
				                    itemStyle: {
				                        normal: {
				                            color : '#87cdfa'
				                        }
				                    }
				                },
				                {
				                    name:'朋友',
				                    itemStyle: {
				                        normal: {
				                            color : '#9acd32'
				                        }
				                    }
				                }
				            ],
				            itemStyle: {
				                normal: {
				                    label: {
				                        show: true,
				                        textStyle: {
				                            color: '#800080'
				                        }
				                    },
				                    nodeStyle : {
				                        brushType : 'both',
				                        strokeColor : 'rgba(255,215,0,0.4)',
				                        lineWidth : 8
				                    }
				                },
				                emphasis: {
				                    label: {
				                        show: false
				                        // textStyle: null      // 默认使用全局文本样式，详见TEXTSTYLE
				                    },
				                    nodeStyle : {
				                        r: 30
				                    },
				                    linkStyle : {}
				                }
				            },
				            minRadius : 15,
				            maxRadius : 25,
				            density : 0.05,
				            attractiveness: 1.2,
				            nodes:[
				                {category:0, name: '乔布斯', value : 10},
				                {category:1, name: '丽萨-乔布斯',value : 2},
				                {category:1, name: '保罗-乔布斯',value : 3},
				                {category:1, name: '克拉拉-乔布斯',value : 3},
				                {category:1, name: '劳伦-鲍威尔',value : 7},
				                {category:2, name: '史蒂夫-沃兹尼艾克',value : 5},
				                {category:2, name: '奥巴马',value : 8},
				                {category:2, name: '比尔-盖茨',value : 9},
				                {category:2, name: '乔纳森-艾夫',value : 4},
				                {category:2, name: '蒂姆-库克',value : 4},
				                {category:2, name: '龙-韦恩',value : 1},
				            ],
				            links : [
				                {source : 1, target : 0, weight : 1},
				                {source : 2, target : 0, weight : 2},
				                {source : 3, target : 0, weight : 1},
				                {source : 4, target : 0, weight : 2},
				                {source : 5, target : 0, weight : 3},
				                {source : 6, target : 0, weight : 6},
				                {source : 7, target : 0, weight : 6},
				                {source : 8, target : 0, weight : 1},
				                {source : 9, target : 0, weight : 1},
				                {source : 10, target : 0, weight : 1},
				                {source : 3, target : 2, weight : 1},
				                {source : 6, target : 2, weight : 1},
				                {source : 6, target : 3, weight : 1},
				                {source : 6, target : 4, weight : 1},
				                {source : 6, target : 5, weight : 1},
				                {source : 7, target : 6, weight : 6},
				                {source : 7, target : 3, weight : 1},
				                {source : 9, target : 6, weight : 1}
				            ],
				            markPoint : {
				                symbol: 'emptyCircle',
				                data : [
				                    {name : '打酱油的标注', value : 100, x:'5%', y:'50%', symbolSize:32},
				                    {name : '打酱油的标注', value : 100, x:'95%', y:'50%', symbolSize:32}
				                ]
				            }
				        }
				    ]	
				});

				var radarChart=Ext.create("Pallas.Chart",{type:'radar',
					 tooltip : {
					        trigger: 'axis'
					    },
					    legend: {
					        x : 'left',
					        data:['图一','图二','图三']
					    },
					    toolbox: {
					        show : true,
					        feature : {
					            mark : {show: true},
					            dataView : {show: true, readOnly: false},
					            restore : {show: true},
					            saveAsImage : {show: true}
					        }
					    },
					    polar : [
					        {
					            indicator : [
					                { text : '指标一' },
					                { text : '指标二' },
					                { text : '指标三' },
					                { text : '指标四' },
					                { text : '指标五' }
					            ],
					            center : ['25%',210],
					            radius : 150,
					            startAngle: 90,
					            splitNumber: 8,
					            name : {
					                formatter:'【{value}】',
					                textStyle: {color:'red'}
					            },
					            scale: true,
					            precision: 1,
					            //power: 100,
					            axisLine: {            // 坐标轴线
					                show: true,        // 默认显示，属性show控制显示与否
					                lineStyle: {       // 属性lineStyle控制线条样式
					                    color: 'green',
					                    width: 2,
					                    type: 'solid'
					                }
					            },
					            axisLabel: {           // 坐标轴文本标签，详见axis.axisLabel
					                show: false,
					                // formatter: null,
					                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
					                    color: '#ccc'
					                }
					            },
					            splitArea : {
					                show : true,
					                areaStyle : {
					                    color: ['rgba(250,0,250,0.3)','rgba(0,200,200,0.3)']
					                }
					            },
					            splitLine : {
					                show : true,
					                lineStyle : {
					                    width : 2,
					                    color : 'yellow'
					                }
					            }
					        },
					        {
					            indicator : [
					                { text : '语文', max  : 150 },
					                { text : '数学', max  : 150 },
					                { text : '英语', max  : 150 },
					                { text : '物理', max  : 120 },
					                { text : '化学', max  : 108 },
					                { text : '生物', max  : 72 }
					            ],
					            center : ['75%', 210],
					            radius : 150
					        }
					    ],
					    series : [
					        {
					            name: '雷达图',
					            type: 'radar',
					            itemStyle: {
					                emphasis: {
					                    // color: 各异,
					                    lineStyle: {
					                        width: 4
					                    }
					                }
					            },
					            data : [
					                {
					                    value : [100, 8, 0.40, -80, 2000],
					                    name : '图一',
					                    symbol: 'star5',
					                    symbolSize: 4,           // 可计算特性参数，空数据拖拽提示图形大小
					                    itemStyle: {
					                        normal: {
					                            lineStyle: {
					                                type: 'dashed'
					                            }
					                        }
					                    }
					                },
					                {
					                    value : [10, 3, 0.20, -100, 1000],
					                    name : '图二',
					                    itemStyle: {
					                        normal: {
					                            areaStyle: {
					                                type: 'default'
					                            }
					                        }
					                    }
					                },
					                {
					                    value : [20, 3, 0.3, -13.5, 3000],
					                    name : '图三',
					                    symbol: 'none',         // 拐点图形类型，非标准参数
					                    itemStyle: {
					                        normal: {
					                            lineStyle: {
					                                type: 'dotted'
					                            }
					                        }
					                    }
					                }
					            ]
					        },
					        {
					            name: '成绩单',
					            type: 'radar',
					            polarIndex : 1,
					            itemStyle: {
					                normal: {
					                    areaStyle: {
					                        type: 'default'
					                    }
					                }
					            },
					            data : [
					                {
					                    value : [120, 118, 130, 100, 99, 70],
					                    name : '张三',
					                    itemStyle: {
					                        normal: {
					                            areaStyle: {
					                                color: (function (){
					                                   // var zrColor = require('zrender/tool/color');
					                                  //  var x = 600
					                                  //  return zrColor.getRadialGradient(
					                                  //      x, 210, 0, x, 200, 150,
					                                  //      [[0, 'rgba(255,255,0,0.3)'],[1, 'rgba(255,0,0,0.5)']]
					                                  //  )
					                                })()
					                            }
					                        }
					                    }
					                },
					                {
					                    value : [90, 113, 140, 30, 70, 60],
					                    name : '李四',
					                    itemStyle: {
					                        normal: {
					                            lineStyle: {
					                                type: 'dashed'
					                            }
					                        }
					                    }
					                }
					            ],
					            markPoint : {
					                symbol: 'emptyHeart',
					                data : [
					                    {name : '打酱油的标注', value : 100, x:'50%', y:'15%', symbolSize:32}
					                ]
					            }
					        }
					    ]
			    });
				
				
				var mixedChart=Ext.create("Pallas.Chart",{type:'mixed',
					tooltip : {
				        trigger: 'item'
				    },
				    legend: {
				        x:'right',
				        data:['北京','上海','广东']
				    },
				    dataRange: {
				        orient: 'horizontal',
				        min: 0,
				        max: 2500,
				        text:['高','低'],           // 文本，默认为数值文本
				        splitNumber:0
				    },
				    toolbox: {
				        show : true,
				        orient: 'vertical',
				        x:'right',
				        y:'center',
				        feature : {
				            mark : {show: true},
				            dataView : {show: true, readOnly: false}
				        }
				    },
				    series : [
				        {
				            name: 'iphone销量',
				            type: 'map',
				            mapType: 'china',
				            mapLocation: {
				                x: 'left'
				            },
				            selectedMode : 'multiple',
				            itemStyle:{
				                normal:{label:{show:true}},
				                emphasis:{label:{show:true}}
				            },
				            data:[
				                {name: '北京',value: 1234,selected:true},
				                {name: '天津',value: Math.round(Math.random()*1000)},
				                {name: '上海',value: 3456,selected:true},
				                {name: '重庆',value: Math.round(Math.random()*1000)},
				                {name: '河北',value: Math.round(Math.random()*1000)},
				                {name: '河南',value: Math.round(Math.random()*1000)},
				                {name: '云南',value: Math.round(Math.random()*1000)},
				                {name: '辽宁',value: Math.round(Math.random()*1000)},
				                {name: '黑龙江',value: Math.round(Math.random()*1000)},
				                {name: '湖南',value: Math.round(Math.random()*1000)},
				                {name: '安徽',value: Math.round(Math.random()*1000)},
				                {name: '山东',value: Math.round(Math.random()*1000)},
				                {name: '新疆',value: Math.round(Math.random()*1000)},
				                {name: '江苏',value: Math.round(Math.random()*1000)},
				                {name: '浙江',value: Math.round(Math.random()*1000)},
				                {name: '江西',value: Math.round(Math.random()*1000)},
				                {name: '湖北',value: Math.round(Math.random()*1000)},
				                {name: '广西',value: Math.round(Math.random()*1000)},
				                {name: '甘肃',value: Math.round(Math.random()*1000)},
				                {name: '山西',value: Math.round(Math.random()*1000)},
				                {name: '内蒙古',value: Math.round(Math.random()*1000)},
				                {name: '陕西',value: Math.round(Math.random()*1000)},
				                {name: '吉林',value: Math.round(Math.random()*1000)},
				                {name: '福建',value: Math.round(Math.random()*1000)},
				                {name: '贵州',value: Math.round(Math.random()*1000)},
				                {name: '广东',value: 2345,selected:true},
				                {name: '青海',value: Math.round(Math.random()*1000)},
				                {name: '西藏',value: Math.round(Math.random()*1000)},
				                {name: '四川',value: Math.round(Math.random()*1000)},
				                {name: '宁夏',value: Math.round(Math.random()*1000)},
				                {name: '海南',value: Math.round(Math.random()*1000)},
				                {name: '台湾',value: Math.round(Math.random()*1000)},
				                {name: '香港',value: Math.round(Math.random()*1000)},
				                {name: '澳门',value: Math.round(Math.random()*1000)}
				            ]
				        },
				        {
				            name:'各省销量',
				            type:'pie',
				            tooltip: {
				                trigger: 'item',
				                formatter: "{a} <br/>{b} : {c} ({d}%)"
				            },
				            center: ['80%', 225],
				            radius: [30, 80],
				            data:[
				                {name: '北京', value: 1234},
				                {name: '上海', value: 3456},
				                {name: '广东', value: 2345}
				            ]
				        }
				    ],
				    animation: false
				    });


				var pieFieldset=Ext.create("Ext.panel.Panel",{layout:'fit',items:[pieChart],columnWidth:0.9,height:400});
				var lineFieldset=Ext.create("Ext.panel.Panel",{layout:'fit',items:[lineChart],columnWidth:0.9,height:400});
				var barFieldset=Ext.create("Ext.panel.Panel",{layout:'fit',items:[barChart],columnWidth:0.9,height:400});
				var mapFieldset=Ext.create("Ext.panel.Panel",{layout:'fit',items:[mapChart],columnWidth:0.9,height:400});


				var scatterFieldset=Ext.create("Ext.panel.Panel",{layout:'fit',items:[scatterChart],columnWidth:0.9,height:400});
				var kFieldset=Ext.create("Ext.panel.Panel",{layout:'fit',items:[kChart],columnWidth:0.9,height:400});
				var forceFieldset=Ext.create("Ext.panel.Panel",{layout:'fit',items:[forceChart],columnWidth:0.9,height:400});
				
				var radarFieldset=Ext.create("Ext.panel.Panel",{layout:'fit',items:[radarChart],columnWidth:0.9,height:400});
				var chordFieldset=Ext.create("Ext.panel.Panel",{layout:'fit',items:[chordChart],columnWidth:0.9,height:400});
				
				var mixedFieldset=Ext.create("Ext.panel.Panel",{layout:'fit',items:[mixedChart],columnWidth:0.9,height:400});

				var columnPanel=Ext.create("Ext.panel.Panel",{ 
					layout:'column',
					items:[
					       pieFieldset,lineFieldset,barFieldset,scatterFieldset,mapFieldset,kFieldset,forceFieldset,
					       radarFieldset,chordFieldset,mixedFieldset
					       ]
				});
				var portal=Ext.create("Ext.panel.Panel",{
					autoScroll:true,
					items:[columnPanel]
				});
				Ext.create("Ext.container.Viewport",{
					layout:'fit',
					items:[portal]
				}); 
			});
			
		</script> 
</head>

</html>