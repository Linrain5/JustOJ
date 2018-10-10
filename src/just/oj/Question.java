package just.oj;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Question")
public class Question extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String question = request.getParameter("question");
		String y1 = request.getParameter("y1");
		String ya1 = request.getParameter("ya1");
		String y2 = request.getParameter("y2");
		String ya2 = request.getParameter("ya2");
		String y3 = request.getParameter("y3");
		String ya3 = request.getParameter("ya3");
		String y4 = request.getParameter("y4");
		String ya4 = request.getParameter("ya4");
		String y5 = request.getParameter("y5");
		String ya5 = request.getParameter("ya5");
		int a = tijiao(name, question,y1,ya1,y2,ya2,y3,ya3, y4,ya4, y5, ya5);
		response.getWriter().append(String.valueOf(a));
	}
	public static int tijiao(String name,String question,String y1,String ya1,String y2,String ya2,String y3,String ya3,String y4,String ya4,String y5,String ya5) {
		try {
			int a = 0;
			Class.forName("com.mysql.cj.jdbc.Driver");
			String sql = "insert into question(question,name,yangli_1,yanglia_1,yangli_2,yanglia_2,yangli_3,yanglia_3,yangli_4,yanglia_4,yangli_5,yanglia_5)values(?,?,?,?,?,?,?,?,?,?,?,?);";
			String url="jdbc:mysql://" + "127.0.0.1" + "/" + "JustOJ" + "?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF8";
			Connection conn = DriverManager.getConnection(url, "root", "3455H*B87pp");
			PreparedStatement pst = conn.prepareStatement(sql);
		
			pst.setString(1, question);
			pst.setString(2, name);
			pst.setString(3, y1);
			pst.setString(4, ya1);
			pst.setString(5, y2);
			pst.setString(6, ya2);
			pst.setString(7, y3);
			pst.setString(8, ya3);
			pst.setString(9, y4);
			pst.setString(10, ya4);
			pst.setString(11, y5);
			pst.setString(12, ya5);
			a = pst.executeUpdate();
			pst.close();
			conn.close();
			return a;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
