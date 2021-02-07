package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjavaweb.model.NewModel;

public class NewMapper implements RowMapper<NewModel>{

	@Override
	public NewModel mapRow(ResultSet rs){
		NewModel news = new NewModel();
		try {
			news.setId(rs.getLong("id"));
			news.setTitle(rs.getNString("title"));
			news.setThumbnail(rs.getNString("thumbnail"));
			news.setShortDescription(rs.getNString("shortdescription"));
			news.setContent(rs.getNString("content"));
			news.setCategoryId(rs.getLong("categoryid"));
			news.setCreatedBy(rs.getNString("createdby"));
			news.setCreatedDate(rs.getTimestamp("createddate"));
			news.setModifiedBy(rs.getNString("modifiedby"));
			news.setModifiedDate(rs.getTimestamp("modifieddate"));
			
			return news;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
}
