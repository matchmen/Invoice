package com.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bean.UserBean;
import com.controller.BaseController;
import com.exception.BusinessException;
import com.exception.ParameterException;
import com.exception.SystemException;
import com.model.BaseInfo;
import com.model.Company;
import com.model.User;
import com.service.UserService;

@Controller
@RequestMapping("/user.do")
@SessionAttributes("currUser")
public class UserContreller extends BaseController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(params="method=login")
	public String login(String username,String password,ModelMap map) throws SystemException{
		
		User user = userService.login(username, password);
		
		if(null!=user){
			
			map.addAttribute("currUser", user);
			
			return "main";
			
		}else{
			map.addAttribute("errorMsg", "用户名或密码不正确!");
			return "login";
		}
	}
	@RequestMapping(params="method=logout")
	public String logout(HttpServletRequest request,ModelMap map) throws SystemException{
		return "login";
	}
	@RequestMapping(params="method=addUser")
	public String addUser(User user,ModelMap map) throws ParameterException, SystemException{
		
		User currUser = (User)map.get("currUser");
		
		userService.addUser(user,currUser);
		
		return "addUserSuccess";
	}
	@RequestMapping(params="method=checkUserInfo")
	public String checkUserInfo(ModelMap map){
		
		User currUser = (User)map.get("currUser");
		
		User em = userService.findUser(currUser.getId());
		
		map.addAttribute("currUser", em);
		
		return "checkUserInfo";
	}
	@RequestMapping(params="method=updatePassword")
	public String updatePassword(HttpSession session,ModelMap map,String password,String oldPassword) throws ParameterException, SystemException{
		
		User currUser = (User)map.get("currUser");
		
		userService.updatePassword(currUser, oldPassword,password);
		
		session.removeAttribute("currUser");
		
		return "updatePasswordSuccess";
	}
	@RequestMapping(params="method=updateUserInfo")
	public String updateUserInfo(HttpSession session,ModelMap map,User user) throws ParameterException, SystemException{
		
		User em = (User)map.get("currUser");
		
		user.setId(em.getId());
		
		em = userService.updateUserInfo(user);
		
		session.removeAttribute("currUser");
		
		session.setAttribute("currUser", em);
		
		return "updateUserInfoSuccess";
	}
	@RequestMapping(params="method=loginPage")
	public String loginPage(){
		return "login";
	}
	@RequestMapping(params="method=addUserPage")
	public String addUserPage(){
		
		return "addUser";
	}
	@RequestMapping(params="method=setCpInfoPage")
	public String setCpInfoPage(){
		return "setCpInfo";
	}
	@RequestMapping(params="method=mainPage")
	public String mainPage(){
		return "main";
	}
	@RequestMapping(params="method=updatePasswordPage")
	public String updatePasswordPage(){
		return "updatePassword";
	}
	@RequestMapping(params="method=updateUserInfoPage")
	public String updateUserInfoPage(ModelMap map,User user){
		User em = (User)map.get("currUser");
		map.addAttribute("currUser", em);
		return "updateUserInfo";
	}
	
	@RequestMapping(params="method=removeUser")
	public String removeUser(ModelMap map,String str) throws ParameterException, BusinessException{
		
		User em = (User)map.get("currUser");
		
		userService.removeUser(str,em);
		
		return "success";
	}
	
	@RequestMapping(params="method=removeUserPage")
	public String removeUserPage(){
		
		return "removeUser";
	}
	@RequestMapping(params="method=registerType")
	public String registerType(UserBean userBean,User user,ModelMap map) throws ParameterException, SystemException{
		
		userBean = new UserBean();
		
		userBean.setUser(user);
		
		map.addAttribute("userBean", userBean);
		
		return "register";
	}
	
	@RequestMapping(params="method=registerTypePage")
	public String registerTypePage(){
		return "registerType";
	}
	
	@RequestMapping(params="method=register")
	public String register(Company company,User user,ModelMap map) throws ParameterException, SystemException{
		
		UserBean bean = new UserBean();
		bean.setCompany(company);
		bean.setUser(user);
		userService.register(bean);
		return "registerSuccess";
	}
	
	@RequestMapping(params="method=setBaseInfoPage")
	public String setBaseInfoPage(ModelMap map){
		
		User user = (User)map.get("currUser");
		
		BaseInfo baseInfo = userService.findBaseInfo(user);
		
		map.addAttribute("baseInfo", baseInfo);
		
		return "setBaseInfo";
	}
	@RequestMapping(params="method=setBaseInfo")
	public String setBaseInfo(BaseInfo baseInfo,ModelMap map) throws SystemException, ParameterException{
		
		User user = (User)map.get("currUser");
		
		userService.setBaseInfo(baseInfo, user);
		
		return "setBaseInfoSuccess";
	}
	
}
