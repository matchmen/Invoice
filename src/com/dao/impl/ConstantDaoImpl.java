package com.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.dao.ConstantDao;
import com.dao.DaoJdbcTemplate;
import com.model.Constant;
@Repository("constantDao")
public class ConstantDaoImpl extends DaoJdbcTemplate implements ConstantDao<Constant>{

	@Override
	public List<Constant> find(Constant constant) {
		
		String sql = "select * from t_constant_List where PAGE_NAME = ? AND CONSTANT_NAME = ?";
		
		return getJdbcTempalte().query(sql, new Object[]{constant.getPageName(),constant.getConstantName()}, new RowMapper<Constant>(){
			@Override
			public Constant mapRow(ResultSet rs, int arg1)
					throws SQLException {
				Constant constant = new Constant();
				constant.setConstantName(rs.getString("CONSTANT_NAME"));
				constant.setConstantValue(rs.getString("CONSTANT_VALUE"));
				constant.setIndex(rs.getInt("CONSTANT_INDEX"));
				return constant;
			}
		});
	}
}
