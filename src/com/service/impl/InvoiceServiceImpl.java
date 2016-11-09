package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bean.InvoiceBean;
import com.dao.AuthorityDao;
import com.dao.CompanyDao;
import com.dao.ContractDao;
import com.dao.UserDao;
import com.dao.InvoiceDao;
import com.exception.BusinessException;
import com.exception.ParameterException;
import com.model.Authority;
import com.model.Company;
import com.model.Contract;
import com.model.User;
import com.model.Invoice;
import com.service.InvoiceService;
import com.util.StringUtils;
@Repository("invoiceService")
public class InvoiceServiceImpl implements InvoiceService {
	
	@Autowired
	private InvoiceDao invoiceDao;
	@Autowired
	private AuthorityDao authorityDao;
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ContractDao contractDao;
	
	@Override
	public Invoice checkInvoiceInfo(String invoiceId, Integer invId) throws ParameterException {
		
		//空验证
		if(null==invoiceId
				||StringUtils.isBlank(invoiceId)
				||StringUtils.isEmpty(invoiceId)){
			throw new ParameterException("checkInvoiceInfo", "invoiceId", "开票编号不能为空", null, null	);
		}
		
		if(!validateAuth(invoiceId,invId)){
			return null;
		}
		
		Invoice invoice = invoiceDao.findByInvoiceId(invoiceId);
		
		return invoice;
	}

	@Override
	public void updateInvoiceInfo(Invoice invoice, Integer empId) throws BusinessException,ParameterException {
		if(null!=invoice){
			if(!validateAuth2(invoice.getInvId(),empId)){
				throw new BusinessException("updateInvoiceInfo", "没有修改权限!", invoice, "invoice") ;
			}
			//根据id检索旧数据信息
			Invoice oldInvoice = invoiceDao.find(invoice.getInvId());
			if(null!=oldInvoice){
				//如果修改了发票编号，则先验证修改过后的发票编号是否已经存在
				if(!oldInvoice.getInvoiceId().equals(invoice.getInvoiceId())){
					Invoice inv = invoiceDao.findByInvoiceId(invoice.getInvoiceId());
					if(null!=inv){
						throw new ParameterException("updateInvoiceInfo","invoiceId","发票编号已经存在!",invoice,"invoice");
					}
				}
			}
			invoiceDao.update(invoice);
		}
		
	}
	/**
	 * 权限验证
	 * @param invoiceId
	 * @param invid
	 * @return
	 */
	private boolean validateAuth(String invoiceId,Integer empId){

		if(null!=invoiceId&&null!=empId){
			
			Invoice invoice = invoiceDao.findByInvoiceId(invoiceId);
			
			if(null==invoice){
				return false;
			}
			
			User emp = userDao.find(empId);
			
			if(null==emp){
				return false;
			}
			
			Company com = companyDao.find(emp.getCompanyCode());
			
			if(null==com){
				return false;
			}
			
			Authority authority = new Authority();
			
			authority.setInvId(invoice.getInvId());
			
			authority.setEmpId(empId);
			
			authority.setComId(com.getId());
			
			Authority au = authorityDao.findInv(authority);
			
			if(null!=au){
				return true;
			}else{
				return false;
			}
			
		}
		
		
		return false;
	}
	/**
	 * 
	 * @param invoiceId
	 * @param invid
	 * @return
	 */
	private boolean validateAuth2(Integer invId,Integer empid){
		
		if(null!=invId&&null!=empid){
			
			Invoice invoice = invoiceDao.find(invId);
			
			if(null==invoice){
				return false;
			}
			
			User emp = userDao.find(empid);
			
			if(null==emp){
				return false;
			}
			
			Company com = companyDao.find(emp.getCompanyCode());
			
			if(null==com){
				return false;
			}
			
			Authority authority = new Authority();
			
			authority.setInvId(invId);
			
			authority.setEmpId(empid);
			
			authority.setComId(com.getId());
			
			Authority au = authorityDao.findInv(authority);
			
			if(null!=au){
				return true;
			}else{
				return false;
			}
			
		}
		return false;
	}

	@Override
	public void addInvoice(InvoiceBean bean) throws BusinessException,
			ParameterException{
		//数据信息验证
		validate(bean);
		
		Company company = companyDao.find(bean.getEmplpoyee().getCompanyCode());
		if(null==company){
			throw new BusinessException("addInvoiceInfo", "添加失败", bean.getInvoice(), "invoice");
		}
		Authority auth = new Authority();
		auth.setComId(company.getId());
		auth.setEmpId(bean.getEmplpoyee().getId());
		//添加数据库
		invoiceDao.add(bean.getInvoice());
		auth.setInvId(invoiceDao.findByInvoiceId(bean.getInvoice().getInvoiceId()).getInvId());
		//权限绑定
		authorityDao.addInv(auth);
		
	}
	
	private void validate(InvoiceBean bean) throws ParameterException{
		if(null!=bean&&null!=bean.getInvoice()){
			Invoice invoice = bean.getInvoice();
			//合同编号验证
			if(null==invoice
					||StringUtils.isBlank(invoice.getContractId())
					||StringUtils.isEmpty(invoice.getContractId())){
				throw new ParameterException("addInvoiceInfo", "contractId", "合同编号不能为空", invoice, "invoice");
			}
			//数据库存在验证
			Contract contract = contractDao.findByContractId(invoice.getContractId());
			if(null==contract){
				throw new ParameterException("addInvoiceInfo", "contractId", "合同编号不存在", invoice, "invoice");
			}
			//开票批次为空验证
			if(null==invoice.getInvoiceIndex()
					||StringUtils.isBlank(String.valueOf(invoice.getInvoiceIndex()))
					||StringUtils.isEmpty(String.valueOf(invoice.getInvoiceIndex()))){
				throw new ParameterException("addInvoiceInfo", "invoiceIndex", "开票批次不能为空", invoice, "invoice");
			}
			//数据库存在验证
			List<Invoice> invoiceList = invoiceDao.findByContractId(invoice.getContractId());
			if(null!=invoiceList&&invoiceList.size()>0){
				for (Invoice invoice2 : invoiceList) {
					if(invoice.getInvoiceIndex()==invoice2.getInvoiceIndex()){
						throw new ParameterException("addInvoiceInfo", "invoiceIndex", "开票批次已经存在", invoice, "invoice");
					}
				}
			}
			//发票编号验证
			if(null!=invoice.getInvoiceId()
					&&!StringUtils.isBlank(invoice.getInvoiceId())
					&&!StringUtils.isEmpty(invoice.getInvoiceId())){
				Invoice inv = invoiceDao.findByInvoiceId(invoice.getInvoiceId());
				if(null!=inv){
					throw new ParameterException("addInvoiceInfo", "invoiceId", "发票编号已经存在", invoice, "invoice");
				}
			}
		}
		
	}

}
