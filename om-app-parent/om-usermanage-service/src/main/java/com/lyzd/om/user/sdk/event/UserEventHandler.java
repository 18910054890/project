package com.lyzd.om.user.sdk.event;

import com.lyzd.om.user.model.User;
import com.lyzd.om.user.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Thinker
 *
 */
@Slf4j
//@Component
public class UserEventHandler {

    private UserRepository repository;

    public UserEventHandler(UserRepository repository) {
        this.repository = repository;
    }


    @Transactional
    public void updateUserName(UserNameUpdatedEvent event) {
        User user = repository.byId(event.getUserId());
        user.updateName(event.getNewName());
        //repository.save(user);
        log.info("User[{}]  updated to {} due to user name  change.", event.getUserId(), event.getNewName());
    }
}
