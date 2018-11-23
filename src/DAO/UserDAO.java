package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class UserDAO extends BaseDAO{

	public User getUser(String user_id) throws SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;

		try{
			conn=getConnection();
			stmt = conn.prepareStatement("select * from Usuario where usuario_login=?");
			stmt.setString(1, user_id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()){
				User u = createUser(rs);
				rs.close();
				return u;
			}
			rs.close();
		}finally{
			if(stmt !=null){
				stmt.close();
			}
			if(conn !=null){
				conn.close();
			}			
		}
		return null;
	}
	
	public List<User> getUsersGroup(String group_id) throws SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		List<User> users = new ArrayList<>();;
		try{
			conn=getConnection();
			stmt = conn.prepareStatement("select A.user_id as 'user_id', B.identity as 'identity', B.name as 'name', B.mothername as 'mothername', B.birthdate as 'birthdate', B.sex as 'sex', B.email as 'email', b.foreign as 'foreign', b.state as 'state', b.type as 'type', b.pass as 'pass', b.icon as 'icon' from UserGroup A, User B where a.user_id=b.user_id and a.group_id=?");
			stmt.setString(1, group_id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()){
				User u = createUser(rs);
				users.add(u);
			}
			rs.close();
		}finally{
			if(stmt !=null){
				stmt.close();
			}
			if(conn !=null){
				conn.close();
			}			
		}
		return users;
	}
	
	
	public List<User> getUserAutentication(String user_id) throws SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		List<User> users = new ArrayList<>();
		try{
			conn=getConnection();
			stmt = conn.prepareStatement("select * from Usuario where user_login=?");
			stmt.setString(1, user_id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()){
				User u = createUser(rs);
				users.add(u);				
			}
			rs.close();
		}finally{
			if(stmt !=null){
				stmt.close();
			}
			if(conn !=null){
				conn.close();
			}			
		}
		return users;
	}
	
	public User createUser(ResultSet rs) throws SQLException{
		User u = new User();
		u.setLogin(rs.getString("usuario_login"));
		u.setPassword(rs.getString("usuario_password"));
		u.setNome(rs.getString("usuario_nome"));
		u.setFuncao(rs.getString("usuario_funcao"));
		u.setStatus(rs.getString("usuario_status"));	
		return u;
	}
}
