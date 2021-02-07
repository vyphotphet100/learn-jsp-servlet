package com.laptrinhjavaweb.dao.impl;

import java.util.List;

import com.laptrinhjavaweb.dao.INewDAO;
import com.laptrinhjavaweb.mapper.NewMapper;
import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.paging.Pagable;

public class NewDAO extends AbstractDAO<NewModel> implements INewDAO {

	@Override
	public List<NewModel> findAll() {
		String sql = "SELECT * FROM news;";
		return this.query(sql, new NewMapper());
	}
	
	@Override
	public List<NewModel> findAll(Pagable pagable) {
		StringBuilder sql = new StringBuilder("SELECT * FROM news ");
		
		if (pagable.getSorter().getSortName() != null && pagable.getSorter().getSortBy() != null) 
			sql.append("ORDER BY " + pagable.getSorter().getSortName() + " " + pagable.getSorter().getSortBy() + " ");
		
		if (pagable.getOffset() != null && pagable.getLimit() != null) 
			sql.append("LIMIT "+ pagable.getLimit() +" OFFSET "+ pagable.getOffset());
			
		return this.query(sql.toString(), new NewMapper());
	}

	@Override
	public List<NewModel> findByCategoryId(Long categoryId) {
		String sql = "SELECT * FROM news WHERE categoryid = ?;";
		return this.query(sql, new NewMapper(), categoryId);
	}

	@Override
	public Long save(NewModel newModel) {
		String sql = "INSERT INTO news(title, thumbnail, shortdescription, content, categoryid, "
				+ "createddate, createdby, modifieddate, modifiedby) " + "VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?);";
		return this.insert(sql, newModel.getTitle(), newModel.getThumbnail(), newModel.getShortDescription(),
				newModel.getContent(), newModel.getCategoryId(), newModel.getCreatedDate(), newModel.getCreatedBy(),
				newModel.getModifiedDate(), newModel.getModifiedBy());
	}

	@Override
	public NewModel findOne(Long id) {
		String sql = "SELECT * FROM news WHERE id = ?;";
		List<NewModel> news = this.query(sql, new NewMapper(), id);
		if (!news.isEmpty())
			return news.get(0);
		return null;
	}

	@Override
	public void update(NewModel newModel) {
		StringBuilder sql = new StringBuilder("UPDATE news SET title = ?, ");
		sql.append("thumbnail = ?, shortdescription = ?, ");
		sql.append("content = ?, categoryid = ?, ");
		sql.append("modifieddate = ?, modifiedby = ? ");
		sql.append("WHERE id = ?;");
		this.update(sql.toString(), newModel.getTitle(), newModel.getThumbnail(), newModel.getShortDescription(),
				newModel.getContent(), newModel.getCategoryId(), newModel.getModifiedDate(), newModel.getModifiedBy(),
				newModel.getId());
	} 

	@Override
	public void delete(long id) {
		String sql = "DELETE FROM news WHERE id = ?";
		this.update(sql, id);
	}

}
