package com.lyzd.om.web.demo;

import static com.google.common.collect.ImmutableMap.of;
import static org.springframework.http.HttpStatus.CREATED;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lyzd.om.shared.utils.PagedResource;
import com.lyzd.om.user.sdk.command.CreateUserCommand;
import com.lyzd.om.user.sdk.command.UpdateUserNameCommand;
import com.lyzd.om.user.sdk.representation.UserRepresentation;
import com.lyzd.om.user.service.UserRepresentationService;
import com.lyzd.om.user.service.UserService;


/**
 * @author Thinker
 *
 */
@RestController
@RequestMapping(value = "/userTest")
public class UserDemoController {
	
    private UserService userService;
    
    private UserRepresentationService userRepresentationService;
    
    
    @Autowired
    public UserDemoController(UserService userService, UserRepresentationService userRepresentationService) {
		this.userService = userService;
		this.userRepresentationService = userRepresentationService;
	}


	@PostMapping
    @ResponseStatus(CREATED)
    public Map<String, String> createUser(@RequestBody @Valid CreateUserCommand command) {
    	System.out.println(Thread.currentThread().getId());
        return of("id", userService.create(command));
    }


    @PutMapping("/{id}/name")
    public Map<String, String> updateUserName(@PathVariable("id") String UserId,
                                                 @RequestBody @Valid UpdateUserNameCommand command) {
        return of("id", userService.updateUserName(UserId, command));
    }


    @GetMapping(value = "/{id}")
    public UserRepresentation byId(@PathVariable(name = "id") String id) {
        return userService.byId(id);
    }

    @GetMapping
    public PagedResource<UserRepresentation> pagedUsers(@RequestParam(required = false, defaultValue = "1") int pageIndex,
                                                                     @RequestParam(required = false, defaultValue = "10") int pageSize) {
        return userRepresentationService.listUsers(pageIndex, pageSize);
    }



}
