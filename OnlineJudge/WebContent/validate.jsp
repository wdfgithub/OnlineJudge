<%@ page language="java" 
	import="java.awt.*"
	import="java.awt.image.BufferedImage"
	import="java.util.*"
	import="javax.imageio.ImageIO"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	response.setHeader("Cache-Control","no-cache");
	int width=100,height=28;
	BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
	Graphics g=image.getGraphics();
	g.setColor(new Color(200,200,200));
	g.fillRect(0,0,width,height);
	Random rnd=new Random();
	String s="abcdefghijklmnopqrstuvwxyz0123456789";

	String randStr="";
	for(int i=0;i<4;i++){
		int randNum=rnd.nextInt(s.length());
		randStr+=s.charAt(randNum);
	}
	
	session.setAttribute("randStr",randStr);
	g.setColor(Color.black);
	g.setFont(new Font("",Font.PLAIN,20));
	g.drawString(randStr,30,20);
	for(int i=0;i<100;i++){
		int x=rnd.nextInt(width);
		int y=rnd.nextInt(height);
		g.drawOval(x,y,1,1);
	}
	ImageIO.write(image, "JPEG", response.getOutputStream());
	out.clear();
	out=pageContext.pushBody();
%>
</body>
</html>