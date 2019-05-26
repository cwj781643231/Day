package com.hzmsc.scada.dao.dic;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hzmsc.scada.entity.dic.ErrorCode;

@Repository
public class ErrorCodeDaoImpl implements ErrorCodeDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDatasource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public ErrorCode findById(int errorCodeId) {
		String sql = "select * from dic_errorcode where errorCodeId=?";
		return this.jdbcTemplate.queryForObject(sql, new Object []{errorCodeId}, new ErrorCodeMapper());
	}

	private static final class ErrorCodeMapper implements RowMapper<ErrorCode> {
		public ErrorCode mapRow(ResultSet rs, int rowNum) throws SQLException {
			ErrorCode errorCode = new ErrorCode();
			errorCode.setErrorCodeId(rs.getInt("errorCodeId"));
			errorCode.setErrorCodeName(rs.getString("errorCodeName"));
			errorCode.setErrorCodeCN(rs.getString("errorCodeCN"));
			errorCode.setErrorCodeEN(rs.getString("errorCodeEN"));
			return errorCode;
		}
	}

	public int countOfErrorCodeId(int errorCodeId) {
		String sql = "select count(*) from dic_errorcode where errorCodeId=?";
		return this.jdbcTemplate.queryForObject(sql, Integer.class, errorCodeId);
	}
	
}
