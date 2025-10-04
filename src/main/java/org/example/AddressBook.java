package org.example;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class AddressBook {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "addressBook", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    public List<BuddyInfo> buddies;

    public AddressBook(){
        buddies = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void addBuddy(BuddyInfo newBuddy){
        buddies.add(newBuddy);
        newBuddy.setAddressBook(this);
    }

    public void removeBuddy(BuddyInfo thisBuddy){
        for(int i =0; i < buddies.size(); i++) {
            if (buddies.get(i).getName().equals(thisBuddy.getName())) {
                buddies.remove(buddies.get(i));
            }
        }
    }

    public void printBuddies(){
        for(int i =0; i < buddies.size(); i++) {
            System.out.println(buddies.get(i).getName() +" "+ buddies.get(i).getPhoneNumber());
        }
    }


    public List<BuddyInfo> getBuddies() {
        return buddies;
    }
    public void setBuddies(ArrayList<BuddyInfo> buddies) {
        this.buddies = buddies;
    }

    public int countBuddies() {
    	return this.buddies.size();
    }

    public static void main(String[] args) {
        AddressBook ad =  new AddressBook();
        ad.addBuddy(new BuddyInfo("Adam", "131322313", "123 Street"));
        ad.addBuddy(new BuddyInfo("Bianca","817177474","456 Avenue"));
        ad.addBuddy(new BuddyInfo("Charles","12313","789 Boulevard"));
        ad.printBuddies();
    }
}
