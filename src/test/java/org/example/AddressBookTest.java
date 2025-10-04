package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class AddressBookTest {

    @Test
    public void addBuddy() {
        AddressBook addressBook = new AddressBook();
        BuddyInfo buddy = new BuddyInfo("Ali", "987654321", "123 Main St");
        addressBook.addBuddy(buddy);
        assertTrue(addressBook.getBuddies().contains(buddy));
    }

    @Test
    public void removeBuddy() {
        AddressBook addressBook = new AddressBook();
        BuddyInfo buddy = new BuddyInfo("Bob", "123123123", "456 Elm St");
        addressBook.addBuddy(buddy);
        addressBook.removeBuddy(buddy);
        assertFalse(addressBook.getBuddies().contains(buddy));
    }

    @Test
    public void printBuddies() {
        AddressBook addressBook = new AddressBook();
        BuddyInfo buddy1 = new BuddyInfo("Charlie", "111111111", "789 Oak St");
        BuddyInfo buddy2 = new BuddyInfo("Diana", "222222222", "101 Pine St");
        addressBook.addBuddy(buddy1);
        addressBook.addBuddy(buddy2);
        addressBook.printBuddies();
    }
}