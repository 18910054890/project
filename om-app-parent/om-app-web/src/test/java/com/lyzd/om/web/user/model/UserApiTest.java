package com.lyzd.om.web.user.model;

import static java.util.stream.IntStream.range;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lyzd.om.user.model.User;
import com.lyzd.om.user.repository.UserRepository;
import com.lyzd.om.user.sdk.command.CreateUserCommand;

/**
 * Unit testing
 * @author Thinker
 *
 */
class UserApiTest extends CommonApiTest {

    @Autowired
    private UserRepository userRepository;

    //@Autowired
    //private UserRepresentationService userRepresentationService;

    @Test
    public void createUser() {
    	
    	//Call factory method creating a user instance.
        User aUser = User.create("Thinker", 2080); 
        
        userRepository.save(aUser); //User persistence.
        
        String id = given()
                .contentType("application/json")
                .body(new CreateUserCommand("Thinker", 2080))
                .when()
                .post("/user")
                .then().statusCode(201)
                .extract().body().jsonPath().getString("id");
        //query from data base.
        User dataBaseUser = userRepository.byId(id); 
        assertNotNull(dataBaseUser);
        assertEquals(id, dataBaseUser.getId());
    }

    @Test
    public void listAllUsers() {
        range(0, 10).forEach(value -> userRepository.save(User.create("Thinker", 2082)));
        given()
                .when()
                .get("/user?pageIndex=2&pageSize=5")
                .then().statusCode(200)
                .body("pageIndex", is(2))
                .body("resource.size()", is(5));
    }

   


}