package org.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddressBookViewController {

    private final AddressBookRepository addressBookRepo;
    
    public AddressBookViewController(AddressBookRepository addressBookRepo) {
        this.addressBookRepo = addressBookRepo;
    }

    @GetMapping("/addressbooks/view")
    public String index(Model model) {
        model.addAttribute("addressBooks", addressBookRepo.findAll());
        return "index";
    }

    @PostMapping("/addressbooks/view")
    public String addAddressBook(Model model) {
        AddressBook addressBook = new AddressBook();
        addressBookRepo.save(addressBook);
        model.addAttribute("addressBook",addressBook);
        model.addAttribute("buddyInfo",new BuddyInfo());
        System.out.println("Added AddressBook with ID: " + addressBook.getId());
        return "redirect:/addressbooks/"+addressBook.getId()+"/view";
    }

    @GetMapping("/addressbooks/{id}/view")
    public String viewAddressBooks(@PathVariable long id, Model model) {
        AddressBook addressBook = addressBookRepo.findById(id).orElseThrow();
        model.addAttribute("addressBook", addressBook);
        model.addAttribute("buddies", addressBook.getBuddies());
        model.addAttribute("buddyInfo",new BuddyInfo());
        return "addressbook";
    }

    @PostMapping("/addressbooks/{id}/view")
    public String addBuddy(@PathVariable long id, @ModelAttribute("buddyInfo") BuddyInfo buddy, Model model) {
        AddressBook addressBook = addressBookRepo.findById(id).orElseThrow();
        addressBook.addBuddy(new BuddyInfo(buddy.getName(), buddy.getPhoneNumber(), buddy.getAddress()));
        addressBookRepo.save(addressBook);
        model.addAttribute("addressBook", addressBook);
        model.addAttribute("buddies", addressBook.getBuddies());
        model.addAttribute("buddyInfo",buddy);
        return "addressbook";
    }
}
