package entity;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.Folder;
import javax.mail.Store;
import com.sun.mail.imap.IMAPFolder;

public class Mail {
	
	String this_rcv_mail;
	String this_rcv_pwd;
	
	public Mail(String s1, String s2) { this.this_rcv_mail = s1; this_rcv_pwd = s2;}
	
	String that_snd_mail;
	String that_snd_pwd;
	String that_rcv_mail;
	String mail_cxt;
	
	public Mail(String s1, String s2, String s3, String s4) {
		that_snd_mail = s1;
		that_snd_pwd = s2;
		that_rcv_mail = s3;
		mail_cxt = s4;
	}
  
	//@Test
	public void SendMail() throws Exception {
		String host = "smtp.qq.com";// "smtp.gmail.com" 
		String port = "25"; //"465"
		Properties props = new Properties();
		props.setProperty("mail.smtp.host", host); 
		props.setProperty("mail.smtp.port", port);
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.ssl.enable", "false");//"true"
		props.setProperty("mail.smtp.connectiontimeout", "5000");
		
		//final String user = "1098458782@qq.com";  //"***@gmail.com"
		final String user = that_snd_mail;
		final String pwd = that_snd_pwd;
		Session session = Session.getInstance(props, new Authenticator() { //Default可能不可以
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				
				return new PasswordAuthentication(user,pwd);
			}
		});
		session.setDebug(true);
		Transport transport = session.getTransport("smtp");//"smtps"
		transport.connect(host,user,pwd); 
		
		MimeMessage message = new MimeMessage(session);
		
		setMailContent(message);
		
		message.setSubject("邮件标题");
		
		message.setFrom(new InternetAddress(user,"zz")); 
		message.addRecipients(Message.RecipientType.TO,new InternetAddress[]{
				//new InternetAddress("648701545@qq.com","hh"),
				new InternetAddress(that_rcv_mail, "hh")
				
		});
		message.saveChanges();
		
		transport.send(message);
		
		transport.close();	
		
	}
	
	private void setMailContent(MimeMessage message) throws MessagingException {
		
		Multipart part = new MimeMultipart();
		
		BodyPart bodypart1 = new MimeBodyPart();
		bodypart1.setText(mail_cxt); //设置邮件内容
		part.addBodyPart(bodypart1 );
		
		message.setContent(part);
	}
	

	public int receive()  throws Exception{
		
		String protocol = "imap";
        boolean isSSL = true;
        String host = "imap.qq.com";
        //int port = 995;
        int port = 993;
        //String username = "1098458782@qq.com";
        String username = this_rcv_mail;
        String password = this_rcv_pwd;
 
        Properties props = new Properties();
        props.put("mail.imap.ssl.enable", isSSL);
        props.put("mail.imap.host", host);
        props.put("mail.imap.port", port);
 
        Session session = Session.getDefaultInstance(props);
 
        Store store = null;
        
        store = session.getStore(protocol);
        store.connect(username, password);
 
        IMAPFolder folder = (IMAPFolder) store.getFolder("INBOX"); 
        folder.open(Folder.READ_WRITE); 
        
        int size = folder.getMessageCount();
        //Message message = folder.getMessage(size);
        
        int unread = folder.getUnreadMessageCount();
       System.out.println("未读数量: " + unread);
       //System.out.println("邮件数量: " + folder.getMessageCount());
        
       int newMsg = folder.getNewMessageCount();
	   //System.out.println("新邮件: " + newMsg);
	   return unread;
		
	}
	
}