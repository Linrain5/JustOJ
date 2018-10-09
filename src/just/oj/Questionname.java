package just.oj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class Questionname {
	static Questionname instance=null;
	public static Questionname getInstance() {
		if (instance==null)
			instance=new Questionname();
		return instance;
	}
	
	public Set<Quest>  tijiao() {
		try {
			Set<Quest> quest = new HashSet<>();
			Class.forName("com.mysql.cj.jdbc.Driver");
			String sql = "select id,name from question order by id";
			String url="jdbc:mysql://" + "127.0.0.1" + "/" + "JustOJ" + "?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF8";
			Connection conn = DriverManager.getConnection(url, "root", "3455H*B87pp");
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			while (rs.next()){
				Quest qst = new Quest();
				qst.setid(rs.getInt("id"));
				qst.setname(rs.getString("name"));
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
