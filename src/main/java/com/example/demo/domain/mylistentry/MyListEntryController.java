package com.example.demo.domain.mylistentry;



import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import com.example.demo.core.exception.InvalidSortingDirectionException;
import com.example.demo.core.exception.NoMyListEntryByIdFoundException;
import com.example.demo.core.generic.SortDirection;
import com.example.demo.domain.mylistentry.dto.MyListEntryDTO;
import com.example.demo.domain.mylistentry.dto.MyListEntryMapper;
import com.example.demo.domain.mylistentry.dto.MyListEntryMinimalDTO;
import com.example.demo.domain.mylistentry.dto.MyListEntryMinimalMapper;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Log4j2
public class MyListEntryController {

    @Autowired
    MyListEntryServiceImpl myListEntryServiceImpl;

    private final MyListEntryMapper myListEntryMapper;

    private final MyListEntryMinimalMapper myListEntryMinimalMapper;


    public MyListEntryController(MyListEntryMapper myListEntryMapper, MyListEntryServiceImpl myListEntryServiceImpl, MyListEntryMinimalMapper myListEntryMinimalMapper) {
        this.myListEntryMapper = myListEntryMapper;
        this.myListEntryServiceImpl = myListEntryServiceImpl;
        this.myListEntryMinimalMapper = myListEntryMinimalMapper;
    }

    // Endpoint of creating a new MyListEntry

    /*
    Comment for luca:
    - we tried to figure out why this method does not seem to work: @PreAuthorize("@userPermissionEvaluator.userIsOwnerOfPost(myListEntry1)"):

    points we thought could have been the problem:
    - the parameter type of the method (MyListEntryMinimalDTO from the request, but method required MyListEntry) -> wasn't the solution
    - the method may start a loop, we tried starting the application in debug mode... no final conclusion yet (in application.properties -> logging.level.root=debug)

    ultimately the goal is to achieve this endpoint:
    @RequestMapping(value= "/mylistentries", method=RequestMethod.POST)
    @PreAuthorize("(hasAuthority('MYLISTENTRY_CREATE') && @userPermissionEvaluator.userIsOwnerOfPost(#myListEntry1)) || hasAuthority('MYLISTENTRY_CREATE_ADMIN')")
    @Operation(summary = "Creates a new myListEntry", description = "Creates a new myListEntry with status code 201 when successful")
    public ResponseEntity<MyListEntryDTO> createListEntry(@Valid @RequestBody MyListEntryMinimalDTO myListEntry1) {
        log.info("Endpoint of creating new MyListEntry was called");
        return ResponseEntity.status(HttpStatus.CREATED).body(myListEntryMapper.toDTO(myListEntryServiceImpl.createMyListEntry(myListEntryMinimalMapper.fromDTO(myListEntry1))));
    }
     */
    @RequestMapping(value= "/mylistentries", method=RequestMethod.POST)
    @PreAuthorize("hasAuthority('MYLISTENTRY_CREATE') || hasAuthority('MYLISTENTRY_CREATE_ADMIN')")
    @Operation(summary = "Creates a new myListEntry", description = "Creates a new myListEntry with status code 201 when successful")
    public ResponseEntity<MyListEntryDTO> createListEntry(@Valid @RequestBody MyListEntryMinimalDTO myListEntry1) {
        log.info("Endpoint of creating new MyListEntry was called");
        return ResponseEntity.status(HttpStatus.CREATED).body(myListEntryMapper.toDTO(myListEntryServiceImpl.createMyListEntry(myListEntryMinimalMapper.fromDTO(myListEntry1))));
    }


    /*
    ultimately the goal is to achieve this endpoint:
    @RequestMapping(value= "/mylistentrieslist", method=RequestMethod.POST)
    @PreAuthorize("(hasAuthority('MYLISTENTRY_CREATE') " +
            "&& @userPermissionEvaluator.userIsOwnerOfPost(#myListEntry1)) " +
            "|| hasAuthority('MYLISTENTRY_CREATE_ADMIN')")
    @Operation(summary = "Creates multiple new myListEntries", description = "Creates multiple new myListEntries with status code 201 when successful")
    public ResponseEntity<List<MyListEntryDTO>> createListEntries(@Valid @RequestBody List<MyListEntryMinimalDTO> myListEntry1) {
        log.info("Endpoint of creating multiple new MyListEntries was called");
        return ResponseEntity.status(HttpStatus.CREATED).body(myListEntryMapper.toDTOs(myListEntryServiceImpl.createMyListEntries(myListEntryMinimalMapper.fromDTOs(myListEntry1))));
    }
     */

    // Endpoint of creating multiple new MyListEntries
    @RequestMapping(value= "/mylistentrieslist", method=RequestMethod.POST)
    @PreAuthorize("hasAuthority('MYLISTENTRY_CREATE') || hasAuthority('MYLISTENTRY_CREATE_ADMIN')")
    @Operation(summary = "Creates multiple new myListEntries", description = "Creates multiple new myListEntries with status code 201 when successful")
    public ResponseEntity<List<MyListEntryDTO>> createListEntries(@Valid @RequestBody List<MyListEntryMinimalDTO> myListEntry1) {
        log.info("Endpoint of creating multiple new MyListEntries was called");
        return ResponseEntity.status(HttpStatus.CREATED).body(myListEntryMapper.toDTOs(myListEntryServiceImpl.createMyListEntries(myListEntryMinimalMapper.fromDTOs(myListEntry1))));
    }

