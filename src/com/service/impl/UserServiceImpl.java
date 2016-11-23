package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bean.UserBean;
import com.dao.AuthorityDao;
import com.dao.CompanyDao;
import com.dao.BaseInfoDao;
import com.dao.SystemInfoDao;
import com.dao.UserDao;
import com.exception.BusinessException;
import com.exception.ParameterException;
import com.exception.SystemException;
import com.model.Company;
import com.model.BaseInfo;
import com.model.SystemInfo;
import com.model.User;
import com.service.UserService;
import com.util.StringUtils;
@Repository("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private BaseInfoDao baseInfoDao;	
	@Autowired
	private AuthorityDao authorityDao;	
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private SystemInfoDao systemInfoDao;
	
	@Override
	public User login(String str,String password) throws SystemException {
		//查询ID
		Integer id = userDao.findId(str);
		
		if(null!=id){
			//員工信息
			User ep = userDao.find(id);
			//判断查询结果,检验公司代码是否已经注册
			if(null!=ep && ep.getPassword().equals(StringUtils.md5Password(password))){
				return ep;
			}else{
				return null;
			}
		}
		else{
			return null;
		}
	}

	@Override
	public void logout(User employee) {

	}

	@Override
	public User findUser(Integer id) {
		return userDao.find(id);
	}

	@Override
	public void addUser(User user,User manager) throws ParameterException, SystemException {
		//数据验证
		validate(user);
		
		if(null!=manager){
			Integer comID = userDao.findComId(manager.getId());
			BaseInfo info = baseInfoDao.findByComId(comID);
			if(null!=comID){
				//设置密码
				user.setPassword(info.getInitPassword());
				//设为公司所属
				user.setIsCompany(true);
				//添加
				userDao.add(user);
				Integer userID = userDao.findId(user.getPhoneNumber());
				if(null!=userID){
					//绑定公司
					userDao.addBind(comID, userID);
				}else{
					throw new ParameterException("addUser", "", "添加失败!", "", "");
				}
			}else{
				throw new ParameterException("addUser", "", "添加失败!", "", "");
			}
		}else{
			throw new ParameterException("addUser", "", "添加失败!", "", "");
		}
		
	}
	
	private void validate(User user) throws ParameterException{
		//为空验证
		if(null==user.getEmployeeId()
				||StringUtils.isBlank(user.getEmployeeId())
				||StringUtils.isEmpty(user.getEmployeeId())){
			throw new ParameterException("addUser", "employeeId", "员工号不能为空!", user, "user");
		}
		//为空验证
		if(null==user.getUsername()
				||StringUtils.isBlank(user.getUsername())
				||StringUtils.isEmpty(user.getUsername())){
			throw new ParameterException("addUser", "userId", "员工姓名不能为空!", user, "user");
		}
		//电话号码验证
		if(null==user.getPhoneNumber()
				||StringUtils.isBlank(user.getPhoneNumber())
				||StringUtils.isEmpty(user.getPhoneNumber())){
			throw new ParameterException("addUser", "phoneNumber", "电话号码不能为空!", user, "employee");
		}
		//格式验证
				if(!StringUtils.isNumber(user.getPhoneNumber())){
					throw new ParameterException("addUser", "phoneNumber", "电话号码格式正确!", user, "user");
				}
				Integer id = userDao.findId(user.getPhoneNumber());
				//数据库存在验证
				if(null!=id){
					throw new ParameterException("addUser", "phoneNumber", "电话号码已经被注册!", user, "user");
				}
				//个人邮箱验证
				if(null==user.getEmail()
						||StringUtils.isBlank(user.getEmail())
						||StringUtils.isEmpty(user.getEmail())){
					throw new ParameterException("addUser", "email", "个人邮箱不能为空!", user, "user");
				}
				//邮箱格式验证
				if(!StringUtils.isEmail(user.getEmail())){
					throw new ParameterException("addUser", "email", "邮箱格式不正确!", user, "user");
				}
				id = userDao.findId(user.getEmail());
				//数据库存在验证
				if(null!=id){
					throw new ParameterException("addUser", "email", "邮箱已经被注册!", user, "user");
				}
				//密码验证
				/*if(null==user.getPassword()
						||StringUtils.isBlank(user.getPassword())
						||StringUtils.isEmpty(user.getPassword())){
					throw new ParameterException("adduser", "password", "密码不能为空!", user, "user");
				}*/
			}

	@Override
	public void updatePassword(User user,String oldPassword,String password) throws ParameterException, SystemException {
		
		//旧密码为空验证
		if(null==oldPassword
				||StringUtils.isBlank(oldPassword)
				||StringUtils.isEmpty(oldPassword)){
			throw new ParameterException("updatePassword", "oldPassword", "旧密码不能为空!", null, null);
		}
		//新密码为空验证
		if(null==password
				||StringUtils.isBlank(password)
				||StringUtils.isEmpty(password)){
			throw new ParameterException("updatePassword", "password", "密码不能为空!", user.getPassword(), "password");
		}
		//旧密码是否一致
		if(!StringUtils.md5Password(oldPassword).equals(user.getPassword())){
			throw new ParameterException("updatePassword", "oldPassword", "旧密码验证错误!", null, null);
		}
		
		userDao.updatePw(StringUtils.md5Password(password), user.getId());
	}

	@Override
	public User updateUserInfo(User user) throws ParameterException {
		
		User olduser = userDao.find(user.getId());
		
		//为空验证
		if(null==user.getUsername()
				||StringUtils.isBlank(user.getUsername())
				||StringUtils.isEmpty(user.getUsername())){
			throw new ParameterException("updateUserInfo", "userId", "员工姓名不能为空!", user, "currUser");
		}
		//电话号码验证
		if(null==user.getPhoneNumber()
				||StringUtils.isBlank(user.getPhoneNumber())
				||StringUtils.isEmpty(user.getPhoneNumber())){
			throw new ParameterException("updateUserInfo", "phoneNumber", "电话号码不能为空!", user, "currUser");
		}
		//格式验证
		if(!StringUtils.isNumber(user.getPhoneNumber())){
			throw new ParameterException("updateUserInfo", "phoneNumber", "电话号码格式正确!", user, "currUser");
		}
		
		if(null!=olduser){
			if(!olduser.getPhoneNumber().equals(user.getPhoneNumber())){
				List<User> userList = userDao.findList(user.getPhoneNumber());
				//数据库存在验证
				if(null!=userList&&userList.size()>0){
					throw new ParameterException("updateUserInfo", "phoneNumber", "电话号码已经被注册!", user, "currUser");
				}
			}
		}
		//个人邮箱验证
		if(null==user.getEmail()
				||StringUtils.isBlank(user.getEmail())
				||StringUtils.isEmpty(user.getEmail())){
			throw new ParameterException("updateuserInfo", "email", "个人邮箱不能为空!", user, "currUser");
		}
		//邮箱格式验证
		if(!StringUtils.isEmail(user.getEmail())){
			throw new ParameterException("updateuserInfo", "email", "邮箱格式不正确!", user, "currUser");
		}
		
		if(null!=olduser){
			if(!olduser.getEmail().equals(user.getEmail())){
				List<User> userList = userDao.findList(user.getEmail());
				//数据库存在验证
				if(null!=userList&&userList.size()>0){
					throw new ParameterException("updateuserInfo", "email", "邮箱已经被注册!", user, "currUser");
				}
			}
		}
		
		userDao.update(user);
		
		return userDao.find(user.getId());
	}

	@Override
	public void removeUser(String str,User user) throws ParameterException, BusinessException {
		//为空验证
		if(null==str||StringUtils.isBlank(str)||StringUtils.isEmpty(str)){
			throw new ParameterException("removeuser", "str", "请输入邮箱或手机号!", str, "str");
		}
		//删除者身份验证
		if(!user.getIsAdmin()&&!user.getIsCompany()){
			throw new BusinessException("error", "没有删除权限", "", ""); 
		}
		
		User delEmp =  userDao.findByStr(str);
		//被删除者身份验证
		if(null==delEmp){
			throw new ParameterException("removeUser", "str", "邮箱或手机号不存在!", str, "str");
		}
		//根据删除者id查询公司信息ID
		Integer comIdByA = userDao.findComId(user.getId());
		//根据删除者信息查询公司信息
		Integer comIdByB = userDao.findComId(delEmp.getId());
		//删除者和被删除者是否同一家公司验证
		if(comIdByA!=comIdByB){
			throw new ParameterException("removeUser", "str", "邮箱或手机号不存在!", str, "str");
		}
		//解除与公司身份关系
		userDao.removeBind(delEmp.getId());
		userDao.leaveCompany(delEmp.getId());
		//解除数公司的数据信息绑定
		List<Integer> idConList = authorityDao.findConByEmpId(delEmp.getId());
		
		for (Integer id1 : idConList) {
			authorityDao.removeCon(id1,comIdByA);
		}
		List<Integer> idInvList = authorityDao.findInvByEmpId(delEmp.getId());
		for (Integer id2 : idInvList) {
			authorityDao.removeInv(id2,comIdByA);
		}

	}

	@Override
	public void register(UserBean userBean) throws SystemException, ParameterException {
		//公司ID
		int comId = 0;
		//用户ID
		int userId = 0;
		//基本信息对象
		BaseInfo baseInfo =new BaseInfo();
		//是否是公司注册
		if(userBean.getUser().getIsCompany()){
			//公司数据验证			
			validateCompanyInfo(userBean);
		}
		//用户数据验证
		validateuser(userBean);
		//添加公司信息到db
		if(userBean.getUser().getIsCompany()){
			//添加
			companyDao.add(userBean.getCompany());
			//查找插入信息
			Company com = companyDao.find(userBean.getCompany().getCompanyCode());
			//为null验证
			if(null!=com){
				comId = com.getId();
			}
		}
		
		//密码加密
		userBean.getUser().setPassword(StringUtils.md5Password(userBean.getUser().getPassword()));
		//设为管理员
		userBean.getUser().setIsAdmin(true);
		//添加到db
		userDao.add(userBean.getUser());
		//查找插入信息
		User uu = userDao.findByStr(userBean.getUser().getPhoneNumber());
		//为null验证
		if(null!=uu){
			userId = uu.getId();
		}
		//用户与公司绑定
		if(userBean.getUser().getIsCompany()){
			userDao.addBind(comId, userId);
		}
		
		
		baseInfo.setCompanyId(comId);
		baseInfo.setUserId(userId);
		if(userBean.getUser().getIsCompany())
			baseInfo.setUserId(0);
		SystemInfo info = systemInfoDao.find();
		if(null!=info){
		//初始密码设为000000
		baseInfo.setInitPassword(info.getUserInitPW());
		//初始化提示时间
		baseInfo.setCueTimeFinance(info.getCueTimeOfFinance());
		baseInfo.setCueTimeSales(info.getCueTimeOfSales());
		}
		//添加用户初始化信息
		baseInfoDao.add(baseInfo);
		
	}
	/**
	 * 用户信息验证
	 * @param bean
	 * @throws ParameterException
	 */
	private void validateuser(UserBean bean) throws ParameterException{
		if(null!=bean.getUser()){
			//为空验证
			if(null==bean.getUser().getUsername()
					||StringUtils.isBlank(bean.getUser().getUsername())
					||StringUtils.isEmpty(bean.getUser().getUsername())){
				throw new ParameterException("register", "userId", "姓名不能为空!", bean, "userBean");
			}
			//电话号码验证
			if(null==bean.getUser().getPhoneNumber()
					||StringUtils.isBlank(bean.getUser().getPhoneNumber())
					||StringUtils.isEmpty(bean.getUser().getPhoneNumber())){
				throw new ParameterException("register", "phoneNumber", "电话号码不能为空!", bean, "userBean");
			}
			//格式验证
			if(!StringUtils.isNumber(bean.getUser().getPhoneNumber())){
				throw new ParameterException("register", "phoneNumber", "电话号码格式正确!", bean, "userBean");
			}
			Integer id = userDao.findId(bean.getUser().getPhoneNumber());
			//数据库存在验证
			if(null!=id){
				throw new ParameterException("register", "phoneNumber", "电话号码已经被注册!", bean, "userBean");
			}
			//个人邮箱验证
			if(null==bean.getUser().getEmail()
					||StringUtils.isBlank(bean.getUser().getEmail())
					||StringUtils.isEmpty(bean.getUser().getEmail())){
				throw new ParameterException("register", "email", "个人邮箱不能为空!", bean, "userBean");
			}
			//邮箱格式验证
			if(!StringUtils.isEmail(bean.getUser().getEmail())){
				throw new ParameterException("register", "email", "邮箱格式不正确!", bean, "userBean");
			}
			id = userDao.findId(bean.getUser().getEmail());
			//数据库存在验证
			if(null!=id){
				throw new ParameterException("register", "email", "邮箱已经被注册!", bean, "userBean");
			}
			//密码验证
			if(null==bean.getUser().getPassword()
					||StringUtils.isBlank(bean.getUser().getPassword())
					||StringUtils.isEmpty(bean.getUser().getPassword())){
				throw new ParameterException("register", "password", "密码不能为空!", bean, "userBean");
			}
		}
	}
	/**
	 * 公司信息验证
	 * @param bean
	 * @throws ParameterException
	 */
	private void validateCompanyInfo(UserBean bean) throws ParameterException {
		
		//公司名称为空验证
		if(null==bean.getCompany().getCompanyName()
				||StringUtils.isBlank(bean.getCompany().getCompanyName())
				||StringUtils.isEmpty(bean.getCompany().getCompanyName())){
			throw new ParameterException("register", "comoanyName", "公司名称不能为空!", bean, "userBean");
		}
		Company companyNameByName = companyDao.findByOtherInfo(bean.getCompany().getCompanyName());
		//数据库存在判断
		if(null!=companyNameByName){
			throw new ParameterException("register", "comoanyName", "公司名称已经被注册!", bean, "userBean");
		}
		
		Company companyNameByCode = companyDao.find(bean.getCompany().getCompanyCode());
		//公司代码为空验证
		if(null==bean.getCompany().getCompanyCode()
				||StringUtils.isBlank(bean.getCompany().getCompanyCode())
				||StringUtils.isEmpty(bean.getCompany().getCompanyCode())){
			throw new ParameterException("register", "companyCode", "公司代码不能为空!", bean, "userBean");
		}
		//数据库是否存在验证
		if(null!=companyNameByCode){
			throw new ParameterException("register", "companyCode", "公司代码已经被注册!", bean, "userBean");
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
	public void setBaseInfo(BaseInfo baseInfo,User user) throws SystemException,
			ParameterException {
		//密码为空验证
		if(user.getIsCompany()&&(null==baseInfo.getInitPassword()
				||StringUtils.isBlank(baseInfo.getInitPassword())
				||StringUtils.isEmpty(baseInfo.getInitPassword()))){
			throw new ParameterException("setBaseInfo", "initPassword", "密码不能为空!", baseInfo, "baseInfo");
		}
		//财务开票提示时间为空验证
		if(null==baseInfo.getCueTimeFinance()){
			throw new ParameterException("setBaseInfo", "initPassword", "财务开票提示时间不能为空!", baseInfo, "baseInfo");
		}
		//数字验证
		if(!StringUtils.isNumber(baseInfo.getCueTimeFinance().toString())){
			throw new ParameterException("setBaseInfo", "cueTimeFinance", "财务开票提示时间不能为空!", baseInfo, "baseInfo");
		}
		//销售催款提示时间为空验证
		if(null==baseInfo.getCueTimeSales()){
			throw new ParameterException("setBaseInfo", "initPassword", "财务开票提示时间不能为空!", baseInfo, "baseInfo");
		}
		//数字验证
		if(!StringUtils.isNumber(baseInfo.getCueTimeSales().toString())){
			throw new ParameterException("setBaseInfo", "cueTimeSales", "财务开票提示时间不能为空!", baseInfo, "baseInfo");
		}
		//更新
		baseInfoDao.update(baseInfo);

	}

	private BaseInfo findBaseInfoByUserId(Integer id) {
		return baseInfoDao.findByUserId(id);
		
	}
	
	private BaseInfo findBaseInfoByComId(Integer id){
		return baseInfoDao.findByComId(id);
	}

	@Override
	public BaseInfo findBaseInfo(User user) {
		//属于公司
		if(user.getIsCompany()){
			//根据用户ID查询公司ID
			Integer comId = userDao.findComId(user.getId());
			
			return findBaseInfoByComId(comId);
		}else{
			return findBaseInfoByUserId(user.getId());
		}
		
	}
}
