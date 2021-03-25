/*
 * Copyright 2021 RAKOTOARISOA.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jms.dao;

import com.jms.model.Client;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author RAKOTOARISOA
 */
public class ClientDAOTest {
    
    private Client client;
    
    public ClientDAOTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    /**
     * Test of method authenticate() when email and password are valid
     */
   @Test
    public void testAuthenticateEmailPasswordReturnTrue() {
        System.out.println("authenticate-check if email and password are equals to client email and password");
        String email = "rc@gmail.com";
        String password = "rm123";
        boolean result = ClientDAO.authenticate(email, password);
        assertTrue(result);
        
    }
    
    /**
     * Test of method authenticate() when email and password are null
     */
    @Test
    public void testAuthenticateEmailPasswordNull() {
        System.out.println("authenticate-check if email and password are not null");
        String email = null;
        String password = null;
        boolean result = ClientDAO.authenticate(email, password);
        assertFalse(result);
    }

}
