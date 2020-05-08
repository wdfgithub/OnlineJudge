package util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.Security;
import java.util.Date;
import java.util.Properties;

public class EmailUtil {
	public static void sendEmail(String email, String subject, String content) throws MessagingException {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

        final Properties properties = new Properties();
        
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\Administrator\\workspace\\OnlineJudge\\config\\mail\\mail.properties"));
			properties.load(bufferedReader);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

        String username = properties.getProperty("mail.username");
        String password = properties.getProperty("mail.password");
        String domain = properties.getProperty("mail.domain");
        Session session = Session.getDefaultInstance(properties, new Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(properties.getProperty("mail.username"), password);
            }

        });

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(username + "@" + domain));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
        msg.setSubject(subject);
        msg.setText(content);
        msg.setSentDate(new Date());
        Transport.send(msg);
    }
}
