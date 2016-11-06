package com.util;

import java.util.Date;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import com.exception.BusinessException;
import com.model.Email;

public class SendEmail extends  Thread{

	public static void send(Email mail) throws BusinessException {
		
		HtmlEmail email = new HtmlEmail();
		try {
			email.setHostName(mail.getMailSmtpHost());
			// 字符编码集的设置
			email.setCharset("utf8");
			// 收件人的邮箱
			email.addTo(mail.getRmailUser());
			// 发送人的邮箱
			email.setFrom(mail.getSmailUser(), mail.getPassword());
			// 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和密码
			email.setAuthentication(mail.getSmailUser(), mail.getPassword());
			// 要发送的邮件主题
			email.setSubject(mail.getHead());
			// 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签
			email.setMsg(mail.getMessage());
			// 发送
			email.send();
			
		} catch (EmailException e) {
			throw new BusinessException("", "邮件发送失败!", null, "");
		}
	}

	public static void main(String[] args) {

		Email mail = new Email();
		mail.setMailSmtpAuth("true");
		mail.setMailSmtpHost("smtp.163.com");
		mail.setSmailUser("matchmen_1@163.com");
		mail.setPassword("matchmen111");
		mail.setRmailUser("1142229061@qq.com");
		mail.setHead("测试邮件");
		mail.setMessage("测试邮件,拉屎去了！"+new Date());
		try {
			send(mail);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
