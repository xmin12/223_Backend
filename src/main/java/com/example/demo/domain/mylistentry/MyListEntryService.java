package com.example.demo.domain.mylistentry;

import com.example.demo.core.exception.InvalidSortingDirectionException;
import com.example.demo.core.exception.NoMyListEntryByIdFoundException;
import com.example.demo.core.generic.AbstractService;
import com.example.demo.core.generic.SortDirection;
import com.example.demo.domain.mylistentry.MyListEntry;
import com.example.demo.domain.mylistentry.dto.MyListEntryDTO;
import com.example.demo.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface MyListEntryService extends AbstractService<MyListEntry> {


        // Method to create a new MyListEntry
        MyListEntry createMyListEntry(MyListEntry myListEntry1);

        // Method to get all MyListEntries
        List<MyListEntry> getMyListEntries();

        // Method to get a MyListEntry by its ID
        MyListEntry getMyListEntryById(UUID id) throws NoMyListEntryByIdFoundException;

        // Method to delete a MyListEntry by its ID
       void deleteMyListEntry(UUID myListEntryId);

        // Method to update a MyListEntry by its ID
        MyListEntry updateMyListEntry(UUID myListEntryId, MyListEntryDTO myListEntryDetails) throws NoMyListEntryByIdFoundException;

        // Method to retrieve a sorted list of MyListEntries of a specific user by importance
        List<MyListEntry> sortedMyListEntryListOfSpecificUserByImportance(UUID userId, SortDirection sortingDirection) throws InvalidSortingDirectionException;

        // Method to retrieve a list of MyListEntries of a specific user
        List<MyListEntry> myListEntryListOfSpecificUser(UUID userId);

        List<MyListEntry> createMyListEntries(List<MyListEntry> myListEntries);
    }


