package just.oj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Ztai {
	static Ztai instance = null;

	public static Ztai getInstance() {
		if (instance == null)
			instance = new Ztai();
		return instance;
	}

	public ArrayList<Ztaibean> tijiao(String name) {
		try {
			ArrayList<Ztaibean> ztai = new ArrayList<Ztaibean>();
			Class.forName("com.mysql.cj.jdbc.Driver");
			String sql = "select * from "+name+" order by id";
			String url = "jdbc:mysql://" + "127.0.0.1" + "/" + "Justuser"
					+ "?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF8";
			Connection conn = DriverManager.getConnection(url, "root", "3455H*B87pp");
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Ztaibean zt = new Ztaibean();
				zt.setId(Integer.valueOf(rs.getString("id")));
				zt.setZtai(rs.getString("Ztai"));
				ztai.add(zt);
			}
			pst.close();
			conn.close();
			return ztai;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
