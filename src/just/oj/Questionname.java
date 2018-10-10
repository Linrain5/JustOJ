package just.oj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Questionname {
	static Questionname instance=null;
	public static Questionname getInstance() {
		if (instance==null)
			instance=new Questionname();
		return instance;
	}
	
	public ArrayList<Quest>  tijiao() {
		try {
			ArrayList<Quest> quest = new ArrayList<>();
			Class.forName("com.mysql.cj.jdbc.Driver");
			String sql = "select * from question order by id";
			String url="jdbc:mysql://" + "127.0.0.1" + "/" + "JustOJ" + "?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF8";
			Connection conn = DriverManager.getConnection(url, "root", "3455H*B87pp");
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			while (rs.next()){
				Quest qst = new Quest();
				qst.setid(rs.getInt("id"));
				qst.setname(rs.getString("name"));
				qst.setquestion(rs.getString("question"));
				qst.sety1(rs.getString("yangli_1"));
				qst.sety2(rs.getString("yangli_2"));
				qst.sety3(rs.getString("yangli_3"));
				qst.sety4(rs.getString("yangli_4"));
				qst.sety5(rs.getString("yangli_5"));
				qst.setya1(rs.getString("yanglia_1"));
				qst.setya2(rs.getString("yanglia_2"));
				qst.setya3(rs.getString("yanglia_3"));
				qst.setya4(rs.getString("yanglia_4"));
				qst.setya5(rs.getString("yanglia_5"));
				
				quest.add(qst);
			}
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
