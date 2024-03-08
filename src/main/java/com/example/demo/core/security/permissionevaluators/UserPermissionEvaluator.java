package com.example.demo.core.security.permissionevaluators;

import com.example.demo.core.exception.NoMyListEntryByIdFoundException;
import com.example.demo.domain.mylistentry.MyListEntry;
import com.example.demo.domain.mylistentry.MyListEntryServiceImpl;
import com.example.demo.domain.mylistentry.dto.MyListEntryDTO;
import com.example.demo.domain.mylistentry.dto.MyListEntryMinimalDTO;
import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserPermissionEvaluator {

  public UserPermissionEvaluator() {
  }

  @Autowired
  MyListEntryServiceImpl myListEntryServiceImpl;

  // Method to check if a user is above a certain age
  public boolean isUserAboveAge(User principal, int age) {
    return true;
  }

  // Method to check if the authenticated user is the owner of a given post
  public boolean isOwnPost(UUID id) throws NoMyListEntryByIdFoundException {
    MyListEntry targettedMyListEntry = myListEntryServiceImpl.getMyListEntryById(id);
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetailsImpl currentUser = (UserDetailsImpl) authentication.getPrincipal();
    User user = currentUser.user();
    System.out.println(targettedMyListEntry.getUser().getId());
    System.out.println(user.getId());
    return user.getId().toString().equals(targettedMyListEntry.getUser().getId().toString());
  }

  public boolean userIsOwnerOfPost(MyListEntryMinimalDTO targettedMyListEntry) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetailsImpl currentUser = (UserDetailsImpl) authentication.getPrincipal();
    User user = currentUser.user();
    System.out.println(targettedMyListEntry.getUser().getId());
    System.out.println(user.getId());
    return user.getId().toString().equals(targettedMyListEntry.getUser().getId().toString());
  }



}
