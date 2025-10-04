package org.example;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/buddies")
public class BuddyInfoController {

    private final BuddyInfoRepository repo;


    public BuddyInfoController(BuddyInfoRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public BuddyInfo createBuddy(@RequestBody BuddyInfo buddy) {
        return repo.save(buddy);
    }

    @GetMapping
    public Iterable<BuddyInfo> getAllBuddies() {
        return repo.findAll();
    }



}
