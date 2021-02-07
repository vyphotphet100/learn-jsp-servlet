package com.laptrinhjavaweb.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.laptrinhjavaweb.dao.GenericDAO;
import com.laptrinhjavaweb.mapper.RowMapper;
import com.mysql.cj.api.jdbc.Statement;

public abstract class AbstractDAO<T> implements GenericDAO<T> {

	private ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
	public Connection getConnection() {
		try {
			Class.forName(resourceBundle.getString("driverName"));
			String url = resourceBundle.getString("url");
			String user = resourceBundle.getString("user");
			String password = resourceBundle.getString("password");
			return DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			return null;
		}
	}

	@Override
	public List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) {
		List<T> res = new ArrayList<T>();
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			conn = this.getConnection();
			stm = conn.prepareStatement(sql);
			this.setParameters(stm, parameters);
			rs = stm.executeQuery();

			while (rs.next()) {
				T category = rowMapper.mapRow(rs);
				res.add(category);
			}

			return res;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (stm != null)
					stm.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}

	}

	@Override
	public Long insert(String sql, Object... parameters) {
		Connection conn = this.getConnection();
		PreparedStatement stm = null;
		ResultSet rs = null;
		Long id = null;

		try {
			if (conn != null) {
				conn.setAutoCommit(false);
				stm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				this.setParameters(stm, parameters);
				stm.executeUpdate();
				rs = stm.getGeneratedKeys();
				if (rs.next())
					id = rs.getLong(1);

				conn.commit();
				return id;
			}

			return null;
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

			e.printStackTrace();
			return null;
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (stm != null)
					stm.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	@Override
	public void update(String sql, Object... parameters) {
		Connection conn = this.getConnection();
		PreparedStatement stm = null;

		if (conn != null) {
			try {
				conn.setAutoCommit(false);
				stm = conn.prepareStatement(sql);
				this.setParameters(stm, parameters);
				stm.executeUpdate();
				conn.commit();
			} catch (SQLException e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} finally {
				try {
					if (conn != null)
						conn.close();
					if (stm != null)
						stm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	private void setParameters(PreparedStatement stm, Object... parameters) {
		for (int i = 0; i < parameters.length; i++) {
			try {
				Object parameter = parameters[i];
				int index = i + 1;
				
				if (parameter instanceof Long)
					stm.setLong(index, (Long) parameter);
				else if (parameter instanceof String)
					stm.setNString(index, (String) parameter);
				else if (parameter instanceof Integer)
					stm.setInt(index, (Integer) parameter);
				else if (parameter instanceof Timestamp)
					stm.setTimestamp(index, (Timestamp) parameter);
				else if (parameter == null)
					stm.setNull(index, Types.NULL);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
