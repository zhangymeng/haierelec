<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="<%=basePath%>/css/layui.css">
  </head>
<body>  
<div style="margin-bottom: 5px;">       
   
<ins class="adsbygoogle" style="display:inline-block;width:970px;height:90px" data-ad-client="ca-pub-6111334333458862" data-ad-slot="3820120620"></ins>
 
</div>

<div id="edit" margin-top:20px">
<form class="layui-form" action="">  

   <div class="layui-inline">
    <label class="layui-form-label">电器</label>
    <div class="layui-input-block">
      <input type="text" id="title" name="title" autocomplete="off" placeholder="请输入您想要查找的电器名称" class="layui-input" style="width:250px;">
    </div>
  </div>
  
   <div class="layui-inline">
    <div class="layui-inline">
      <label class="layui-form-label">范围</label>
      <div class="layui-input-inline" style="width: 100px;">
        <input type="text" name="price_min" placeholder="￥" lay-verify="required|number" autocomplete="off" class="layui-input">
      </div>
      -
      <div class="layui-input-inline" style="width: 100px;">
        <input type="text" name="price_max" placeholder="￥" lay-verify="required|number" autocomplete="off" class="layui-input">
      </div>
    </div>
  </div>
  
  <div class="layui-inline">
    <div class="layui-input-block">
      <button class="layui-btn" lay-submit="" lay-filter="demo1">确认</button>
    </div>
  </div>
  </form>
   <div id="stuDesc" style="margin-left:110px;"></div>
</div>
 
<!-- <div class="layui-btn-group demoTable">
  <button class="layui-btn" data-type="getCheckData">获取选中行数据</button>
  <button class="layui-btn" data-type="getCheckLength">获取选中数目</button>
  <button class="layui-btn" data-type="isAll">验证是否全选</button>
</div> -->

 <div class="student" style="margin-left: 30px">
 <table id="demo" lay-filter="test"></table>
 </div>
<script type="text/html" id="barDemo">
</script>
               
          
<script src="<%=basePath%>layui.js"></script>
<script src="<%=basePath%>js/jquery.js"></script>
<script>
layui.use(['table', 'form'],function(){
  var table = layui.table
  ,form = layui.form;
  var id;
  
   //第一个实例
  table.render({
    elem: '#demo'
    ,height: 315
    ,url: '<%=basePath%>/offer/findAll' //数据接口
    ,page: true //开启分页
    ,width:1300
    ,height:500
    ,cols: [[ //表头
      {field: 'id', title: 'ID', width:100, sort: true, fixed: 'left'}
      ,{field: 'eTitle', title: '电器', width:220}
      ,{field: 'eNo', title: '电器编号', width:220}
      ,{field: 'sTitle', title: '供应商', width:220} 
      ,{field: 'sNo', title: '供应商编号', width: 220}
      ,{field: 'money', title: '报价', sort: true}
    ]]
  });
  
  //监听表格复选框选择
  table.on('checkbox(demo)', function(obj){
    console.log(obj)
  });
  //监听工具条
  table.on('tool(demo)', function(obj){
    var data = obj.data;
    if(obj.event === 'detail'){
    	window.open(data.url);  
    } else if(obj.event === 'del'){
    //删除操作
      
    } else if(obj.event === 'edit'){

    }
  });
  
  //自定义验证规则
form.verify({
    title: function(value){
      if(value.length < 5){
        return '标题至少得5个字符啊';
      }
    }
    ,pass: [/(.+){6,12}$/, '密码必须6到12位']
    ,content: function(value){
      layedit.sync(editIndex);
    }
});

  //监听提交
form.on('submit(demo1)', function(data){
	var title = data.field.title;
	var minMoney = data.field.price_min;
	var maxMoney = data.field.price_max;
	if(minMoney>0 && maxMoney>0 && maxMoney>minMoney){
		table.render({
		    elem: '#demo'
		    ,height: 315
		    ,url: '<%=basePath%>/offer/findAll?title='+title+'&minMoney='+minMoney+'&maxMoney='+maxMoney //数据接口
		    ,page: true //开启分页
		    ,width:1300
    		,height:500
		    ,cols: [[ //表头
		      {field: 'id', title: 'ID', width:100, sort: true, fixed: 'left'}
		      ,{field: 'eTitle', title: '电器', width:220}
		      ,{field: 'eNo', title: '电器编号', width:220}
		      ,{field: 'sTitle', title: '供应商', width:220} 
		      ,{field: 'sNo', title: '供应商编号', width: 220}
		      ,{field: 'money', title: '报价', sort: true}
		    ]]
		  });
	}else{
		layer.msg("索引价格应为正数,	且后者大于前者");
	}
     return false;
  });

  var $ = layui.$, active = {
    //以下未使用
    getCheckData: function(){ //获取选中数据
      var checkStatus = table.checkStatus('idTest')
      ,data = checkStatus.data;
      layer.alert(JSON.stringify(data));
    }
    ,getCheckLength: function(){ //获取选中数目
      var checkStatus = table.checkStatus('idTest')
      ,data = checkStatus.data;
      layer.msg('选中了：'+ data.length + ' 个');
    }
    ,isAll: function(){ //验证是否全选
      var checkStatus = table.checkStatus('idTest');
      layer.msg(checkStatus.isAll ? '全选': '未全选')
    }
  };
  
  $('.demoTable .layui-btn').on('click', function(){
    var type = $(this).data('type');
    active[type] ? active[type].call(this) : '';
  });
});
</script>

</body>
</html>
