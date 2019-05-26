package com.hzmsc.scada.dao.shiro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.hzmsc.scada.entity.shiro.User;

@Repository
public class UserDaoImpl implements UserDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/*public User createUser(final User user) {
		final String sql = "insert into sys_users(username, password, salt, locked) values (?,?,?,?)";

		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

		this.jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql, new String[] { "id" });
				ps.setString(1, user.getUsername());
				ps.setString(2, user.getPassword());
				ps.setString(3, user.getSalt());
				ps.setBoolean(4, user.getLocked());
				//System.out.println(ps);
				return ps;
			}

		}, keyHolder);
		// TODO Auto-generated method stub
		user.setId(keyHolder.getKey().longValue());

		return user;
	}

	public void updateUser(User user) {
		String sql = "update sys_users set username=?, password=?, salt=?, locked=? where id=?";
		this.jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getSalt(), user.getLocked(),
				user.getId());

		// TODO Auto-generated method stub

	}

	public void deleteUser(User user) {
		String sql = "delete from sys_users where id=?";
		this.jdbcTemplate.update(sql, user.getId());
		// TODO Auto-generated method stub

	}

	public User findById(Long id) {
		String sql = "select * from sys_users where id=?";
		User user = null;
		try{
			user = this.jdbcTemplate.queryForObject(sql, new Object[] { id }, new UserMapper());
		}catch (Exception e){
			e.printStackTrace();
		}
		return user;
	}

	public User findByUsername(String Username) {
		String sql = "select * from sys_users where username=?";
		User user = null;
		try {
			user = this.jdbcTemplate.queryForObject(sql, new Object[] { Username }, new UserMapper());
		} catch (Exception e) {
			// System.out.println(e.getMessage());
			e.printStackTrace();
			// return null;
		}
		return user;
	}

	public List<User> findAll() {
		String sql = "select * from sys_users";
		return this.jdbcTemplate.query(sql, new UserMapper());
	}

	private static final class UserMapper implements RowMapper<User> {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getLong("id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setSalt(rs.getString("salt"));
			user.setLocked(rs.getBoolean("locked"));
			return user;
		}
	}*/
	public User createUser(final User user) {
		final String sql = "insert into sys_users(username, password, salt, locked) values (?,?,?,?)";

		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

		this.jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql, new String[] { "id" });
				ps.setString(1, user.getUsername());
				ps.setString(2, user.getPassword());
				ps.setInt(3, user.getEnabled());
			
				//System.out.println(ps);
				return ps;
			}

		}, keyHolder);
		// TODO Auto-generated method stub
		user.setId(keyHolder.getKey().longValue());

		return user;
	}

	public void updateUser(User user) {
		String sql = "update sys_users set username=?, password=?, enabled=? where id=?";
		this.jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getEnabled(),
				user.getId());

		// TODO Auto-generated method stub

	}

	public void deleteUser(User user) {
		String sql = "delete from sys_users where username=?";
		this.jdbcTemplate.update(sql, user.getId());
		// TODO Auto-generated method stub

	}

	public User findById(Long id) {
		String sql = "select * from sys_users where id=?";
		User user = null;
		try{
			user = this.jdbcTemplate.queryForObject(sql, new Object[] { id }, new UserMapper());
		}catch (Exception e){
			e.printStackTrace();
		}
		return user;
	}

	public User findByUsername(String Username) {
		String sql = "select * from sys_users where username=?";
		User user = null;
		try {
			user = this.jdbcTemplate.queryForObject(sql, new Object[] { Username }, new UserMapper());
		} catch (Exception e) {
			// System.out.println(e.getMessage());
			e.printStackTrace();
			// return null;
		}
		return user;
	}

	public List<User> findAll() {
		String sql = "select * from sys_users";
		return this.jdbcTemplate.query(sql, new UserMapper());
	}

	private static final class UserMapper implements RowMapper<User> {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getLong("id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setEnabled(rs.getInt("enabled"));
			user.setSalt(rs.getString("salt"));
			//user.setLocked(rs.getBoolean("locked"));
			return user;
		}
	}

	public void deleteAllUsers() {
		String sql = "delete  from sys_users";
		this.jdbcTemplate.update(sql);
	}

	public boolean isUserExist(User user) {
		
		return findByUsername(user.getUsername())!=null;
	}

	public User login(User user) {
		String sql = "select * from Sys_user where username=? and password=?";
		user = this.jdbcTemplate.queryForObject(sql, new Object[] { user.getUsername(),user.getPassword() }, new UserMapper());
		return user;
	}
}
