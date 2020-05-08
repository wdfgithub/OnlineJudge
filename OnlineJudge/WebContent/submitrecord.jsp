<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
	import="vo.User"
	import="vo.Record"
	import="dao.*"
	import="util.*"
	import="java.util.ArrayList"
	import="java.util.Collections"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>提交记录</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
	<link href="http://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
	<link rel="stylesheet" href="assets/css/jquery.mCustomScrollbar.min.css" />
        <link rel="stylesheet" href="assets/css/custom.css">
        <style>a{text-decoration:none}
.btn:hover {
color: #f3f3f4;
}
.mybt{
	color:#494a5f;
        	background-color:#F3F3F4;
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
		background-color:#F3F3F4;
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
	StatusDao statusdao=new StatusDao();
	QuestionDao quetiondao=new QuestionDao();
	UserDao userdao=new UserDao();
	ArrayList<Record> records;
	if(request.getParameter("word")!=null){
		String word= new String((request.getParameter("word")).getBytes("ISO-8859-1"),"UTF-8");
		records=statusdao.getRecord(word);
		System.out.println(word);
	}else if(request.getParameter("id")!=null){
		int id=Integer.parseInt(new String((request.getParameter("id")).getBytes("ISO-8859-1"),"UTF-8"));
		String word=quetiondao.getQname(id);
		records=statusdao.getRecord(word);
	}else{
		records=statusdao.getRecord("");
	}
	int showL=0;
	if(request.getParameter("lg")==null){
		showL=0;
	}else {
		showL=Integer.parseInt(new String((request.getParameter("lg")).getBytes("ISO-8859-1"),"UTF-8"));
	}
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
               <h2>提交记录</h2>
               <hr>
               <div class="well">
               <form role="form" action="submitrecord.jsp">
               	<div class="form-group col-sm-11">
               	<input name="id" class ="form-control" type="text" placeholder="搜一搜...">
               	</div>
               	<div class="form-group col-sm-1 pull-right">
               	<input  class ="form-control" type="submit" value="搜索">
               	
               	</div>
               </form>
               <table class="table">
					<thead>
						<tr>
							<th></th>
							<th>问题</th>
							<th>时间</th>
							<th>结果</th>
							<th>
							<div class="dropdown pull-right">
								<button type="button" class="btn dropdown-toggle mybt" id="dropdownMenu1" 
										data-toggle="dropdown">
									语言
									<span class="caret"></span>
								</button>
								<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
								
									<li role="presentation">
										<a role="menuitem" tabindex="-1" href="?lg=0">All</a>
									</li>
									<li role="presentation">
										<a role="menuitem" tabindex="-1" href="?lg=1">C/C++</a>
									</li>
									<li role="presentation">
										<a role="menuitem" tabindex="-1" href="?lg=2">Java</a>
									</li>
								</ul>
							</div>
							</th>
						</tr>
					</thead>
					<tbody>
					<%
						Collections.sort(records, new RecordComparator());
									for (Record record:records) {
										if(showL!=0&&record.getLanguage()!=showL){
											continue;
										}
										String time=Timestamp.stampToDate(record.getSubmittime()+"000");
										int qid=quetiondao.getQid(record.getQname());
					%>
							<tr>
								<td><a href="home.jsp?uid=<%=userdao.getUserid(record.getUname())%>"><%=record.getUname() %></a><br>
									
								</td>
								<td><a href="question.jsp?qid=<%=qid%>&rs=<%=record.getResult()%>" class="mybt"><small><%=qid%> <%=record.getQname() %></small></a></td>
								<td><small><%=time%></small><br>
								</td>
								<td>
								<%
										if(record.getResult()==1){
											%>
											<small>Accept</small>
											<%
										}else if (record.getResult()==2){
											%>
											<small>Wrong Answer</small>
											<%
										}else if (record.getResult()==4){
											%>
											<small>Time Limit Exceeded</small>
											<%
										}else{
											%>
											<small>Compile Error</small>
											<%
										}
								%>
								</td>
								<td><a href="record.jsp?runid=<%=record.getSubmittime() %>" class="mybt pull-right"><small>查看代码</small></a><br></td>
							</tr>
							<%
						}
					%>
							
					</tbody>
				</table>
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