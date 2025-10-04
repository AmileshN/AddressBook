package org.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AddressBookViewController {

    private final AddressBookRepository addressBookRepo;
    
    public AddressBookViewController(AddressBookRepository addressBookRepo) {
        this.addressBookRepo = addressBookRepo;
    }

    @GetMapping("/addressbooks/{id}/view")
    public String viewAddressBooks(@PathVariable long id, Model model) {
        AddressBook addressBook = addressBookRepo.findById(id).orElseThrow();
        model.addAttribute("addressBook", addressBook);
        model.addAttribute("buddies", addressBook.getBuddies());
        return "addressbook";
    }
}
