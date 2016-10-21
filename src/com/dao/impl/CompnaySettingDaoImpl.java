package com.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.dao.CompanySettingInfoDao;
import com.dao.DaoJdbcTemplate;
import com.exception.SystemException;
import com.model.CompanySettingInfo;
import com.util.StringUtils;
@Repository("companySettingInfoDao")
public class CompnaySettingDaoImpl extends DaoJdbcTemplate implements
		CompanySettingInfoDao {

	@Override
	public void add(CompanySettingInfo companySettingInfo) throws SystemException {
		StringBuffer sb = new StringBuffer(
				"INSERT INTO t_compcny_setting_info(COMPANY_CODE,INIT_PWORD,CUE_TIME_OF_SALES,CUE_TIME_OF_FINANCE) VALUES('")
				.append(companySettingInfo.getCompanyCode())
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
	public void update(CompanySettingInfo companySettingInfo) throws  SystemException {

		String sql = "update t_compcny_setting_info set INIT_PWORD = ? , CUE_TIME_OF_SALES = ? , CUE_TIME_OF_FINANCE= ? WHERE COMPANY_CODE = ?";

		getJdbcTempalte().update(
				sql,
				new Object[] { StringUtils.md5Password(companySettingInfo.getInitPassword()),
						companySettingInfo.getCueTimeSales(),
						companySettingInfo.getCueTimeFinance(),
						companySettingInfo.getCompanyCode() });
	}

	@Override
	public CompanySettingInfo find(String id) {
		String sql = "SELECT * FROM t_compcny_setting_info WHERE ID = ?";
		List<CompanySettingInfo> infoList = getJdbcTempalte().query(sql,
				new Object[] { id }, new RowMapper<CompanySettingInfo>() {

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
	}

	@Override
	public CompanySettingInfo findByCompanyCode(String companyCode) {
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
	}

}
