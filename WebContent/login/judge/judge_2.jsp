<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="just.oj.Questionname"%>
<%@page import="just.oj.Quest"%>
<%request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="quest" scope="page" class="just.oj.Quest"></jsp:useBean>
 <jsp:setProperty property="*" name="quest"/>
    <% Cookie[] cookies = request.getCookies();
    
    	if(cookies!=null){
    	            for(int i = 0;i < cookies.length;i++){
    	                if(cookies[i].getName().equals("JustOJname")){      
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>JustOJ社区</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="title" content="Web site" />
    <meta name="description" content="Site description here" />
    <meta name="keywords" content="keywords here" />
    <meta name="language" content="en" />
    <meta name="subject" content="Site subject here" />
    <meta name="robots" content="All" />
    <meta name="copyright" content="Your company" />
    <meta name="abstract" content="Site description here" />
    <meta name="MSSmartTagsPreventParsing" content="true" />
    <link id="theme" rel="stylesheet" type="text/css" href="style.css" title="theme" />
<script type="text/javascript" language="javascript" src="js/addon.js"></script>
<script type="text/javascript" language="javascript" src="js/custom.js"></script><link rel="icon" type="image/x-icon" href="images/icon.ico" />
  </head>
  <body> 
    <!-- top wrapper -->  
    <div id="topWrapper"> 
      <div id="topBannerWrapper"> 
        <div id="topBanner"> 
          <div id="hmenuWrapper"> 
            <div id="hmenu"> 
              <ul>
                <li>
                  <a href="judge_1.jsp" shape="rect">考题总界面</a>
                </li>
                <li id="current" style="border:none">
                  <a href="#" shape="rect">代码提交区</a>
                </li>
               <li>
                  <a href="judge_3.jsp"shape="rect">江科大排名</a>
                </li>
                <li>
                  <a href="judge_4.jsp" shape="rect">我的状态</a>
                </li>
                <li>
                  <a href="judge_5.jsp" shape="rect">新闻</a>
                </li>
                <li>
                  <a href="judge_6.jsp" shape="rect">关于</a>
                </li>
              </ul> 
            </div> 
          </div> 
        </div> 
      </div>  
	</div>
    <!-- end top wrapper -->  
    <div id="wrapper"> 
      <div id="container"> 
        <!--  content -->  
        <div id="content"> 
          <div style="margin-top:20px;"> 



            <div id="maib"> 
             
            <div class="bloc rounded" style="margin-top:20px;"> 
                <h3>Problem description</h3>  
            <%
				ArrayList<Quest> sts=Questionname.getInstance().tijiao();
				int where=0;
				String whe = request.getParameter("where_id");
				where = Integer.valueOf(whe);
			%>
			
                <p>
                  <%=sts.get(where).getquestion()%>
                </p>  
    
            </div>

            <div class="bloc rounded" style="margin-top:20px;"> 
                <h3>样例输入</h3>  
                <p>
                <%=sts.get(where).gety1() %>
                </p>    
            </div>  
            <div class="bloc rounded" style="margin-top:20px;"> 
                <h3>样例输出</h3>  
                <p>
                 <%=sts.get(where).getya1() %>
                </p>    
            </div>  
          </div>

            <div id="sidebaa"> 
				
              <div class="bloc rounded" style="margin-top:20px;"> 
                <form action="/Compile" method="post">
                
                  <label>请选择语言</label>
                <select name="language">
                  <option value="java">Java</option>  
                  <option value="c">C</option>  
                  <option value="cpp">C++</option>
                </select>
                <input name="wid" value="<%=sts.get(where).getid()%>">
                  <textarea rows="30" cols="60" name="code">在这里粘贴上你的代码
                  </textarea>
                  <input type="submit" value="提交代码"/>
                </form>
              </div> 

            </div> 
          </div>  



          <div class="clear"></div> 
        </div>  
        <!-- end content -->  
        <div class="clear" style="height:40px"></div> 
      </div>  
      <!-- end container --> 
    </div>  
    <div id="bottomWrapper"> 
      <div id="footer"> 
        <div class="footer-one-third"> 
          <h2>JustOJ</h2>  
          <ul>
            <li>
              <a href="#" shape="rect">开发小组长 1243137612</a>
            </li>
            <li>
              <a href="#" shape="rect">前端  </a>
            </li>
            <li>
              <a href="#" shape="rect">后端   </a>
            </li>
          </ul> 
        </div>  
        <div class="footer-one-third"> 
          <h2>Questions</h2>  
          <ul>
            <li>
              <a href="#" shape="rect">了解更多的OJ题目</a>
            </li>
            <li>
              <a href="#" shape="rect">更多的学习方法</a>
            </li>
          </ul> 
        </div>  
        <div class="footer-one-third footer-last"> 
          <h2>About</h2>  
          <ul>
            <li>
              <a href="#" shape="rect">开源许可</a>
            </li>
            <li>
              <a href="#" shape="rect">JustOJ小组</a>
            </li>
          </ul> 
        </div> 
      </div>  
      <div id="bottom-links"> 
        <div style="padding-top:20px"> 
            
          <a href="http://182.254.233.137/" title="加入我们吧" target="_blank">加入我们</a>join us
        </div> 
      </div> 
    </div> 
  </body>
</html>
<%  			 break;}
    	    
    	 } 
}
    	else {
    		response.sendRedirect("/index.html"); 
    	}

%>