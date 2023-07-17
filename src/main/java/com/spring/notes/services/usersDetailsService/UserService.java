package com.spring.notes.services.usersDetailsService;

import com.spring.notes.services.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final PersonRepository useRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return useRepository.findPersonByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }
}
