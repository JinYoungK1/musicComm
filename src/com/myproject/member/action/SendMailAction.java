package com.myproject.member.action;

import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SendMailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		String sender = request.getParameter("sender");
		String receiver = request.getParameter("receiver");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		response.setContentType("text/html;charset=UTF-8");
		System.out.println("보내는 사람 : " + sender);
		PrintWriter out = response.getWriter();
		try {
			Properties properties = System.getProperties();
			properties.put("mail.smtp.starttls.enable", "true"); // gmail�� ������ true ����
			properties.put("mail.smtp.host", "smtp.gmail.com"); // smtp ���� �ּ�
			properties.put("mail.smtp.auth", "true"); // gmail�� ������ true ����
			properties.put("mail.smtp.port", "587"); // gmail ��Ʈ
			properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
			Authenticator auth = new GoogleAuthentication();
			Session s = Session.getDefaultInstance(properties, auth);
			System.out.println("프로퍼티 : " + s);
			//Session s = Session.getdefultInstance(properties, auth);
			Message message = new MimeMessage(s);
			System.out.println("메세지 : " + message);
			Address sender_address = new InternetAddress(sender);
			System.out.println("보내는 사람 주소 : " + sender_address);
			Address receiver_address = new InternetAddress(receiver);
			message.setHeader("content-type", "text/html;charset=UTF-8");
			message.setFrom(sender_address);
			message.addRecipient(Message.RecipientType.TO, receiver_address);
			message.setSubject(subject);
			message.setContent(content, "text/html;charset=UTF-8");
			message.setSentDate(new java.util.Date());
			Transport.send(message);
			
			out.print("<script>");
			out.print("alert('메일이 정상적으로 전송되었습니다');");
			out.print("location.href='./MyBoardMain.mu';");
			out.print("</script>");
			out.close();
		} catch (Exception e) {
			out.println("SMTP 서버가 잘못 설정되었거나, 서비스에 문제가 있습니다.");
			e.printStackTrace();
		}
		return null;
	}

}
