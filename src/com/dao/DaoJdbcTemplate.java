package com.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class DaoJdbcTemplate {
	
	@Autowired
	private JdbcTemplate jdbcTempalte;
	
	public JdbcTemplate getJdbcTempalte() {
		return jdbcTempalte;
	}
}
