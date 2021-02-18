package com.lyzd.om.web.user.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lyzd.om.user.model.User;
import com.lyzd.om.user.repository.UserRepository;
import com.lyzd.om.user.sdk.command.UpdateUserNameCommand;

/**
 * Unit testing
 * @author Thinker
 *
 */
class UserNameUpdateApiTest extends CommonApiTest {

    @Autowired
    private UserRepository userRepository;

    //@Autowired
    //private UserRepresentationService userRepresentationService;

    @Test
    public void updaterUserName() {
    	User user = userRepository.byId("009942c966cf4f59a39dd674c98292df");
    	 given()
         .contentType("application/json")
         .body(new UpdateUserNameCommand("009942c966cf4f59a39dd674c98292df","wmz-new"))
         .when()
         .put("/user/{id}/name", user.getId())
         .then().statusCode(200);
        //query from data base.
        User dataBaseUser = userRepository.byId("009942c966cf4f59a39dd674c98292df"); 
        assertNotNull(dataBaseUser);
        assertEquals("wmz-new", dataBaseUser.getName());
    }


   


}