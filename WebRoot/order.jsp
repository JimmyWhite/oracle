<%@ page language="java" import="java.util.*,oracle.Cart,oracle.Product" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

  <head>
  
    <base href="<%=basePath%>">
    
    <title>小卖部</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-theme.min.css">
	<script src="${pageContext.request.contextPath}/bootstrap/js/jquery.1.11.3.min.js"></script>	
	<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript">
	$(function(){ 
	    //控制是否显示手机和地址
	    $("input[name='optionsRadiosinline1']").change(function(){
	    if (this.id =="optionsRadios2")
	    {
	    document.getElementById("tel").setAttribute("style", "display:show");
	    document.getElementById("add").setAttribute("style", "display:show");
	    document.getElementById("phone").setAttribute("type", "show");
	    document.getElementById("address").setAttribute("type", "show");
	    }
	    else
	    {
	    document.getElementById("tel").setAttribute("style", "display:none");
	    document.getElementById("add").setAttribute("style", "display:none");
	    document.getElementById("phone").setAttribute("type", "hidden");
	    document.getElementById("address").setAttribute("type", "hidden");
	    }
	    });
	    $('a[name="submit"]').click(function(){
	    //提交数据到Servlet处理
	    var url = this.href;
	    var price = document.getElementsByTagName('h1')[1].innerHTML;
	    if (price == "总价:0.0")
	    {
	       document.getElementById("tap").setAttribute("style","display:show");
	       document.getElementById("tap").innerHTML="请至少选择一件商品";
	       return false;
	    }
	    if (document.getElementById("optionsRadios2").checked)
	    {
	    var phone = document.getElementById("phone").value;
	    var address = document.getElementById("address").value;
	    var version = 1;
	    }
	    else
	    {
	    var phone = "";
	    var address ="";
	    var version = 0;
	    }
	    $.getJSON(url+"&phone="+phone+"&address="+address+"&version="+version,function(data){  
	     if (data==1)
	     {
	        document.getElementById("continue").setAttribute("disabled", "disabled");
	        document.getElementById("mai").setAttribute("disabled", "disabled");
	        document.getElementById("tap").innerHTML="提交成功，两秒后返回首页！";
	    	document.getElementById("tap").setAttribute("style","display:show");	     
	        setTimeout(function(){
	        window.self.location = "index.jsp";
	        },2000);
	      }
   	    });
	    return false; 
	    });
	});
	</script>
  </head>
  
  <body>
  
 <%//定义购物车和商品变量、总价
 Vector<Cart> carts = (Vector<Cart>)request.getSession().getAttribute("cart");
 if (carts == null) carts = new Vector<Cart>();
   Vector<Product> products = (Vector<Product>)request.getSession().getAttribute("product");
  float all_price=0; %>
  <div class="container">
   <h1 align="center">结算页面</h1>
   <br>
    <table class="table table-bordered table-hover">
      <thead>
        <tr>
          <th>商品</th>
          <th>单价</th>
          <th>数量</th>
        </tr>
      </thead>
      <tbody>
      <% for (int i=0; i<=carts.size()-1;i++){ %>
        <tr>
          <td><%= products.get(carts.get(i).getId()).getBrand()+products.get(carts.get(i).getId()).getName()%></td>
          <td><%= products.get(carts.get(i).getId()).getSell_price() %></td>
          <td><%= carts.get(i).getNumber() %></td>
        </tr>
       <% all_price +=products.get(carts.get(i).getId()).getSell_price()*carts.get(i).getNumber();} %>       
      </tbody>
    </table>
         <h1 align="center">总价:<%=all_price%></h1>  
         <p align="center">
             <label class="checkbox-inline">
               <input type="radio" name="optionsRadiosinline1" id="optionsRadios1" 
               value="option1" checked>门店销售
             </label>
             <label class="checkbox-inline">
               <input type="radio" name="optionsRadiosinline1" id="optionsRadios2" 
               value="option1">电话订货
             </label>
         </p>
         <p align="center">
             <label style="display:none" id="tel">手机:</label>
             <input type="hidden" id="phone" style="border-radius:10px;outline:none">
         </p>  
         <p align="center">
             <label style="display:none" id="add">地址:</label>         
             <input type="hidden" id="address" style="border-radius:10px;outline:none">
         </p>  
         <p align="center">
         <a class="btn btn-primary btn-lg" href="index.jsp" id="continue">继续购物</a> 
         &nbsp; &nbsp;
	     <a class="btn btn-success btn-lg" id="mai" name="submit" href="servlet/OrderServlet?price=<%=all_price%>">提交订单</a>
         <h2 align="center" style="display:none" id="tap"></h2>
    </div>
  </body>
</html>
