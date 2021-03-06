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

<div id="edit" style="display:none; margin-top:20px">
<form class="layui-form" action="">  

   <div class="layui-form-item">
    <label class="layui-form-label">电器编号</label>
    <div class="layui-input-block">
      <input type="text" id="elecNo" name="elecNo" lay-verify="required" autocomplete="off" placeholder="请输入电器编号" class="layui-input" style="width:70%;">
    </div>
  </div>
  
   <div class="layui-form-item">
    <label class="layui-form-label">供应商编号</label>
    <div class="layui-input-block">
      <input type="text" id="supplierNo" name="supplierNo" lay-verify="required" autocomplete="off" placeholder="请输入供应商编号" class="layui-input" style="width:70%;">
    </div>
  </div>
  
<!--   <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">报价</label>
      <div class="layui-input-inline">
        <input type="text" id="money" name="money" lay-verify="required|number" autocomplete="off" class="layui-input">
      </div>
    </div>
  </div> -->
  <div class="layui-form-item">
    <label class="layui-form-label">请求编号</label>
    <div class="layui-input-block">
      <input type="text" id="number" name="number" lay-verify="required" autocomplete="off" placeholder="请输入请求编号（请参考说明）" class="layui-input" style="width:70%;">
    </div>
  </div>
  
  
  <div class="layui-form-item">
    <label class="layui-form-label">浏览网址</label>
    <div class="layui-input-block">
      <input type="text" id="url" name="url" lay-verify="required" autocomplete="off" placeholder="请输入浏览网址" class="layui-input" style="width:70%;">
    </div>
  </div>
  
  <div class="layui-form-item">
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
<div class="layui-btn-group demoTable" style="margin-left: 1200px;">
	<button class="layui-btn" data-type="addStu">添加</button>
</div>
<div class="student" style="margin-left: 30px;">
<table class="layui-table" lay-data="{width: 1300, height:500, url:'<%=basePath%>/offer/findAll', page:true, id:'idTest'}" lay-filter="demo">
  <thead>
    <tr>
     <!--  <th lay-data="{type:'checkbox', fixed: 'left'}"></th> -->
      <th lay-data="{field:'id', width:60, sort: true, fixed: true}">ID</th>
      <th lay-data="{field:'eTitle', width:400}">电器</th>
      <th lay-data="{field:'eNo', width:130}">电器编号</th>
      <th lay-data="{field:'sTitle', width:100}">供应商</th>
      <th lay-data="{field:'sNo', width:130}">供应商编号</th>
      <th lay-data="{field:'money', width:90}">当前价格</th> 
      <th lay-data="{field:'number', width:130}">请求编号</th> 
      <th lay-data="{fixed: 'right', align:'center', toolbar: '#barDemo'}">操作</th>
    </tr>
  </thead>
</table>
 </div>
<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">打开网页查看详情</a>
  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
               
          
<script src="<%=basePath%>layui.js"></script>
<script src="<%=basePath%>js/jquery.js"></script>
<script>
layui.use(['table', 'form'],function(){
  var table = layui.table
  ,form = layui.form;
  var id;
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
      layer.confirm('真的删除行么', function(index){
      $.ajax({
		url: "<%=basePath%>/offer/del",
                    data: {
                        "id" : JSON.stringify(data.id)
                    },
                    success: function (data) {
                    	//var dataobj = typeof data === "object" ? data : eval("(" + data + ")");
						if(data.result==true){
							obj.del();
        					layer.close(index);
        					layer.msg("删除成功");
						}else{
							layer.msg(data.reason);
						}
                    }
            });
      });
      
    } else if(obj.event === 'edit'){
      //layer.alert('编辑行：<br>'+ JSON.stringify(data));
	      layer.open({
	          type: 1,
	          closeBtn: 0,
	          area: ['600px', '400px'],
	          shift: 2,
	          shadeClose: false,
	          content: $("#edit"),
	          btn: ['关闭'],
	          yes: function(index, layero){
/* 	           id=null;
	           layer.close(index);  */
	           window.location.reload();
	          }
	      });
	      $('#stuDesc').html("");
	      document.getElementById("supplierNo").value=data.sNo;
	      document.getElementById("elecNo").value=data.eNo;
 	      document.getElementById("number").value=data.number;
 	      document.getElementById("url").value=data.url;
	      document.getElementById("supplierNo").disabled="disabled";
	      document.getElementById("elecNo").disabled="disabled";
	      form.render();  
	      id = data.id;
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
	var supplierNo = data.field.supplierNo;
	var elecNo = data.field.elecNo;
	var url = data.field.url;
	var number = data.field.number;
	if(id==null){
		$.ajax({
			url: "<%=basePath%>offer/add",
	        data: {
	        	"supplierNo":supplierNo,
	            "elecNo" : elecNo,
	            "number" : number,
	            "url" : url,
	        },
	        success: function (data) {
				if(data.result==true){
				   layer.msg("添加成功");
	
	               setTimeout(function () {
	                   window.location.href = "<%=basePath%>offer/page";
	               }, 1000);
					
				}else{
					layer.msg(data.reason);
				}
	        }
	
	     });
	}else{
		$.ajax({
			url: "<%=basePath%>offer/add",
	        data: {
	        	"id":id,
	            "number" : number,
	            "url" : url,
	        },
	        success: function (data) {
				if(data.result==true){
				   layer.msg("修改成功");
	
	               setTimeout(function () {
	                   window.location.href = "<%=basePath%>offer/page";
	               }, 1000);
					
				}else{
					layer.msg(data.reason);
				}
	        }
	
	     });
	}
     return false;
  });

  var $ = layui.$, active = {
  	//添加
    addStu: function(){ 
		layer.open({
	          type: 1,
	          closeBtn: 1,
	          area: ['500px', '440px'],
	          shift: 2,
	          shadeClose: true,
	          content: $("#edit"),
	          btn: ['查看报价信息'],
          	yes: function(index, layero){
          	var supplierNo = $("#supplierNo").val();
          	var elecNo = $("#elecNo").val();
          	if(elecNo==null || elecNo=="" || supplierNo==null || supplierNo=="" ){
          		layer.msg("请先输入电器编号和供应商编号");
          	}else{
          		//显示分配信息
          		$.ajax({
					url: "<%=basePath%>offer/getES",
			        data: {
			        	"supplierNo":supplierNo,
			        	"elecNo":elecNo,
			        },
			        success: function (aa) {
			             var proHtml = '';
			             if(aa.sTitle!="" && aa.eTitle!=""){
			                proHtml = '供应商：'+aa.sTitle+'<br>电器：'+aa.eTitle;
			             	$('#stuDesc').html(proHtml);
			             	form.render(); 
			             }else{
			             	layer.msg(aa.res);
			             }
			        }
		     	});
          	}
          }
	      });
    }
    //以下未使用
    ,getCheckData: function(){ //获取选中数据
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
