package just.oj;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 * 接受 post 请求   
 * DBMS Mysql
 * 数据库   JustOJ  表   user     1.id varchar(11) 2.password text 3.name text .utf-8编码
 */
@WebServlet("/login/loginserver")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/index.html"); 
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("id");
		String password = request.getParameter("password");
		if(Jiancha(userName,password)) {
			Cookie cookie1 = new Cookie("JustOJname",userName);
			cookie1.setMaxAge(60*60*24);
			cookie1.setPath("/");
			long time = System.currentTimeMillis();
			Cookie cookie2 = new Cookie("JustOJtime",String.valueOf(time));
			cookie2.setMaxAge(60*60*24);
			cookie2.setPath("/");
			response.addCookie(cookie1);
			response.addCookie(cookie2);
			response.sendRedirect("judge/judge_1.jsp");   
		}else {
			response.sendRedirect("/index.html"); 
		}
	}
	public static boolean Jiancha(String user, String password) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String sql = "SELECT * FROM user WHERE id="+user;
			String url="jdbc:mysql://" + "127.0.0.1" + "/" + "JustOJ" + "?serverTimezone=UTC&useSSL=false";
			Connection conn = DriverManager.getConnection(url, "root", "3455H*B87pp");
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			rs.next();
			String passwords = rs.getString(2);
			if(passwords.equals(password)) return true;
			conn.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("驱动加载失败");
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("sql语句异常");
			return false;
		}return false;
	}
}
