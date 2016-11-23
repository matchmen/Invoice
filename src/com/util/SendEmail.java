package com.util;

import java.math.BigDecimal;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import com.exception.BusinessException;
import com.model.Email;
import com.model.Invoice;

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
			throw new BusinessException("error", "邮件发送失败!", null, "");
		}
	}
	
	public static String ctreateMail(Invoice invoice){
		
		StringBuffer sb = new StringBuffer("<html>")
		.append("<head>")
		.append("<title>发票信息</title>")
		.append("<meta http- equiv='Content-Type' content='text/html; charset=gb2312'>")
		.append("</head>")
		.append("<body>")
		.append("<table>")
		.append("<tr>")
			.append("<td>")
			.append("开票期次:")
			.append("</td>")
			.append("<td>")
			.append(invoice.getInvoiceIndex())
			.append("</td>")
		.append("</tr>")	
		.append("<tr>")		
			.append("<td>")
			.append("计划开票日期:")
			.append("</td>")
			.append("<td>")
			.append(invoice.getExpectMakeInvoceDate())
			.append("</td>")
			
			.append("<td>")
			.append("预计付款日期:")
			.append("</td>")
			.append("<td>")
			.append(invoice.getExpectPaymentDate())
			.append("</td>")
		.append("</tr>")	
		.append("<tr>")	
			.append("<td>")
			.append("开票公司名称:")
			.append("</td>")
			.append("<td>")
			.append(invoice.getMakeInvoiceCompanyName())
			.append("</td>")
		.append("</tr>")	
		.append("<tr>")		
			.append("<td>")
			.append("发票抬头:")
			.append("</td>")
			.append("<td>")
			.append(invoice.getCompanyNameOfPurchaser())
			
			.append("<td>")
			.append("买方纳税人编号:")
			.append("</td>")
			.append("<td>")
			.append(invoice.getTpIdeNumOfPurchaser())
		.append("</tr>")	
		.append("<tr>")		
			.append("<td>")
			.append("购买方开户行:")
			.append("</td>")
			.append("<td>")
			.append(invoice.getActualMakeInvoiceDate())
			
			.append("<td>")
			.append("购买方银行账户:")
			.append("</td>")
			.append("<td>")
			.append(invoice.getBankNumberOfPurchaser())
		.append("</tr>")	
		.append("<tr>")		
			.append("<td>")
			.append("发票内容:")
			.append("</td>")
			.append("<td>")
			.append(invoice.getInvoiceContent())
			
			.append("<td>")
			.append("发票类型:")
			.append("</td>")
			.append("<td>")
			.append(invoice.getInvoiceType())
		.append("</tr>")	
		.append("<tr>")	
			.append("<td>")
			.append("税额:")
			.append("</td>")
			.append("<td>")
			.append(invoice.getAmtOfTax())
			
			.append("<td>")
			.append("不含税金额:")
			.append("</td>")
			.append("<td>")
			.append(invoice.getAmtOfNoTax())
		.append("</tr>")	
		.append("<tr>")		
			.append("<td>")
			.append("总金额:")
			.append("</td>")
			.append("<td>")
			.append(invoice.getAmt())
			
			.append("<td>")
			.append("税率:")
			.append("</td>")
			.append("<td>")
			.append(invoice.getRate())
		.append("</tr>")	
		.append("<tr>")		
			.append("<td>")
			.append("卖方纳税人编号:")
			.append("</td>")
			.append("<td>")
			.append(invoice.getCompanyNameOfSale())
		.append("</tr>")	
		.append("<tr>")		
			.append("<td>")
			.append("卖方开户行:")
			.append("</td>")
			.append("<td>")
			.append(invoice.getBankTypeOfSale())
			
			.append("<td>")
			.append("卖方方银行账户:")
			.append("</td>")
			.append("<td>")
			.append(invoice.getBankNumberOfSale())
		.append("</tr>")	
		.append("<tr>")		
			.append("<td>")
			.append("发票寄送地址:")
			.append("</td>")
			.append("<td>")
			.append(invoice.getAddress())
			.append("</td>")
			
			.append("<td>")
			.append("收票人电话:")
			.append("</td>")
			.append("<td>")
			.append(invoice.getPhoneNumber())
			.append("</td>")
			
			.append("<td>")
			.append("收票人姓名:")
			.append("</td>")
			.append("<td>")
			.append(invoice.getAddresseeName())
			.append("</td>")
			
		.append("</tr>")
		.append("</table>")
		.append("</body>")
		.append("</html>");
		return sb.toString();
	}

	public static void main(String[] args) {
		
		Invoice invoce = new Invoice();
		invoce.setAddress("上海市");
		invoce.setAddresseeName("李庆沐");
		invoce.setAmt(new BigDecimal(10000));
		invoce.setAmtOfNoTax(new BigDecimal(11));
		invoce.setAmtOfTax(new BigDecimal(22));
		invoce.setBankNumberOfPurchaser("12221212123123");
		invoce.setBankNumberOfSale("2222222222222222222");
		invoce.setBankTypeOfPurchaser("中国工商银行");
		invoce.setBankTypeOfSale("上海银行");
		invoce.setCompanyNameOfPurchaser("廷桥");
		invoce.setCompanyNameOfSale("微软");
		invoce.setExpectMakeInvoceDate("2016/01/12");
		invoce.setExpectPaymentDate("2018/01/01");
		invoce.setInvoiceIndex(1);
		invoce.setInvoiceType("专用发票");
		invoce.setMakeInvoiceCompanyName("阿里");
		invoce.setPhoneNumber("15052233301");
		invoce.setRate(0.04);
		
		
		Email mail = new Email();
		mail.setMailSmtpAuth("true");
		mail.setMailSmtpHost("smtp.qq.com");
		mail.setSmailUser("matchmen_1@163.com");
		mail.setPassword("matchmen111");
		mail.setRmailUser("1142229061@qq.com");
		mail.setHead("测试邮件");
		mail.setMessage(ctreateMail(invoce));
		try {
			send(mail);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
}
