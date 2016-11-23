package com.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.dao.DaoJdbcTemplate;
import com.dao.SystemInfoDao;
import com.model.SystemInfo;

@Repository("systemInfoDao")
public class SystemInfoDaoImpl extends DaoJdbcTemplate implements SystemInfoDao {

	@Override
	public void add(SystemInfo systemInfo) {

	}

	@Override
	public void update(SystemInfo systemInfo) {

	}

	@Override
	public SystemInfo find() {
		String sql = "select * from T_SYSTEM_INFO WHERE ABLED =1";
		
		List<SystemInfo> list = getJdbcTempalte().query(sql,new RowMapper<SystemInfo>(){

			@Override
			public SystemInfo mapRow(ResultSet rs, int arg1)
					throws SQLException {
				SystemInfo info = new SystemInfo();
				info.setCueTimeOfFinance(rs.getInt("CUE_TIME_OF_FINANCE"));
				info.setCueTimeOfSales(rs.getInt("CUE_TIME_OF_SALES"));
				info.setId(rs.getInt("ID"));
				info.setUserInitPW(rs.getString("USER_INIT_PW"));
				info.setSystemMail(rs.getString("SYS_MAIL"));
				info.setSystemMailPW(rs.getString("SYS_MAIL_PW"));
				info.setSystemMailType(rs.getString("SYS_MAIL_TYPE"));
				return info;
			}
			
		});
		
		if(null!=list&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public void remove(Integer id) {

	}

}
