package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.Journal;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalRepo;
import net.engineeringdigest.journalApp.service.JournalService;
import net.engineeringdigest.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController()
@RequestMapping("/journal")
public class JournalController {

    @Autowired
    private JournalRepo journalRepo;
    @Autowired
    private JournalService journalEntryService;
    @Autowired
    UserService userService;

    @GetMapping("{userName}")
    public ResponseEntity<List<Journal>> getAllJournalsOfUser(@PathVariable String userName) {
        try {
            User byUserName = userService.findByUserName(userName);

            List<Journal> allJournals = byUserName.getJournalList();
            return new ResponseEntity<>(allJournals,HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }

    @PostMapping("{userName}")
    public ResponseEntity<?> createJournal(@RequestBody Journal myJournalEntry, @PathVariable String userName) {
        try {

            if (myJournalEntry.getTitle() != null && myJournalEntry.getContent() != null) {

                boolean saved =journalEntryService.saveJournal(myJournalEntry,userName);
                return new ResponseEntity<>(saved,HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


    @GetMapping("/id/{myId}")
    public ResponseEntity<Journal> updateJournal(@PathVariable ObjectId myId) {
        try {
            Optional<Journal> journalEntry = journalEntryService.getJournalById(myId);
            if (journalEntry.isPresent()) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/id/{myId}/{userName}")
    public ResponseEntity<Journal> updateJournal(@PathVariable ObjectId myId, @RequestBody Journal newEntry,@PathVariable String userName) {
        try {
            Journal oldEntry = journalEntryService.getJournalById(myId).orElse(null);
            if (oldEntry != null) {
                oldEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : oldEntry.getTitle());
                oldEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : oldEntry.getContent());
            }
            journalEntryService.saveJournal(oldEntry);
            return new ResponseEntity<>(oldEntry, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("/id/{myId}/{userName}")
    public ResponseEntity<?> deleteJournal(@PathVariable ObjectId myId,@PathVariable String userName) {
        try {
            journalEntryService.getJournalDeleteById(myId,userName);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
