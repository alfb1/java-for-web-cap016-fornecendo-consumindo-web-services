package br.com.javaparaweb.financeiro.web.util;

import java.util.*;

import javax.mail.Session;
import javax.naming.*;

import org.apache.commons.mail.*;

import br.com.javaparaweb.financeiro.util.UtilException;

public class EmailUtil 
{
   public void enviarEmail( String de, String para, String assunto, String mensagem) throws UtilException
   {
	   try {
		   Context initialContext = new InitialContext();
		   Context envContext = (Context) initialContext.lookup("java:comp/env");
		   
		   Session session = (Session) envContext.lookup("mail/Session");
		   
		   SimpleEmail email = new SimpleEmail();
		   email.setMailSession(session);
		   
		   if ( de != null ) {
			   email.setFrom(de);
		   }else {
			   Properties p = session.getProperties();
			   email.setFrom(p.getProperty("mail.smtp.user" ));
		   }
		   
		   email.addTo(para);
		   email.setSubject(assunto);
		   email.setMsg(mensagem);
		   email.setSentDate( new Date() );
		   email.send();
	   }catch ( EmailException | NamingException e) {
		   throw new UtilException(e);
	   }
   }
}
