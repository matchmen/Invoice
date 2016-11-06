package com.model;

public class Email {
	/**
	 * 收件人邮箱号
	 */
	private String rmailUser;
	/**
	 * 发件人邮箱号
	 */
	private String smailUser;
	/**
	 * mail.smtp.auth
	 */
	private String mailSmtpAuth;
	/**
	 * mail.smtp.host
	 */
	private String mailSmtpHost;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 邮件信息
	 */
	private String message;
	/**
	 * 邮件标题
	 */
	private String head;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getRmailUser() {
		return rmailUser;
	}

	public void setRmailUser(String rmailUser) {
		this.rmailUser = rmailUser;
	}

	public String getSmailUser() {
		return smailUser;
	}

	public void setSmailUser(String smailUser) {
		this.smailUser = smailUser;
	}

	public String getMailSmtpAuth() {
		return mailSmtpAuth;
	}

	public void setMailSmtpAuth(String mailSmtpAuth) {
		this.mailSmtpAuth = mailSmtpAuth;
	}

	public String getMailSmtpHost() {
		return mailSmtpHost;
	}

	public void setMailSmtpHost(String mailSmtpHost) {
		this.mailSmtpHost = mailSmtpHost;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
