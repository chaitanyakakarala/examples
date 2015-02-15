package trail;

import java.util.Properties;

import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

public class Sample {

	public static void main(String[] args) throws Exception{

		String host = "smtp.gmail.com";
	    String username = "chaitanya.kakarala";
	    String password = "28july90";
	    Properties props = new Properties();
	    props.put("mail.protocol.ssl.trust", host);
	    // set any needed mail.smtps.* properties here
	    Session session = Session.getInstance(props);
	    MimeMessage msg = new MimeMessage(session);
	    msg.setContent("trail", "trail");
	    // set the message content here
	    msg.setRecipients(RecipientType.CC, "chaitanya.kakarala@gmail.com");
	    Transport t = session.getTransport("smtps");
	    try {
		t.connect(host, username, password);
		t.sendMessage(msg, msg.getAllRecipients());
	    } finally {
		t.close();
	    }
	}

}
