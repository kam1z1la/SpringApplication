package com.spring.notes.services.usersDetailsService;

import com.spring.notes.entity.Person;
import com.spring.notes.services.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    PersonRepository useRepository;
    @InjectMocks
    UserService userService;

    @Test
    void testLoadUserByUsername() {
        Person testPerson = cteateTestPerson();

        Mockito.when(useRepository.findPersonByUsername(testPerson.getUsername()))
                .thenReturn(Optional.of(testPerson));

        assertDoesNotThrow( () ->
                userService.loadUserByUsername(testPerson.getUsername()));
    }

    private Person cteateTestPerson() {
        return  Person.builder()
                .id(1L)
                .username("user123")
                .passwoard("qwerty")
                .build();
    }
}