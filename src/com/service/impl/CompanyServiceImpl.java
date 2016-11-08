package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bean.CompanyBean;
import com.dao.CompanyDao;
import com.dao.CompanySettingInfoDao;
import com.dao.EmployeeDao;
import com.exception.ParameterException;
import com.exception.SystemException;
import com.model.Company;
import com.model.CompanySettingInfo;
import com.model.Employee;
import com.service.CompanyService;
import com.util.StringUtils;

@Repository("companyService")
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private CompanySettingInfoDao companySettingInfoDao;
	
	@Override
	public void register(Company company,Employee employee) throws ParameterException, SystemException {
		
		CompanyBean bean = new CompanyBean();
		bean.setEmployee(employee);
		if(null!=company){
			bean.setCompany(company);
			//设为管理员
			employee.setIsAdmin(true);
			//公司数据验证			
			validateCompanyInfo(bean);
		}
		//用户数据验证
		validateEmployee(bean);
		
		if(null!=company)
			//添加
			companyDao.add(company);

		//密码加密
		employee.setPassword(StringUtils.md5Password(employee.getPassword()));
		//添加到员工表中
		employeeDao.add(employee);
		
		CompanySettingInfo companySettingInfo =new CompanySettingInfo();
		//公司信息设置
		if(null!=company)
			companySettingInfo.setCompanyCode(company.getCompanyCode());
		companySettingInfo.setCueTimeFinance(7);
		companySettingInfo.setCueTimeSales(7);
		companySettingInfo.setInitPassword("000000");
		companySettingInfoDao.add(companySettingInfo);
		
	}

	@Override
	public void updateCompanyInfo(Company company) {
		//数据验证
		//validate(company);
		//添加
		companyDao.update(company);
	}

	@Override
	public void cancellate(Company company) {
		
	}

	private void validateEmployee(CompanyBean bean) throws ParameterException{
		if(null!=bean.getEmployee()){
			//为空验证
			/*if(null==bean.getEmployee().getEmployeeId()
					||StringUtils.isBlank(bean.getEmployee().getEmployeeId())
					||StringUtils.isEmpty(bean.getEmployee().getEmployeeId())){
				throw new ParameterException("register", "employeeId", "员工号不能为空!", bean, "companybean");
			}*/
			//为空验证
			if(null==bean.getEmployee().getEmployeeName()
					||StringUtils.isBlank(bean.getEmployee().getEmployeeName())
					||StringUtils.isEmpty(bean.getEmployee().getEmployeeName())){
				throw new ParameterException("register", "employeeId", "姓名不能为空!", bean, "companybean");
			}
			//电话号码验证
			if(null==bean.getEmployee().getPhoneNumber()
					||StringUtils.isBlank(bean.getEmployee().getPhoneNumber())
					||StringUtils.isEmpty(bean.getEmployee().getPhoneNumber())){
				throw new ParameterException("register", "phoneNumber", "电话号码不能为空!", bean, "companybean");
			}
			//格式验证
			if(!StringUtils.isNumber(bean.getEmployee().getPhoneNumber())){
				throw new ParameterException("register", "phoneNumber", "电话号码格式正确!", bean, "companybean");
			}
			Integer id = employeeDao.findId(bean.getEmployee().getPhoneNumber());
			//数据库存在验证
			if(null!=id){
				throw new ParameterException("register", "phoneNumber", "电话号码已经被注册!", bean, "companybean");
			}
			//个人邮箱验证
			if(null==bean.getEmployee().getEmail()
					||StringUtils.isBlank(bean.getEmployee().getEmail())
					||StringUtils.isEmpty(bean.getEmployee().getEmail())){
				throw new ParameterException("register", "email", "个人邮箱不能为空!", bean, "companybean");
			}
			//邮箱格式验证
			if(!StringUtils.isEmail(bean.getEmployee().getEmail())){
				throw new ParameterException("register", "email", "邮箱格式不正确!", bean, "companybean");
			}
			id = employeeDao.findId(bean.getEmployee().getEmail());
			//数据库存在验证
			if(null!=id){
				throw new ParameterException("register", "email", "邮箱已经被注册!", bean, "companybean");
			}
			//密码验证
			if(null==bean.getEmployee().getPassword()
					||StringUtils.isBlank(bean.getEmployee().getPassword())
					||StringUtils.isEmpty(bean.getEmployee().getPassword())){
				throw new ParameterException("register", "password", "密码不能为空!", bean, "companybean");
			}
		}

	}
	
	private void validateCompanyInfo(CompanyBean bean) throws ParameterException {
		
		//公司名称为空验证
		if(null==bean.getCompany().getCompanyName()
				||StringUtils.isBlank(bean.getCompany().getCompanyName())
				||StringUtils.isEmpty(bean.getCompany().getCompanyName())){
			throw new ParameterException("register", "comoanyName", "公司名称不能为空!", bean, "companybean");
		}
		/*Company companyNameByName = companyDao.findByOtherInfo(bean.getCompany().getCompanyName());
		//数据库存在判断
		if(null!=companyNameByName){
			throw new ParameterException("register", "comoanyName", "公司名称已经被注册!", bean, "companybean");
		}*/
		
		Company companyNameByCode = companyDao.find(bean.getCompany().getCompanyCode());
		//公司代码为空验证
		if(null==bean.getCompany().getCompanyCode()
				||StringUtils.isBlank(bean.getCompany().getCompanyCode())
				||StringUtils.isEmpty(bean.getCompany().getCompanyCode())){
			throw new ParameterException("register", "companyCode", "公司代码不能为空!", bean, "companybean");
		}
		//数据库是否存在验证
		if(null!=companyNameByCode){
			throw new ParameterException("register", "companyCode", "公司代码已经被注册!", bean, "companybean");
		}
		/*//为空验证
		if(null==bean.getCompany().getCompanyEmail()
				||StringUtils.isBlank(bean.getCompany().getCompanyEmail())
				||StringUtils.isEmpty(bean.getCompany().getCompanyEmail())){
			throw new ParameterException("register", "companyEmail", "公司邮箱不能为空!",bean, "companybean");
		}*/
		//格式验证
		/*if(!StringUtils.isEmail(bean.getCompany().getCompanyEmail())){
			throw new ParameterException("register", "companyEmail", "公司邮箱格式无效!",bean, "companybean");
		}*/
		/*Company companyNameByEmail = companyDao.findByOtherInfo(bean.getCompany().getCompanyEmail());
		//数据库是否存在验证
		if(null!=companyNameByEmail){
			throw new ParameterException("register", "companyEmail", "公司邮箱已经被注册!", bean, "companybean");
		}
		//为空验证
		if(null==bean.getCompany().getTellphoneNumber()
				||StringUtils.isBlank(bean.getCompany().getTellphoneNumber())
				||StringUtils.isEmpty(bean.getCompany().getTellphoneNumber())){
			throw new ParameterException("register", "tellphoneNumber", "公司电话不能为空!", bean, "companybean");
		}
		Company companyNameByph = companyDao.findByOtherInfo(bean.getCompany().getTellphoneNumber());
		//数据库是否存在验证
		if(null!=companyNameByph){
			throw new ParameterException("register", "tellphoneNumber", "公司电话已经被注册!", bean, "companybean");
		}*/
		
			}

	@Override
	public Company findByCompanyCode(String companyCode) {
		
		Company company = companyDao.find(companyCode);
		
		return company;
	}

	@Override
	public CompanySettingInfo findCompanySettingInfo(String companyCode) {
		
		return companySettingInfoDao.findByCompanyCode(companyCode);
		
	}

	@Override
	public void updateCompanySettingInfo(CompanySettingInfo info) throws ParameterException, SystemException {
		//密码为空验证
		if(null==info.getInitPassword()
				||StringUtils.isBlank(info.getInitPassword())
				||StringUtils.isEmpty(info.getInitPassword())){
			throw new ParameterException("setCompanyInfo", "initPassword", "密码不能为空!", info, "info");
		}
		//财务开票提示时间为空验证
		if(null==info.getCueTimeFinance()){
			throw new ParameterException("setCompanyInfo", "initPassword", "财务开票提示时间不能为空!", info, "info");
		}
		//数字验证
		if(!StringUtils.isNumber(info.getCueTimeFinance().toString())){
			throw new ParameterException("setCompanyInfo", "cueTimeFinance", "财务开票提示时间不能为空!", info, "info");
		}
		//销售催款提示时间为空验证
		if(null==info.getCueTimeSales()){
			throw new ParameterException("setCompanyInfo", "initPassword", "财务开票提示时间不能为空!", info, "info");
		}
		//数字验证
		if(!StringUtils.isNumber(info.getCueTimeSales().toString())){
			throw new ParameterException("setCompanyInfo", "cueTimeSales", "财务开票提示时间不能为空!", info, "info");
		}
		//更新
		companySettingInfoDao.update(info);
	}
}
