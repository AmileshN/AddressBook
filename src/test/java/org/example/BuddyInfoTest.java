package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class BuddyInfoTest {

    @Test
    public void getName() {
        BuddyInfo buddy1 = new BuddyInfo("Charlie", "111111111", "789 Oak St");
        assertEquals("Charlie", buddy1.getName());
    }

    @Test
    public void getPhoneNumber() {
        BuddyInfo buddy2 = new BuddyInfo("Diana", "222222222", "101 Pine St");
        assertEquals("222222222", buddy2.getPhoneNumber());
    }
}