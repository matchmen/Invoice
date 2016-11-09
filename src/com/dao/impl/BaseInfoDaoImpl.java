package com.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.dao.BaseInfoDao;
import com.dao.DaoJdbcTemplate;
import com.exception.SystemException;
import com.model.BaseInfo;
import com.util.StringUtils;
@Repository("baseInfoDao")
public class BaseInfoDaoImpl extends DaoJdbcTemplate implements BaseInfoDao {

	@Override
	public void add(BaseInfo companySettingInfo) throws SystemException {
		StringBuffer sb = new StringBuffer(
				"INSERT INTO T_BASE_INFO(COM_ID,USER_ID,INIT_PWORD,CUE_TIME_OF_SALES,CUE_TIME_OF_FINANCE) VALUES('")
				.append(companySettingInfo.getCompanyId())
				.append("','")
				.append(companySettingInfo.getUserId())
				.append("','")
				.append(StringUtils.md5Password(companySettingInfo.getInitPassword()))
				.append("','")
				.append(companySettingInfo.getCueTimeSales())
				.append("','")
				.append(companySettingInfo.getCueTimeFinance())
				.append("')");
		getJdbcTempalte().execute(sb.toString());
	}

	@Override
	public void reomve(String id) {

	}

	@Override
	public void update(BaseInfo companySettingInfo) throws  SystemException {
		
		if(null==companySettingInfo.getInitPassword()){
			String sql1 = "update T_BASE_INFO set CUE_TIME_OF_SALES = ? , CUE_TIME_OF_FINANCE= ? WHERE ID = ?";

			getJdbcTempalte().update(sql1,
					new Object[] { 
							companySettingInfo.getCueTimeSales(),
							companySettingInfo.getCueTimeFinance(),
							companySettingInfo.getId()
							});
		}else{
		
		String sql2 = "update T_BASE_INFO set INIT_PWORD = ? , CUE_TIME_OF_SALES = ? , CUE_TIME_OF_FINANCE= ? WHERE ID = ?";

		getJdbcTempalte().update(sql2,
			new Object[] { 
					StringUtils.md5Password(companySettingInfo.getInitPassword()),
					companySettingInfo.getCueTimeSales(),
					companySettingInfo.getCueTimeFinance(),
					companySettingInfo.getId()
					});
		}
	}

	@Override
	public BaseInfo find(String id) {
		String sql = "SELECT * FROM T_BASE_INFO WHERE ID = ?";
		List<BaseInfo> infoList = getJdbcTempalte().query(sql,
				new Object[] { id }, new RowMapper<BaseInfo>() {

					@Override
					public BaseInfo mapRow(ResultSet rs, int arg1)
							throws SQLException {
						BaseInfo info = new BaseInfo();
						info.setCompanyId(rs.getInt("COM_ID"));
						info.setUserId(rs.getInt("USER_ID"));
						info.setInitPassword(rs.getString("INIT_PWORD"));
						info.setCueTimeFinance(rs.getInt("CUE_TIME_OF_FINANCE"));
						info.setCueTimeSales(rs.getInt("CUE_TIME_OF_SALES"));
						return info;
					}
				});
		if (infoList.size() > 0) {
			return infoList.get(0);
		} else {
			return null;
		}
	}
/*
	@Override
	public BaseInfo findByCompanyCode(String companyCode) {
		String sql = "SELECT * FROM t_compcny_setting_info WHERE COMPANY_CODE = ?";
		List<CompanySettingInfo> infoList = getJdbcTempalte().query(sql,
				new Object[] { companyCode }, new RowMapper<CompanySettingInfo>() {

					@Override
					public CompanySettingInfo mapRow(ResultSet rs, int arg1)
							throws SQLException {
						CompanySettingInfo info = new CompanySettingInfo();
						info.setCompanyCode(rs.getString("COMPANY_CODE"));
						info.setInitPassword(rs.getString("INIT_PWORD"));
						info.setCueTimeFinance(rs.getInt("CUE_TIME_OF_FINANCE"));
						info.setCueTimeSales(rs.getInt("CUE_TIME_OF_SALES"));
						return info;
					}
				});
		if (infoList.size() > 0) {
			return infoList.get(0);
		} else {
			return null;
		}
	}*/

	@Override
	public BaseInfo findByComId(Integer id) {
		String sql = "SELECT * FROM t_base_info WHERE COM_ID = ?";
		List<BaseInfo> infoList = getJdbcTempalte().query(sql,
				new Object[] { id }, new RowMapper<BaseInfo>() {

					@Override
					public BaseInfo mapRow(ResultSet rs, int arg1)
							throws SQLException {
						BaseInfo info = new BaseInfo();
						info.setId(rs.getInt("ID"));
						info.setUserId(rs.getInt("USER_ID"));
						info.setCompanyId(rs.getInt("COM_ID"));
						info.setInitPassword(rs.getString("INIT_PWORD"));
						info.setCueTimeFinance(rs.getInt("CUE_TIME_OF_FINANCE"));
						info.setCueTimeSales(rs.getInt("CUE_TIME_OF_SALES"));
						return info;
					}
				});
		if (infoList.size() > 0) {
			return infoList.get(0);
		} else {
			return null;
		}
	}

	@Override
	public BaseInfo findByUserId(Integer id) {
		String sql = "SELECT * FROM t_base_info WHERE USER_ID = ?";
		List<BaseInfo> infoList = getJdbcTempalte().query(sql,
				new Object[] { id }, new RowMapper<BaseInfo>() {

					@Override
					public BaseInfo mapRow(ResultSet rs, int arg1)
							throws SQLException {
						BaseInfo info = new BaseInfo();
						info.setId(rs.getInt("ID"));
						info.setUserId(rs.getInt("USER_ID"));
						info.setCompanyId(rs.getInt("COM_ID"));
						info.setInitPassword(rs.getString("INIT_PWORD"));
						info.setCueTimeFinance(rs.getInt("CUE_TIME_OF_FINANCE"));
						info.setCueTimeSales(rs.getInt("CUE_TIME_OF_SALES"));
						return info;
					}
				});
		if (infoList.size() > 0) {
			return infoList.get(0);
		} else {
			return null;
		}
	}
}
