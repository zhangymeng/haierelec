<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'statistical.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <script src="<%=basePath%>amcharts/amcharts.js" type="text/javascript"></script>
    <script src="<%=basePath%>amcharts/serial.js" type="text/javascript"></script>
    <link rel="stylesheet" href="<%=basePath%>/css/layui.css">
	<script>
            var chart;
            var graph;
            var chartData = ${list};
			

            AmCharts.ready(function () {
                // SERIAL CHART
                chart = new AmCharts.AmSerialChart();

                chart.dataProvider = chartData;
                chart.marginLeft = 10;
                chart.categoryField = "createDate";

                // listen for "dataUpdated" event (fired when chart is inited) and call zoomChart method when it happens
                //chart.addListener("dataUpdated", zoomChart);

                // AXES
                // category
                var categoryAxis = chart.categoryAxis;
				categoryAxis.autoGridCount  = false;
				categoryAxis.gridCount = chartData.length;
				categoryAxis.gridPosition = "start";
				categoryAxis.labelRotation = 60;

                // value
                var valueAxis = new AmCharts.ValueAxis();
                valueAxis.axisAlpha = 0;
                valueAxis.inside = true;
                valueAxis.dashLength = 3;
                chart.addValueAxis(valueAxis);

                // GRAPH
                graph = new AmCharts.AmGraph();
                graph.type = "smoothedLine"; // this line makes the graph smoothed line.
                graph.lineColor = "#d1655d";
                //graph.negativeLineColor = "#637bb6"; // this line makes the graph to change color when it drops below 0
                graph.bullet = "round";
                graph.valueField = "jingdong";
                graph.balloonText = "<b><span style='font-size:14px;'>京东[[value]]</span></b>";
                chart.addGraph(graph);
                
                var graph2 = new AmCharts.AmGraph();
                graph2.bullet = "round";
                graph2.balloonText = "<b><span style='font-size:14px;'>国美[[value]]</span></b>";
                graph2.lineColor = "#0000CD";
				graph2.valueField = "guomei";
				graph2.type = "smoothedLine";
				chart.addGraph(graph2);
				
				var graph3 = new AmCharts.AmGraph();
				graph3.bullet = "round";
                graph3.balloonText = "<b><span style='font-size:14px;'>苏宁[[value]]</span></b>";
				graph3.valueField = "suning";
				graph3.type = "smoothedLine";
				chart.addGraph(graph3);

                // CURSOR
                var chartCursor = new AmCharts.ChartCursor();
                chartCursor.cursorAlpha = 0;
                chartCursor.cursorPosition = "mouse";
                chartCursor.categoryBalloonDateFormat = "YYYY";
                chart.addChartCursor(chartCursor);

                // SCROLLBAR
                var chartScrollbar = new AmCharts.ChartScrollbar();
                chart.addChartScrollbar(chartScrollbar);

                chart.creditsPosition = "bottom-right";

                // WRITE
                chart.write("chartdiv");
            });

            // this method is called when chart is first inited as we listen for "dataUpdated" event
            function zoomChart() {
                // different zoom methods can be used - zoomToIndexes, zoomToDates, zoomToCategoryValues
                chart.zoomToDates(new Date(1972, 0), new Date(1984, 0));
            }
        </script>

  </head>
  
<body>
 <div style="float:right;margin-right:30px;cursor:pointer;weight:100%" onclick="backBtn()">
 <i class="layui-icon" style="font-size: 30px; color: #1E9FFF;">&#x1006;</i>  
 </div>  

<div id="chartdiv" style="width:93%; height:620px;margin-top:20px;margin-left:30px"></div>

<script>
function backBtn(){
	window.location.href = "<%=basePath%>elec/page";
} 
</script>
</body>
</html>
