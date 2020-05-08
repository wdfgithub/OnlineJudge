<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">  
  <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
  <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>找回密码</title>
<style type="text/css">
.btn:hover {
color: #f3f3f4;
}
body{
	background-color: #f3f3f4;
    text-align: center;
}
 .container{
	display:table;
	height:100%;
}
.row{
	display: table-cell;
	vertical-align: middle;
}
/* centered columns styles */
.row-centered {
	text-align:center;
}
.col-centered {
	display:inline-block;
	float:none;
	text-align:center;
	margin-right:-4px;
}
.mybt{
	background-color:#494a5f;
	color:#f3f3f4;
}
</style>
</head>
<body>
<script type="text/javascript">

function checkForm(){
	var password=changeForm.password.value;
	var regexpw = /^[A-Za-z0-9]{6,20}$/;
	if(password==""){
		alert("请输入密码！");
	}else if(!regexpw.test(password)){
		alert("密码应为6-20位字母数字组合！");
	}else{
		alert("更改密码成功！");
		changeForm.submit();
	}
}
</script>

<div class="container">
	<div class="row row-centered">
		<div class="col-md-5 col-centered">
		<br>
		<br>
			<h2>找回密码</h2>
			<form  role="form" action="ChangePasswordServlet?id=<%=Integer.parseInt(new String((request.getParameter("id")).getBytes("ISO-8859-1"),"UTF-8"))%>" name="changeForm" method="post">
			<div class="form-group">
				<input class="form-control" type="text" name="password" placeholder="请输入新密码">
			</div>
			<div class="form-group">
				<input class="btn mybt btn-block" type="button" value="更改密码" onClick="checkForm()">
			</div>
			<div class="form-group">
				<a href="login.html"><small>尝试登陆</small></a>
			</div>
			</form>
		</div>
	</div>
</div>
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script> 
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

</body>
</html>