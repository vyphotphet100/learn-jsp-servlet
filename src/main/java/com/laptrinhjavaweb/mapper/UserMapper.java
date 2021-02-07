package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjavaweb.model.RoleModel;
import com.laptrinhjavaweb.model.UserModel;

public class UserMapper implements RowMapper<UserModel>{

	@Override
	public UserModel mapRow(ResultSet rs) {
		UserModel userModel = new UserModel();
		try {
			userModel.setId(rs.getLong("id"));
			userModel.setUserName(rs.getNString("username"));
			userModel.setPassword(rs.getNString("password"));
			userModel.setFullName(rs.getNString("fullname"));
			userModel.setStatus(rs.getInt("status"));
			userModel.setRoleId(rs.getLong("roleid"));
			userModel.setCreatedBy(rs.getNString("createdby"));
			userModel.setCreatedDate(rs.getTimestamp("createddate"));
			userModel.setModifiedBy(rs.getNString("modifiedby"));
			userModel.setModifiedDate(rs.getTimestamp("modifieddate"));
			
			try {
				RoleModel role = new RoleModel();
				role.setCode(rs.getNString("code"));
				role.setName(rs.getNString("name"));
				userModel.setRole(role);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return userModel;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
