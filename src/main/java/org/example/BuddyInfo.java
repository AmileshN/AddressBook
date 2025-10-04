package org.example;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class BuddyInfo {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String phoneNumber;

    private String address;

    @ManyToOne
    @JsonIgnore
    private AddressBook addressBook;

    public AddressBook getAddressBook() {
        return addressBook;
    }

    public void setAddressBook(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BuddyInfo(){
        this.name = "N/A";
        this.phoneNumber = "";
        this.address = "N/A";
    }

    public BuddyInfo(String name, String phoneNumber, String address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getName() {
        return this.name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public static void main(String[] args) {
        BuddyInfo buddy = new BuddyInfo("Homer","1234222222","742 Evergreen Terrace");
        System.out.println("Hello "+buddy.getName()+"!");
    }
}
