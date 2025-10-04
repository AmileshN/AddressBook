package org.example;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addressbooks")
public class AddressBookController {


    private final AddressBookRepository addRepo;
    private final BuddyInfoRepository buddyRepo;

    public AddressBookController(AddressBookRepository addRepo, BuddyInfoRepository buddyRepo) {
        this.addRepo = addRepo;
        this.buddyRepo = buddyRepo;
    }

    @PostMapping
    public AddressBook createAddressBook(@RequestBody AddressBook addressBook) {
        return addRepo.save(addressBook);

    }

    @GetMapping
    public Iterable<AddressBook> getAllAddressBooks() {
        return addRepo.findAll();
    }

    @GetMapping("/{id}")
    public AddressBook getAddressBookById(@PathVariable long id) {
        return addRepo.findById(id).orElse(null);
    }

    @PostMapping("/{id}/buddies")
    public AddressBook addBuddyToAddressBook(@PathVariable long id, @RequestBody BuddyInfo buddy) {
        AddressBook addressBook = addRepo.findById(id).orElseThrow();
        buddyRepo.save(buddy);
        addressBook.addBuddy(buddy);
        addRepo.save(addressBook);
        return addressBook;

    }

    @DeleteMapping("/{id}/buddies/{buddyId}")
    public AddressBook removeBuddyFromAddressBook(@PathVariable long id, @PathVariable long buddyId) {
        AddressBook addressBook = addRepo.findById(id).orElseThrow();
        addressBook.removeBuddy(buddyRepo.findById(buddyId).orElseThrow());
        return addRepo.save(addressBook);
    }







}
