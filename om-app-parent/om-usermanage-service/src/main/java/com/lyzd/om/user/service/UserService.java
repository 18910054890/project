package com.lyzd.om.user.service;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lyzd.om.user.model.User;
import com.lyzd.om.user.repository.UserRepository;
import com.lyzd.om.user.sdk.command.CreateUserCommand;
import com.lyzd.om.user.sdk.command.UpdateUserNameCommand;
import com.lyzd.om.user.sdk.representation.UserRepresentation;

/**
 * @author Thinker
 *
 */
@Slf4j
@Component
public class UserService {
	
	@Autowired
    private UserRepository userRepository;
    
    
    public UserService() {
	}

    @Autowired
	public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public String create(CreateUserCommand command) {
        User user = User.create(command.getName(), command.getAge());
        userRepository.save(user);
        log.info("Created user[{}].", user.getId());
        return user.getId();
    }
    
    @Transactional(readOnly = true)
    public UserRepresentation byId(String id) {
        return userRepository.byId(id).toRepresentation();
    }


    @Transactional
    public String updateUserName(String userId, UpdateUserNameCommand command) {
        User user = userRepository.byId(userId);
        user.updateName(command.getNewName());
        userRepository.save(user);
        log.info("Updated name to {} for user[{}].", user.getName(), user.getId());
        return user.getId();
    }
}
