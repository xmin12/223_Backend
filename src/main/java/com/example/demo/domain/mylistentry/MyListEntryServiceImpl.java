package com.example.demo.domain.mylistentry;

import com.example.demo.core.exception.InvalidSortingDirectionException;
import com.example.demo.core.exception.NoMyListEntryByIdFoundException;
import com.example.demo.core.generic.AbstractRepository;
import com.example.demo.core.generic.AbstractServiceImpl;
import com.example.demo.core.generic.SortDirection;
import com.example.demo.domain.mylistentry.dto.MyListEntryDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Log4j2
public class MyListEntryServiceImpl extends AbstractServiceImpl<MyListEntry> implements MyListEntryService {

    @Autowired
    MyListEntryRepository myListEntryRepository;

    public MyListEntryServiceImpl(AbstractRepository<MyListEntry> repository) {
        super(repository);
    }

    // Method to create a new MyListEntry
    @Override
    public MyListEntry createMyListEntry(MyListEntry myListEntry1) {
        log.info("Service of creating a new MyListEntry was called");
        return myListEntryRepository.save(myListEntry1);
    }

    // Method to get all MyListEntries
    @Override
    public List<MyListEntry> getMyListEntries() {
        log.info("Service of getting all MyListEntries was called");
        return myListEntryRepository.findAll();
    }

    // Method to get a MyListEntry by its ID
    @Override
    public MyListEntry getMyListEntryById(UUID id) throws NoMyListEntryByIdFoundException {
        log.info("Service of getting a MyListEntry by Id was called");
        return myListEntryRepository.findById(id).orElseThrow(() -> new NoMyListEntryByIdFoundException("No MyListEntry by Id found: " + id));
    }

    // Method to delete a MyListEntry by its ID
    @Override
    public void deleteMyListEntry(UUID myListEntryId) {
        log.info("Service of deleting a MyListEntry by Id was called");
        myListEntryRepository.deleteById(myListEntryId);
    }

    // Method to update a MyListEntry by its ID

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    @Override
    public MyListEntry updateMyListEntry (UUID myListEntryId, MyListEntryDTO myListEntryDetails) throws
    NoMyListEntryByIdFoundException {
        log.info("Service of updating a MyListEntry was called");
        MyListEntry myListEntry1 = myListEntryRepository.findById(myListEntryId).orElseThrow(() -> new NoMyListEntryByIdFoundException("No MyListEntry by Id found on update: " + myListEntryId));
        myListEntry1.setTitle(myListEntryDetails.getTitle());
        myListEntry1.setImportance(myListEntryDetails.getImportance());
        myListEntry1.setText(myListEntryDetails.getText());
        myListEntry1.setCreationDate(myListEntryDetails.getCreationDate());
        return myListEntryRepository.save(myListEntry1);
    }


    // Method to retrieve a sorted list of MyListEntries of a specific user by importance
    @Override
    public List<MyListEntry> sortedMyListEntryListOfSpecificUserByImportance(UUID userId, SortDirection sortingDirection) throws InvalidSortingDirectionException {
        log.info("Service of getting a MyListEntriesList of a specific user and sorted was called");
        Sort sortingDirectionOfImportance = null;
        try {
            switch (sortingDirection) {
                case ASC -> sortingDirectionOfImportance = Sort.by("importance").ascending();
                case DESC -> sortingDirectionOfImportance =  Sort.by("importance").descending();
            }
        } catch (IllegalArgumentException ex) {
            throw new InvalidSortingDirectionException("Invalid sorting direction. Use 'ASC', 'DESC', or leave it empty for no sorting.");
        }
        return myListEntryRepository.findByUserId(userId, sortingDirectionOfImportance);
    }

    // Method to retrieve a list of MyListEntries of a specific user
    @Override
    public List<MyListEntry> myListEntryListOfSpecificUser(UUID userId) {
        log.info("Service of getting a MyListEntriesList of a specific user was called");
        return myListEntryRepository.findByUserId(userId);
    }

    // Method to create multiple MyListEntries
    @Override
    public List<MyListEntry> createMyListEntries(List<MyListEntry> myListEntries) {
        log.info("Service of creating multiple MyListEntries was called");
        return myListEntryRepository.saveAll(myListEntries);
    }
}
