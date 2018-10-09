<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="just.oj.Questionname"%>
<%@page import="just.oj.Quest"%>
<%request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="student" scope="page" class="just.oj.Quest"></jsp:useBean>
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
<script type="text/javascript" language="javascript" src="js/custom.js"></script>
<link rel="icon" type="image/x-icon" href="images/icon.ico" />
  </head>
  <body> 
    <!-- top wrapper -->  
    <div id="topWrapper"> 
      <div id="topBannerWrapper"> 
        <div id="topBanner"> 
          <div id="hmenuWrapper"> 
            <div id="hmenu"> 
              <ul>
                <li id="current" style="border:none">
                  <a href="judge_1.jsp" shape="rect">考题总界面</a>
                </li>
                <li>
                  <a href="judge_2.jsp" shape="rect">代码提交区</a>
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
      <div id="topSlideshowWrapper"> 
        <div id="sliderContainer"> 
          <!-- YOUR SLIDESHOW IMAGES HERE (recommended size 920x300) -->  
          <div id="slider1"> 
            <ul id="slider1Content">
              <li class="slider1Image"> 
                  <img src="images/css/background.jpg" />  
                  <span class="bottom" style="display: inline;"> 
                    <strong>诚心为大家建造code环境</strong>  
                    <br clear="none" /> 江科大code论坛社区
                  </span> 
              </li>
              <li class="slider1Image"> 
                  <img src="images/css/background2.jpg" />
                  <span class="bottom" style="display: inline;"> 
                    <strong>诚心为大家建造code环境</strong>  
                    <br clear="none" /> 江科大code论坛社区
                  </span> 
              </li>
              <li class="slider1Image"> 
                  <img src="images/css/background3.jpg" /> 
                  <span class="bottom" style="display: inline;"> 
                    <strong>诚心为大家建造code环境</strong>  
                    <br clear="none" />江科大code论坛社区
                  </span> 
              </li>
              <li class="slider1Image" style="clear:both"></li>
            </ul>  
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
            <div id="main"> 
            	<%
	Set<Quest> sts=Questionname.getInstance().tijiao();
	Iterator<Quest> it=sts.iterator();
	%>
            	<%
	while (it.hasNext()){
		Quest st=it.next();
	%>
              <li>
              <a href="#" shape="rect"><%=st.getid() %>.<%=st.getname() %></a>
            </li>
 
            <% }%>
           </div> 
            <div id="sidebar"> 
              <div class="bloc rounded" style="margin-top:20px;"> 
                <h3>Declaration</h3>  
                <p>
                  加入我们吧~~~ 
                  <strong>you can be stronger</strong>
                </p> 
              </div>  
              <div class="bloc rounded" style="margin-top:20px;"> 
                <h3>news</h3>  
                <p>
                  最新更新的OJ题库更改日志如下：
                </p> 
                <p>
                  1.
                </p>
                <p>
                  2.
                </p>
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
<% 
				break;
    	 }
    }   	 
}
    	else {
    		response.sendRedirect("/index.html"); 
    	}

%>