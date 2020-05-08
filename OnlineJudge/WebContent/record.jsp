<%@ page language="java" contentType="text/html; charset=UTF-8"
    import="vo.User"
    import="vo.Record"
    import="dao.StatusDao"
    import="dao.UserDao"
    import="util.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看代码</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
	<link href="http://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
	<link rel="stylesheet" href="assets/css/jquery.mCustomScrollbar.min.css" />
        <link rel="stylesheet" href="assets/css/custom.css">
        <style>a{text-decoration:none}
        .mywell{
        	color:#404040;
			background-color:#f3f3f4;
        }
        .btn:hover {
color: #f3f3f4;
}
        body{
        	background-color:#E3E3E4;
        	color:#494a5f;
        }
        hr{
        	border:none;
        	height:1px;
        	background-color:#494a5f;
        }
        .mycolor{
			color:#494a5f;
		}
.well{
	margin:10px;
}
        </style>
</head>
<body>
<%
	if(session.getAttribute("user")==null){
		response.setHeader("Refresh","0;url=/OnlineJudge/login.html");
		return;
	}
	User user=(User)session.getAttribute("user");

	StatusDao sd=new StatusDao();
	UserDao ud=new UserDao();
	int runid = Integer.parseInt(new String((request.getParameter("runid")).getBytes("ISO-8859-1"),"UTF-8"));
	Record record=sd.getRecord(runid);
	
%>
<div class="page-wrapper">
	<nav id="sidebar" class="sidebar-wrapper">
		<div class="sidebar-content">
			<a href="#" id="toggle-sidebar"><i class="fa fa-bars mycolor"></i></a>
				<div class="sidebar-brand">
                </div>
                <div class="sidebar-header">
					<div class="user-pic">
                        <img class="img-responsive img-rounded" src="assets/img/user.jpg" alt="">
                    </div>
                    <div class="user-info">
                        <span class="user-name"><%=user.getUname()%></span>
                        <span class="user-role"><%=user.getEmail()%></span>
                        <span class="user-role">已完成问题:  <%=user.getNOsolution()%></span>
                    </div>
                </div><!-- sidebar-header  -->
                <div class="sidebar-search">
                    <div>                   
                        <div class="input-group">                          
                            <input type="text" class="form-control search-menu" placeholder="搜一搜...">
                            <span class="input-group-addon"><i class="fa fa-search"></i></span>
                        </div>
                    </div>                    
                </div><!-- sidebar-search  -->
                <div class="sidebar-menu">
                    <ul>
                        <li class="header-menu"><span></span></li>
                        <li class="sidebar-dropdown">
                        	<a href="home.jsp?uid=<%=user.getUid()%>"><i class="fa fa-user"></i><span>个人主页</span></a>
                        </li>
                        <li class="sidebar-dropdown">
                            <a href="challenge.jsp"><i class="fa fa-tv"></i><span>编程挑战</span></a>
                        </li>
                        <li class="sidebar-dropdown">
                        	<a href=""><i class="fa fa-diamond"></i><span>排行榜</span></a>
                        </li>
                        <li class="sidebar-dropdown">
                        	<a href="submitrecord.jsp"><i class="fa fa-bar-chart-o"></i><span>所有提交记录</span></a></li>
                    </ul>
                   </div><!-- sidebar-menu  -->            
                </div><!-- sidebar-content  -->
        	<div class="sidebar-footer">
				<a href="#" class="tooltip-test" data-toggle="tooltip" title="为了偷懒有点乱......"><i class="fa fa-info"></i></a>
				<a href="login.html" class="tooltip-test" data-toggle="tooltip" title="注销"><i class="fa fa-power-off"></i></a>
			</div>
		</nav><!-- sidebar-wrapper  -->




		<main class="page-content">
			<div id="infoDiv" class="container">
               <h2>查看代码</h2>
               <hr>
	           <div class="well mywell well-sm" >
	           		<strong>用户<br></strong>
		           <br>
		           <a href="home.jsp?uid=<%=ud.getUserid(record.getUname())%>"><small><%=record.getUname()%></small></a><br>
		           <br>
		           <strong>问题<br></strong>
		           <br>
		           <small><%=record.getQname()%><br></small>
		           <br>
	           		<strong>提交结果<br></strong>
		           <br>
		           <small>
						<%
							if(record.getResult()==1){
								%>Accept
								<%
							}else if(record.getResult()==2){
								%>Wrong Answer
								<%
							}else if(record.getResult()==4){
								%>Time Limit Exceeded
								<%
							}else{
								%>Compile Error
								<%
							}
						%>
					<br></small>
		           <br>
		           
		           <strong>提交时间<br></strong>
		           <br>
		           <small><%=Timestamp.stampToDate(record.getSubmittime()+"000")%><br></small>
		           <br>
		           <strong>语言<br></strong>
		           <br>
		           <small>
						<%
							if(record.getLanguage()==1){
								%>
								C/C++
								<%
							}else{
								%>
								Java
								<%
							}
						%>
					<br></small>
		           <br>
		           <strong>代码<br></strong>
		           <br>
		           <pre><code><%=Escape.transform(record.getCode().trim())%></code></pre>
               </div>
			</div>
		</main><!-- page-content" -->
	</div><!-- page-wrapper -->
	
	
	<script src="http://cdn.bootcss.com/jquery/1.11.0/jquery.min.js" type="text/javascript"></script>
	<script>window.jQuery || document.write('<script src="js/jquery-1.11.0.min.js"><\/script>')</script>
	<script src="http://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="assets/js//jquery.mCustomScrollbar.concat.min.js"></script>
        <script src="assets/js/custom.js"></script>
<script>
	$(function () { $("[data-toggle='tooltip']").tooltip(); });
  
</script>
</body>
</html>