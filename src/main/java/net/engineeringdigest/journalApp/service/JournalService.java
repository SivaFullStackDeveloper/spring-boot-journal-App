package net.engineeringdigest.journalApp.service;
import net.engineeringdigest.journalApp.entity.Journal;
import net.engineeringdigest.journalApp.repository.JournalRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class JournalService {
    @Autowired
    private JournalRepo journalEntryRepo;


    public List<Journal> getAllJournals() {
       return  journalEntryRepo.findAll();
    }

    public boolean saveJournal(Journal journalEntry) {
          journalEntryRepo.save(journalEntry);
          return true;
    }

    public Optional<Journal> getJournalById(ObjectId id) {
        return journalEntryRepo.findById(id);

    }

    public void getJournalDeleteById(ObjectId id) {
         journalEntryRepo.deleteById(id);

    }





}
