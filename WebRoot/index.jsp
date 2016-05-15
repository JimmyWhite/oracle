<%@ page language="java" import="java.util.*,oracle.Product" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
  <head>
    <base href="<%=basePath%>">
    
    <title>小卖部</title>
    
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-theme.min.css">
	<script src="${pageContext.request.contextPath}/bootstrap/js/jquery.1.11.3.min.js"></script>	
	<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
	<jsp:include page="/servlet/ProductServlet"></jsp:include>
	  <!--
     处理加入购物车请求,送往CartServlet处理数据并返回结果
     利用正则判断用户输入是否符合要求
  -->
	<script type="text/javascript">
    $(function(){
		$("a").click(function(){
	  		var url = this.href;
	  		var id = this.id;	
	  		var r = /^[0-9]*[1-9][0-9]*$/;
	  		var number = document.getElementById(id).value;
	  		 if (r.test(parseInt(number)))
	  		 {
	  		$.getJSON(url+"?id="+id+"&number="+number,function(data){
	  		if (data=="1")
	  		     $("#s"+id).text("已加入购物车！");
	  		     });
	  		 }
	  		else
	  	         $("#s"+id).text("请输入正确数量！");
	  		return false;
		});
	});
	
	</script>
	</head>
 <body>
 <%
 //从数据库中取出商品信息交给jsp显示，商品信息存在变量vector。

 Vector<Product> vector =(Vector<Product>)request.getAttribute("vector");	
 %>
 <h1 align="center">购物页面</h1>
 <br>
<div class="container">
<!-- 两个3x3循环显示商品 -->
<%int no=0;
for (int i=0; i<=2; i++){ %>
	<div class="row clearfix">
		<div class="col-md-12 column">
			<div class="row">
			<%for (int j=0; j<=2; j++){
            String photo ="./images/" + vector.get(no).getPhoto();
			 %>
				<div class="col-md-4">
					<div class="thumbnail">
						<img alt="300x200" src=<%=photo%> /> 
						<div class="caption">
							<h3 align="center">
								<%=vector.get(no).getBrand()+vector.get(no).getName() %>
							</h3>
							<p align="center">
								规格：<%=vector.get(no).getSpec() %>  价格：<%= vector.get(no).getSell_price()%>
							</p>
							<p align="center">
								数量：<input type="text" value=1 id=<%=no%> style="border-radius:10px;outline:none">
							</p>
							<p align="center">
								<a class="btn btn-primary" id=<%=no%> href="servlet/CartServlet">加入购物车</a>
								<span id=<%="s"+no%>></span>
							</p>
						</div>
					</div>
				</div>
				<% no += 1;}  %>
			</div>
		</div>
	</div>
	<% } %>
	<p align="center">  
	<a class="btn btn-danger btn-lg" href="order.jsp">结算购物车</a>
    </p>
</div>

 </body>
</html>
