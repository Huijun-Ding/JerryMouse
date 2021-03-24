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
     * Test of authenticate method, of class ClientDAO.
     * case when email= "" and password =""
     */
    @Test
    public void testAuthenticateEmailEmptyPasswordEmpty() {
        System.out.println("authenticate");
        String email = "";
        String password = "";
        boolean expResult = false;
        boolean result = ClientDAO.authenticate(email, password);
        assertFalse(result);
    }
    
    @Test
    public void testAuthenticateEmailNotEmptyPasswordNotEmpty() {
        System.out.println("authenticate");
        String email = "ss@gmail.com";
        String password = "ss";
        boolean expResult = true;
        boolean result = ClientDAO.authenticate(email, password);
        assertTrue(result);
    }
    
}
