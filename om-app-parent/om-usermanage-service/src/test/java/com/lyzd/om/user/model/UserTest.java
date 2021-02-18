package com.lyzd.om.user.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    public void should_create_product() {
        User user = User.create("name", 20);
        assertNotNull(user);
    }
}