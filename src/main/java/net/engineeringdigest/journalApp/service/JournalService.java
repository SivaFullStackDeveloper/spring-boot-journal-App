package net.engineeringdigest.journalApp.service;
import net.engineeringdigest.journalApp.entity.Journal;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Component
public class JournalService {
    @Autowired
    private JournalRepo journalEntryRepo;
    @Autowired
    private UserService userService;

    public List<Journal> getAllJournals() {
       return  journalEntryRepo.findAll();
    }

    @Transactional
    public boolean saveJournal(Journal journalEntry, String userName) {
        User byUserName = userService.findByUserName(userName);
        Journal savedEntry = journalEntryRepo.save(journalEntry);
        byUserName.getJournalList().add(savedEntry);
        userService.saveUser(byUserName);
        return true;
    }

    public boolean saveJournal(Journal journalEntry) {
        Journal savedEntry = journalEntryRepo.save(journalEntry);
        
        return true;
    }

    public Optional<Journal> getJournalById(ObjectId id) {
        return journalEntryRepo.findById(id);

    }

    public void getJournalDeleteById(ObjectId id,String userName) {
        User user = userService.findByUserName(userName);
        user.getJournalList().removeIf(e->e.getId().equals(id));
        userService.saveUser(user);
        journalEntryRepo.deleteById(id);

    }





}
