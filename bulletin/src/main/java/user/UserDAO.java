package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public UserDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3307/BBS";
			String dbID = "root";
			String dbPassword = "earthyoung";
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int login(String userID, String userPassword) {
		String SQL = "SELECT userPassword FROM USER WHERE userID=?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(userPassword)) {
					return 1;	// �α��� ����
				}
				return 0;	// �α��� ����(��й�ȣ Ʋ��)
			}
			return -1;	// �������� �ʴ� ���̵�
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -2;	// �����ͺ��̽� ����
	}
	
	public int join(User user) {
		try {
			String SQL = "INSERT INTO USER VALUES (?, ?, ?, ?, ?)";
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2,  user.getUserPassword());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4,  user.getUserGender());
			pstmt.setString(5, user.getUserEmail());
			return pstmt.executeUpdate();	// insert �� ������ ���, ���������� ����Ǹ� �ݵ�� 0 �̻��� ���ڸ� ��ȯ.
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	// �����ͺ��̽� ����
	}
	
}
