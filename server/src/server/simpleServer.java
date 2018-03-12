package server;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class simpleServer {

    /*
     * 
     * javax.mail.Session：上下文环境信息，如服务器的主机名、端口号、协议名称等
     * javax.mail.Message：邮件模型，发送邮件和接收邮件的媒介，封装了邮件的信息，如发件人、收件人、邮件标题、邮件内容等
     * javax.mail.Transport：连接邮件SMTP服务器，发送邮件
     * javax.mail.Store：连接邮件POP3、IMAP服务器，收取邮件
     */
    public static void main(String[] args) throws MessagingException {
        // TODO Auto-generated method stub
        Properties props = new Properties();
        //start debugging
        props.setProperty("mail.debug", "true");
        //auth
        props.setProperty("mail.smtp.auth", "true");
        //server and the zhuji
        props.setProperty("mail.host", "imap-mail.outlook.com");
        //send protocal name
        props.setProperty("mail.transport.protocal", "smtp");

        //environ
        Session session = Session.getInstance(props);

        //create mail instance
        Message msg = new MimeMessage(session);
        msg.setSubject("javaMail Testing");
        //content setting
        msg.setText("From JavaMail");
        //set sender
        msg.setFrom(new InternetAddress("sun201709@outlook.com"));

        Transport transport = session.getTransport();
        //connect to mail server
        transport.connect("sun201709", "Santee0510");

        transport.sendMessage(msg,
                new Address[] { new InternetAddress("sun.2164@osu.edu") });

        transport.close();

    }

}
