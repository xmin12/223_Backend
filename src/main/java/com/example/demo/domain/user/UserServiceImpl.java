package com.example.demo.domain.user;

import com.example.demo.core.exception.NoMyListEntryByIdFoundException;
import com.example.demo.core.generic.AbstractServiceImpl;
import com.example.demo.domain.mylistentry.MyListEntry;
import com.example.demo.domain.user.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class UserServiceImpl extends AbstractServiceImpl<User> implements UserService {

  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder,
                         UserRepository userRepository) {
    super(repository);
    this.passwordEncoder = passwordEncoder;
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return ((UserRepository) repository).findByEmail(email)
                                        .map(UserDetailsImpl::new)
                                        .orElseThrow(() -> new UsernameNotFoundException(email));
  }

  @Override
  public User register(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return save(user);
  }
  @Override
  //This Method can be used for development and testing. the Password for the user will be set to "1234"
  public User registerUser(User user){
    user.setPassword(passwordEncoder.encode("1234"));
    return save(user);
  }

  public Stream<Character> getRandomSpecialChars(int count) {
    Random random = new SecureRandom();
    IntStream specialChars = random.ints(count, 33, 45);
    return specialChars.mapToObj(data -> (char) data);
  }

  @Override
  public Boolean isUserAuthenticated(){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return authentication.isAuthenticated();
  }

  @Override
  public User updateUserById(UUID id, User user) throws UsernameNotFoundException {

    User user1 = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User Not found!"));
    user1.setLastName(user.getLastName());
    user1.setFirstName(user.getFirstName());
    user1.setEmail(user.getEmail());

    return userRepository.save(user1);
  }

}
