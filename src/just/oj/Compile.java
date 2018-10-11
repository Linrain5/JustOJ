package just.oj;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Compile")
public class Compile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Compile() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = "";
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("JustOJuser")) {
					name=cookies[i].getValue();		
				}
			}
		}
		response.setCharacterEncoding("UTF-8");
		String code = request.getParameter("code");
		String lang = request.getParameter("language");
		String wid = request.getParameter("wid");
		ArrayList<String> que = panduan(wid);
		String qua = "";
		for (String s : que) {
			qua += s + "\n";
		}
		if (lang.equals("java")) {
			CompileAndRunJavaFile cr = new CompileAndRunJavaFile();
			cr.setCode(code);
			cr.setWID(wid);

			cr.setabc(qua);
			cr.compileAndRunJavaFile(cr.getCode());

			String stream = "";
			ArrayList<String> answer = cr.panduan();
			for (String s : answer) {
				stream += s + "\n";
			}

			if (cr.isCompileAndRunOK() || cr.getOutMsg().equals(stream)) {
				String string = "运行时间："+String.valueOf(cr.getUseTime())+"     "+"内存使用："+cr.getUseMemory()
				+"     "+"输出："+String.valueOf(cr.getOutMsg())+"\nAC";
				zeng(name,string);
			} else if (cr.isCompilerError()) {
				String string = "编译错误:"+String.valueOf(cr.getCE());
				zeng(name,string);
			} else if (cr.isRunningError()) {
				String string = "运行错误:"+cr.getError();
				zeng(name,string);
			} else if (!cr.getOutMsg().equals(stream)) {
				String string = "答案错误:"+cr.getError();
				zeng(name,string);
			}
		}
	}

	public ArrayList<String> panduan(String wid) {
		try {
			ArrayList<String> quest = new ArrayList<>();
			Class.forName("com.mysql.cj.jdbc.Driver");
			String sql = "select * from question where id=" + wid;
			String url = "jdbc:mysql://" + "127.0.0.1" + "/" + "JustOJ"
					+ "?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF8";
			Connection conn = DriverManager.getConnection(url, "root", "3455H*B87pp");
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			rs.next();
			quest.add(rs.getString("yangli_1"));
			quest.add(rs.getString("yangli_2"));
			quest.add(rs.getString("yangli_3"));
			quest.add(rs.getString("yangli_4"));
			quest.add(rs.getString("yangli_5"));
			pst.close();
			conn.close();
			return quest;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public void zeng(String name,String string) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String sql = "insert into "+name+"(Ztai)values(?)";
			String url = "jdbc:mysql://" + "127.0.0.1" + "/" + "Justuser"
					+ "?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF8";
			Connection conn = DriverManager.getConnection(url, "root", "3455H*B87pp");
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, string);
			pst.executeUpdate();
			pst.close();
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
