package com.hzmsc.scada.dao;

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

import com.hzmsc.scada.entity.Employee;


@Repository
public class EmployeeDaoImpl implements EmployeeDao {
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDatasource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Employee createEmployee(final Employee employee) {
		final String sql = "INSERT INTO `employees` ("
				+ "`employeeCode`, `name`, `gender`, `birthday`, "
				+ "`regtime`, `address`, `phone`, `email`, `idcard`, "
				+ "`position`, `workshop`, `username`, `password`, `isActive`, "
				+ "`disableTime`, `reactivateTime` "
				+ ") VALUES ("
				+ "?, ?, ?, ?, "
				+ "?, ?, ?, ?, ?, "
				+ "?, ?, ?, ?, ?, "
				+ "?, ?)";
		
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

		this.jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql, new String[] { "employeeId" });
				
				ps.setString(1, employee.getEmployeeCode());
				ps.setString(2, employee.getName());
				ps.setString(3, employee.getGender());
				ps.setDate(4, employee.getBirthday());
				ps.setTimestamp(5, employee.getRegtime());
				ps.setString(6, employee.getAddress());
				ps.setString(7, employee.getPhone());
				ps.setString(8, employee.getEmail());
				ps.setString(9, employee.getIdcard());
				ps.setInt(10, employee.getPosition());
				ps.setInt(11, employee.getWorkshop());
				ps.setString(12, employee.getUsername());
				ps.setString(13, employee.getPassword());
				ps.setInt(14, employee.getIsActive());
				ps.setTimestamp(15, employee.getDisableTime());
				ps.setTimestamp(16, employee.getReactivateTime());
				
				return ps;
			}
		}, keyHolder);
		
		employee.setEmployeeId(keyHolder.getKey().intValue());
		
		return employee;
	}

	public void updateEmployee(Employee employee) {
		String sql = "UPDATE `employees` SET "
				+ "`employeeCode`=?, `name`=?, `gender`=?, `birthday`=?, `regtime`=?, "
				+ "`address`=?, `phone`=?, `email`=?, `idcard`=?, `position`=?, "
				+ "`workshop`=?, `username`=?, `password`=?, `isActive`=?, `disableTime`=?, "
				+ "`reactivateTime`=? "
				+ "WHERE `employeeId`=?";
		
		this.jdbcTemplate.update(sql, 
				employee.getEmployeeCode(), employee.getName(), employee.getGender(), employee.getBirthday(), employee.getRegtime(), 
				employee.getAddress(), employee.getPhone(), employee.getEmail(), employee.getIdcard(), employee.getPosition(), 
				employee.getWorkshop(), employee.getUsername(), employee.getPassword(), employee.getIsActive(), employee.getDisableTime(), 
				employee.getReactivateTime(), employee.getEmployeeId()
				);
		
	}

	public void deleteEmployee(Employee employee) {
		String sql = "delete from employees where employeeId=?";
		this.jdbcTemplate.update(sql, employee.getEmployeeId());		
	}
	
	public void safeDeleteEmployee(Employee employee) {
		String sql = "update employees set isActive=0, disableTime=now() where employeeId=?";
		this.jdbcTemplate.update(sql, employee.getEmployeeId());		
	}

	public int countById(int employeeId) {
		String sql = "select count(*) from employees where employeeId=?";
		return this.jdbcTemplate.queryForObject(sql, Integer.class, employeeId);
	}
	
	public int countByActiveId(int employeeId, int isActive) {
		String sql = "select count(*) from employees where employeeId=? and isActive=?";
		return this.jdbcTemplate.queryForObject(sql, Integer.class, employeeId, isActive);
	}

	public Employee findById(int employeeId) {
		String sql = "select * from employees where employeeId=?";
		return this.jdbcTemplate.queryForObject(sql, new Object[] { employeeId }, new EmployeeMapper());
	}
	
	public Employee findByActiveId(int employeeId, int isActive) {
		String sql = "select * from employees where employeeId=? and isActive=?";
		return this.jdbcTemplate.queryForObject(sql, new Object[] { employeeId, isActive }, new EmployeeMapper());
	}

	public List<Employee> findAll() {
		String sql = "select * from employees";
		return this.jdbcTemplate.query(sql, new EmployeeMapper());
	}
	
	public List<Employee> findActiveAll(int isActive) {
		String sql = "select * from employees where isActive=?";
		return this.jdbcTemplate.query(sql, new Object[] { isActive }, new EmployeeMapper());
	}
	
	private static final class EmployeeMapper implements RowMapper<Employee> {
		public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
			Employee employee = new Employee();
			
			employee.setEmployeeId(rs.getInt("employeeId"));
			employee.setEmployeeCode(rs.getString("employeeCode"));
			employee.setName(rs.getString("name"));
			employee.setGender(rs.getString("gender"));
			employee.setBirthday(rs.getDate("birthday"));
			employee.setRegtime(rs.getTimestamp("regtime"));
			employee.setAddress(rs.getString("address"));
			employee.setPhone(rs.getString("phone"));
			employee.setEmail(rs.getString("email"));
			employee.setIdcard(rs.getString("idcard"));
			employee.setPosition(rs.getInt("position"));
			employee.setWorkshop(rs.getInt("workshop"));
			employee.setUsername(rs.getString("username"));
			employee.setPassword(rs.getString("password"));
			employee.setIsActive(rs.getInt("isActive"));
			employee.setDisableTime(rs.getTimestamp("disableTime"));
			employee.setReactivateTime(rs.getTimestamp("reactivateTime"));
			
			return employee;
		}
	}

}
