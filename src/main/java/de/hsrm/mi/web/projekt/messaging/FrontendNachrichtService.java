package de.hsrm.mi.web.projekt.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class FrontendNachrichtService {
    private Logger logger = LoggerFactory.getLogger(FrontendNachrichtEvent.class);

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void sendEvent(FrontendNachrichtEvent ev){
        logger.info("Event: " + ev.operation + " - " + ev.id);
        messagingTemplate.convertAndSend("/topic/tour", ev);
    }
}
