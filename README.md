# OnlineJudge

摘要：

[系统的主要用途]

Online Judge系统（简称OJ）是一个在线的判题系统。用户可以在线提交程序源代码（本系统支持Java语言），系统对源代码进行编译和执行，并通过预先设计的测试数据来检验程序源代码的正确性。

[列出已经实现的功能]

用户：

    1. 获取题库、题目的相关信息。
    
    2. 在线对代码进行编译、执行、保存、返回运行（编译）结果（支持返回结果：正确，答案错误，编译错误，时间超限）。
    
    3. 总体题目评测成绩查询。
    
    4. 用户信息服务，包括注册、登录、忘记密码、邮箱验证、个人主页等功能。
    
后台：

    1. 检测用户输入数据，防止SQL注入攻击。
    
    2. 密码经过MD5加密生成。
    
管理员：

    1. 题库、题目相关信息的增删改查及用户导入、用户成绩查询与修改。
    
[特点]

    1. 采用MVC框架，使用Jsp + Servlet + JavaBean。
    
    2. 采用Bootstrap+jQuery+ AJAX编写前端页面，页面简单整洁大方。采用响应式布局可以为不同终端的用户提供更加舒适的界面和更好的用户体验。
    
    3. 考虑安全性，控制用户输入数据，更改密码会生成加密的token。
    
运行截图：

![image](https://raw.githubusercontent.com/wdfgithub/OnlineJudge/master/screenshot/1.png)
![image](https://raw.githubusercontent.com/wdfgithub/OnlineJudge/master/screenshot/2.png)
![image](https://raw.githubusercontent.com/wdfgithub/OnlineJudge/master/screenshot/3.png)
![image](https://raw.githubusercontent.com/wdfgithub/OnlineJudge/master/screenshot/4.png)
![image](https://raw.githubusercontent.com/wdfgithub/OnlineJudge/master/screenshot/5.png)

移动端查看：

![image](https://raw.githubusercontent.com/wdfgithub/OnlineJudge/master/screenshot/6.png)
