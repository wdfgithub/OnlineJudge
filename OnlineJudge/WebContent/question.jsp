<%@ page language="java" contentType="text/html; charset=UTF-8"
    import="vo.User"
    import="dao.QuestionDao"
    import="vo.Question"
    import="util.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>问题描述</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
	<link href="http://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
	<link rel="stylesheet" href="assets/css/jquery.mCustomScrollbar.min.css" />
        <link rel="stylesheet" href="assets/css/custom.css">
        <style>a{text-decoration:none;
        	color:#494a5f;
        
        }
        .btn:hover {
color: #f3f3f4;
}
        .mywell{
        	color:#404040;
			background-color:#f3f3f4;
        }
        textarea{
 			resize:none;
		}
.mybt{
	background-color:#494a5f;
	color:#f3f3f4;
}
.well{
	margin:10px;
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
        </style>
</head>
<body>
<%
	if(session.getAttribute("user")==null){
		response.setHeader("Refresh","0;url=/OnlineJudge/login.html");
		return;
	}
	User user=(User)session.getAttribute("user");

	QuestionDao qd=new QuestionDao();
	Question quetion=null;
	if(request.getParameter("qid")!=null){
		int qid = Integer.parseInt(new String((request.getParameter("qid")).getBytes("ISO-8859-1"),"UTF-8"));
		quetion=qd.getQuetion(qid);
	}
	int rs = Integer.parseInt(new String((request.getParameter("rs")).getBytes("ISO-8859-1"),"UTF-8"));
	quetion.setStatus(rs);
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
               <h2><%=quetion.getQid()%> <%=quetion.getName()%></h2>
               <hr>
	               	<div class="well mywell col-sm-8 well-sm" >
		               <strong>问题描述<br></strong>
		               		<br>
		               		<small><%=quetion.getDescription()%></small>
		               		<br>
		               		<br>
		               		<strong>输入<br></strong>
		               		<br>
		               		<small><%=quetion.getInputDescription()%></small>
		               		<br>
		               		<br>
		               		<strong>输出<br></strong>
		               		<br>
		               		<small><%=quetion.getOutputDescription()%></small>
		               		<br>
		               		<br>
		               		<strong>样例输入<br></strong>
		               		<br>
		               		<small><%=quetion.getInputExample()%><br></small>
		               		<br>
		               		<strong>样例输出<br></strong>
		               		<br>
		               		<small><%=quetion.getOutputExample()%><br></small>
		               		<br>
		               		<h4><a href="#" class="tooltip-test mywell pull-right" data-toggle="tooltip" title="允许使用:
		               		java.util
		               		java.math"><i class="fa fa-question"></i></a></h4>
		
		               		<form role="form" action="servlet/JudgeServlet?qid=<%=quetion.getQid()%>&uname=<%=user.getUname()%>&uid=<%=user.getUid()%>" method="post">
		               		
		    					<div class="form-group">
			                 				<textarea name="code" class ="form-control" rows="18">public String solution(String input){
	String output=null;
	return output;
}</textarea>
		          				</div>
		          				
		               			<div class="form-group pull-right">
		          				<input type="submit" class="btn mybt" value="提交代码">
		          				</div>
		               		</form>
               </div>
               <div class="col-sm-3 pull-right text-center well mywell" >
               <hr>
              	 <%
              	 	if(quetion.getLevel()==1){
              	 		%>
              	 			<h3>基础题</h3>
              	 		<%
              	 	}else{
              	 		//balabala
              	 	}
              	 %>
              	<hr>
              	<%
              	 	if(quetion.getStatus()==1){
              	 		%>
          	 				<h3>已完成</h3>
          	 			<%
              	 	}else{
              	 		%>
      	 					<h3>未完成</h3>
      	 				<%
              	 	}
              	 %>
              	<hr>
              	<a href="submitrecord.jsp?id=<%=quetion.getQid()%>" >本题提交记录</a>
              	<hr>
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