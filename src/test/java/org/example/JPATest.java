// Amilesh Nanthakumaran 101231105
package org.example;

import jakarta.persistence.*;
import org.junit.Test;

import java.util.List;

public class JPATest {

    @Test
    public void performJPA(){
        BuddyInfo buddy = new BuddyInfo("Ali", "987654321");
        BuddyInfo buddy1 = new BuddyInfo("Bob", "654321");
        BuddyInfo buddy2 = new BuddyInfo("Allison", "987");
        AddressBook addressBook = new AddressBook();
        addressBook.addBuddy(buddy);
        addressBook.addBuddy(buddy1);
        addressBook.addBuddy(buddy2);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPATest");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();
        em.persist(addressBook);
        tx.commit();

        Query q = em.createQuery("Select b from BuddyInfo b");

        @SuppressWarnings("unchecked")
        List<BuddyInfo> list = q.getResultList();

        for (BuddyInfo buddyInfo : list) {
            System.out.println("Buddy: " + buddyInfo.getName() + " " + buddyInfo.getPhoneNumber());
        }

        Query q2 = em.createQuery("Select a from AddressBook a");

        @SuppressWarnings("unchecked")
        List<AddressBook> list2 = q2.getResultList();
        for (AddressBook ab : list2) {
            System.out.println("AddressBook getSize: " + ab.getBuddies().size()+"\n");
        }


        em.close();
        emf.close();


    }
}