    // Endpoint of getting a MyListEntry
    @RequestMapping(value= "/mylistentries", method=RequestMethod.GET)
    @PreAuthorize("hasAuthority('MYLISTENTRY_READ')")
    @Operation(summary = "Fetches all MyListEntries", description = "Fetches all MyListEntries with status code 200 when successful")
    public ResponseEntity<List<MyListEntryDTO>> readMyListEntries() {
        log.info("Endpoint of getting all MyListEntries was called");
        return ResponseEntity.status(HttpStatus.OK).body(myListEntryMapper.toDTOs(myListEntryServiceImpl.getMyListEntries()));
    }

    // Endpoint of getting all MyListEntries
    @RequestMapping(value= "/mylistentries/{myListEntryId}", method=RequestMethod.GET)
    @PreAuthorize("hasAuthority('MYLISTENTRY_READ')")
    @Operation(summary = "Fetches MyListEntry by Id", description = "Fetches MyListEntry by Id with status code 200 when successful")
    public ResponseEntity<MyListEntryDTO> readMyListEntryById(@Valid @PathVariable(value = "myListEntryId") UUID id) throws NoMyListEntryByIdFoundException {
        log.info("Endpoint of getting MyListEntry by Id was called");
        return ResponseEntity.status(HttpStatus.OK).body(myListEntryMapper.toDTO(myListEntryServiceImpl.getMyListEntryById(id)));
    }

    // Endpoint of updating a MyListEntry
    @RequestMapping(value= "/mylistentries/{myListEntryId}", method=RequestMethod.PUT)
    @PreAuthorize("hasAuthority('MYLISTENTRY_UPDATE') || @userPermissionEvaluator.isOwnPost(#id)")
    @Operation(summary = "Updates a MyListEntry", description = "Updates a MyListEntry with status code 201 when successful")
    public ResponseEntity<MyListEntryDTO> updateReturn(@Valid @PathVariable(value = "myListEntryId") UUID id, @RequestBody MyListEntryDTO myListEntryMinimalDetails) throws NoMyListEntryByIdFoundException {
        log.info("Endpoint of updating a MyListEntry was called");
        return ResponseEntity.status(HttpStatus.CREATED).body(myListEntryMapper.toDTO(myListEntryServiceImpl.updateMyListEntry(id, myListEntryMinimalDetails)));
    }

    // Endpoint of deleting a MyListEntry
    @RequestMapping(value= "/mylistentries/{myListEntryId}", method=RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('MYLISTENTRY_DELETE') || @userPermissionEvaluator.isOwnPost(#id)")
    @Operation(summary = "Deletes a MyListEntry", description = "Deletes a MyListEntry with status code 200 when successful")
    public void deleteMyListEntry(@Valid @PathVariable(value = "myListEntryId") UUID id) throws NoMyListEntryByIdFoundException {
        log.info("Endpoint of deleting a MyListEntry was called");
        myListEntryServiceImpl.deleteMyListEntry(id);
    }


    // Retrieve all MyListEntries of a user, sorted by importance
    @GetMapping("/{id}/allmylistentriessorted")
    @PreAuthorize("hasAuthority('MYLISTENTRIES_OF_A_SPECIFIC_USER')")
    @Operation(summary = "Gets all the MyListEntries of a specific user", description = "Gets all the MyListEntries of a specific user with status code 200 when successful")
    public ResponseEntity<List<MyListEntryDTO>> retrieveAllMyListEntireOfAUser(@PathVariable UUID id, @RequestParam(required = false) @Valid SortDirection sortDirection) throws InvalidSortingDirectionException {
        log.info("Endpoint of getting all MyListEntries of a specific user was called");
        if (sortDirection == null){
            return ResponseEntity.status(HttpStatus.OK).body(myListEntryMapper.toDTOs(myListEntryServiceImpl.myListEntryListOfSpecificUser(id)));
        }
        return ResponseEntity.status(HttpStatus.OK).body(myListEntryMapper.toDTOs(myListEntryServiceImpl.sortedMyListEntryListOfSpecificUserByImportance(id, sortDirection)));
    }

    // Exception handler for NoSuchElementException
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e){
        log.error("No such element");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No such Element");
    }

    // Exception handler for InvalidSortingDirection Exception
    @ExceptionHandler(InvalidSortingDirectionException.class)
    public ResponseEntity<String> handleInvalidSortingDirectionException(InvalidSortingDirectionException e){
        log.error("Invalid sorting direction. Use 'ASC', 'DESC', or leave it empty for no sorting.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    // Exception handler for MethodArgumentNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        log.error("Argument not Valid");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Argument not Valid!");
    }

    // Exception handler for NoMyListEntryByIdFoundException
    @ExceptionHandler(NoMyListEntryByIdFoundException.class)
    public ResponseEntity<String> handleNoMyListEntryByIdFoundException(NoMyListEntryByIdFoundException e){
    log.error("NoMyListEntryByIdFoundException: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}