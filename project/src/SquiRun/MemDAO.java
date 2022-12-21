package SquiRun;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemDAO {
	String driver = "oracle.jdbc.driver.OracleDriver"; // 6행 ~ 9행 데이터베이스 접속을 위한 4가지 정보를 String 변수에 저장.
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "c##JhDB";
	String passwd = "k404";
	
	Connection con;
	PreparedStatement st;
	ResultSet rs;
	
	public MemDAO() {
		
	}
	
	public Connection getConn() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public MemDTO getMemberDTO(int num) {
		MemDTO dto = new MemDTO();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		
		try {
			con = getConn();
			String sql = "SELECT * FROM RUN_MEMBER where num=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1,num);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				dto.setNum(rs.getInt("num"));
				dto.setId(rs.getString("id"));
				dto.setPwd(rs.getString("pwd"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	public boolean loginMemberCheck(String id, String pwd)  {
		boolean result = false;
		try {
			con = getConn();
			st = con.prepareStatement("SELECT * FROM RUN_MEMBER WHERE id = ? AND pwd = ?");
			st.setString(1,id.trim());
			st.setString(2,pwd.trim());
			rs = st.executeQuery();
			if (rs.next())
				result = true;
			
		} catch (SQLException e) {
			System.out.println(e+"=> getPassByCheck fail");
		}finally {
			dbClose();
		}
		return result;
	}
	
	public boolean getIdByCheck(String id) {
		boolean result = true;
		try {
			con = getConn();
			st = con.prepareStatement("SELECT * FROM RUN_MEMBER WHERE id = ?");
			st.setString(1,id.trim());
			rs = st.executeQuery();
			if (rs.next())
				result = false;
		} catch (SQLException e) {
			System.out.println(e+"=> getIdByCheck fail");
		} finally {
			dbClose();
		}
		return result;
	}
	
	public boolean insertMember(MemDTO dto) {
		boolean ok = false;
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = getConn();
			String sql = "INSERT INTO RUN_MEMBER("+"num,id,pwd)" + "VALUES(RUN_MEMBER_seq.NEXTVAL,?,?)";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getPwd());
			
			int r = ps.executeUpdate();
			
			if (r>0) {
				System.out.println("가입 성공");
				ok = true;
			}
			else {
				System.out.println("가입 실패");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return ok;
	}
	
	public void dbClose() {
		try {
			if (rs != null) 
				rs.close();
			if (st != null)
				st.close();
			if (con != null)
				con.close();
		} catch (Exception e) {
			System.out.println(e + "=> dbClose fail");
		}
	}
	
}
