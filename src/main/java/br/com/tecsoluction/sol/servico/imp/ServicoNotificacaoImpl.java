package br.com.tecsoluction.sol.servico.imp;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tecsoluction.sol.servico.ServicoNotificacao;

@Service
public class ServicoNotificacaoImpl implements ServicoNotificacao {

	  public static final String NOTIFY_MSG_SESSION_KEY = "siteNotificationMessages";

	    @Autowired
	    private HttpSession httpSession;

	
	@Override
	public void addInfoMessage(String msg) {
	       addNotificacaoMessage(NotificacaoMessageType.INFO, msg);
	   		
	}

	@Override
	public void addErrorMessage(String msg) {
	       addNotificacaoMessage(NotificacaoMessageType.ERROR, msg);
		
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	private void addNotificacaoMessage(NotificacaoMessageType type, String msg) {
        List<NotificacaoMessage> notifyMessages = (List<NotificacaoMessage>)
                httpSession.getAttribute(NOTIFY_MSG_SESSION_KEY);
       
        if (notifyMessages == null) {
            notifyMessages = new ArrayList<NotificacaoMessage>();
        }
        
        notifyMessages.add(new NotificacaoMessage(type, msg));
        httpSession.setAttribute(NOTIFY_MSG_SESSION_KEY, notifyMessages);
    }

    public enum NotificacaoMessageType {
        INFO,
        ERROR
    }

    public class NotificacaoMessage {
        NotificacaoMessageType type;
        String text;

        
        public NotificacaoMessage(NotificacaoMessageType type, String text) {
            this.type = type;
            this.text = text;
        }

        public NotificacaoMessageType getType() {
            return type;
        }

        public String getText() {
            return text;
        }
    
    }	
	
	
	
	
	

}
