package com.lyzd.om.user.sdk.event;

//import com.lyzd.om.spring.common.event.messaging.rabbit.OMRabbitListener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.stereotype.Component;

/**
 * @author Thinker
 *
 */
//@Component
//@OMRabbitListener
public class UserRabbitListener {
	
    private UserEventHandler eventHandler;

    public UserRabbitListener(UserEventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }


    @RabbitHandler
    public void on(UserNameUpdatedEvent event) {
        eventHandler.updateUserName(event);
    }

}
