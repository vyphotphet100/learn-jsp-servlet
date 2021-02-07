package com.laptrinhjavaweb.dao.impl;

import java.util.List;

import com.laptrinhjavaweb.dao.IUserDAO;
import com.laptrinhjavaweb.mapper.UserMapper;
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.paging.Pagable;

public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO {

	@Override
	public List<UserModel> findAll() {
		String sql = "SELECT * from user;";
		return this.query(sql, new UserMapper());
	}

	@Override
	public List<UserModel> findAll(Pagable pagable) {
		StringBuilder sql = new StringBuilder("SELECT * FROM user ");

		if (pagable.getSorter().getSortName() != null && pagable.getSorter().getSortBy() != null)
			sql.append("ORDER BY " + pagable.getSorter().getSortName() + " " + pagable.getSorter().getSortBy() + " ");

		if (pagable.getOffset() != null && pagable.getLimit() != null)
			sql.append("LIMIT " + pagable.getLimit() + " OFFSET " + pagable.getOffset() + " ");

		return this.query(sql.toString(), new UserMapper());
	}

	@Override
	public UserModel findOne(Long id) {
		String sql = "SELECT * FROM user WHERE id = ?;";
		List<UserModel> lRes = this.query(sql, new UserMapper(), id);
		if (!lRes.isEmpty())
			return lRes.get(0);
		return null;
	}

	@Override
	public Long save(UserModel model) {
		StringBuilder sql = new StringBuilder("INSERT INTO ");
		sql.append("user(username, password, fullname, status, roleid, createddate, createdby) ");
		sql.append("VALUES(?, ?, ?, ?, ?, ?, ?);");
		return this.insert(sql.toString(), model.getUserName(), model.getPassword(), model.getFullName(),
				model.getStatus(), model.getRoleId(), model.getCreatedDate(), model.getCreatedBy());
	}

	@Override
	public void update(UserModel model) {
		StringBuilder sql = new StringBuilder("UPDATE user SET ");
		sql.append("username=?, password=?, fullname=?, status=?, roleid=? ");
		sql.append("modifieddate=?, modifiedby=? ");
		sql.append("WHERE id=?");

		this.update(sql.toString(), model.getUserName(), model.getPassword(), model.getFullName(), model.getStatus(),
				model.getRoleId(), model.getModifiedDate(), model.getModifiedBy(), model.getId());
	}

	@Override
	public void delete(long id) {
		String sql = "DELETE FROM user WHERE id=?";
		this.update(sql, id);
	}

	@Override
	public UserModel findByUsernameAndPasswordAndStatus(String username, String password, Integer status) {
		StringBuilder sql = new StringBuilder("SELECT * FROM (user as u JOIN role as r ON u.roleid = r.id) ");
		sql.append("WHERE username=? AND password=? AND status=?;");
		
		List<UserModel> lRes = this.query(sql.toString(), new UserMapper(), username, password, status);
		if (!lRes.isEmpty())
			return lRes.get(0);
		return null;
	}

}
