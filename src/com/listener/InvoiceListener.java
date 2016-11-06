package com.listener;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import com.dao.AuthorityDao;
import com.dao.CompanyDao;
import com.dao.CompanySettingInfoDao;
import com.dao.ContractDao;
import com.dao.EmployeeDao;
import com.dao.InvoiceDao;
import com.exception.BusinessException;
import com.model.Authority;
import com.model.Company;
import com.model.CompanySettingInfo;
import com.model.Email;
import com.model.Employee;
import com.model.Invoice;
import com.util.DateUtils;
import com.util.SendEmail;

public class InvoiceListener implements ApplicationListener {

	@Autowired
	private InvoiceDao invoiceDao;
	@Autowired
	private AuthorityDao authorityDao;
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private ContractDao contractDao;
	@Autowired
	private CompanySettingInfoDao companySettingInfoDao;
	
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		
		Thread t1 = new Thread();
		
		cueSales();
		
	}
	/**
	 * 提醒销售
	 */
	private void cueSales(){
		//查找即将开票的发票信息
		List<Invoice> invoiceList = invoiceDao.find();
		//遍历检索结果，根据检索结果找到相关负责人，并向负责人发提醒邮件
		for (Invoice invoice : invoiceList) {
			List<Authority> authList = authorityDao.findComIdByInvId(invoice.getInvId());
			CompanySettingInfo comInfo = null;
			if(null!=authList&&authList.size()>0){
				Company com = companyDao.find(authList.get(0).getComId());
				comInfo = companySettingInfoDao.findByCompanyCode(com.getCompanyCode());
			}else{
				continue;
			}
			
			if(DateUtils.validateDate(invoice.getExpectMakeInvoceDate(), comInfo.getCueTimeSales())){
				if(null!=authList&&authList.size()>0&&null!=comInfo){
					Email mail = companyDao.findMail(authList.get(0).getComId());
					mail.setHead("测试邮件");
					mail.setMessage("测试邮件");
					mail.setMailSmtpAuth("true");
					mail.setMailSmtpHost("smtp.163.com");
					for (Authority authority : authList) {
						Employee emp = employeeDao.find(authority.getEmpId());
						if(null!=emp){
							mail.setRmailUser(emp.getEmail());
							try {
								SendEmail.send(mail);
							} catch (BusinessException e) {
								System.out.println("邮件发送异常");
							}
						}
					}
				}
			}
		}
	}
	/**
	 * 提醒财务
	 */
	private void cueFinance(){
		
	}
	
}
