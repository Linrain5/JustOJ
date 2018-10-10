package just.oj;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Compile")
public class Compile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Compile() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String code = request.getParameter("code");
		String lang = request.getParameter("language");
		String wid = request.getParameter("wid");
		ArrayList<String> que= panduan(wid); 
		String qua ="";
		for(String s :que) {
			qua+=s+"\n";
		}
		if(lang.equals("java")) {
		CompileAndRunJavaFile cr = new CompileAndRunJavaFile();
		cr.setCode(code);
		cr.setWID(wid);
		
		cr.setabc(qua);
		cr.compileAndRunJavaFile(cr.getCode());
		
		String stream = "";
		ArrayList<String>  answer= cr.panduan();
		for(String s : answer) {
			stream += s+"\n";
		}
		
		if(cr.isCompileAndRunOK()) {
			response.getWriter().append("runtime").append(String.valueOf(cr.getUseTime()));
			response.getWriter().append("\nuse memory").append(String.valueOf(cr.getUseMemory()));
			response.getWriter().append("\nout").append(String.valueOf(cr.getOutMsg()));
			if(cr.getOutMsg().equals(stream)) response.getWriter().append("\n You are right");
		}else if(cr.isCompilerError()) {
			response.getWriter().append("\ncompile error").append(String.valueOf(cr.getCE()));
		}else if(cr.isRunningError()) {
			response.getWriter().append("\nrun error").append(String.valueOf(cr.getError()));
		}
		
		
		}
		
		
	}
	public ArrayList<String>  panduan(String wid) {
		try {
			ArrayList<String> quest = new ArrayList<>();
			Class.forName("com.mysql.cj.jdbc.Driver");
			String sql = "select * from question where id="+wid;
			String url="jdbc:mysql://" + "127.0.0.1" + "/" + "JustOJ" + "?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF8";
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
}
