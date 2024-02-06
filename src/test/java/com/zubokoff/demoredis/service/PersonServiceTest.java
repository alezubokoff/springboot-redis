package com.zubokoff.demoredis.service;

import com.zubokoff.demoredis.entity.redis.Address;
import com.zubokoff.demoredis.entity.redis.Person;
import com.zubokoff.demoredis.repository.UserRepository;
import com.zubokoff.demoredis.repository.redis.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private UserRepository userRepository;

    @Autowired
    @InjectMocks
    private PersonService personService;

    private Person person;

    private Address address;

    @BeforeEach
    void setUp() {
        this.address = new Address("Rua 1", "1");
        this.person = new Person("1111", "Alexandre", address);
    }

    @Test
    @DisplayName("Deve salvar uma pessoa no Redis e um Usuário na base de dados")
    void shouldSavePerson() {
        this.personService.save(1, this.person);
        verify(this.personRepository, times(1)).save(any());
        verify(this.userRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Deve retornar uma lista de pessoas vazia")
    void shouldReturnListEmpty() {
        List<Person> persons = this.personService.getAll();
        verify(this.personRepository, times(1)).findAll();
        Assertions.assertInstanceOf(Collection.class, persons);
    }

    @Test
    @DisplayName("Deve retornar uma lista de pessoas")
    void shouldReturnList() {
        List<Person> personList = List.of(this.person);
        when(this.personRepository.findAll()).thenReturn(personList);

        List<Person> persons = this.personService.getAll();
        verify(this.personRepository, times(1)).findAll();
        Assertions.assertInstanceOf(Collection.class, persons);
        Assertions.assertEquals(persons, personList);
    }

    @Test
    void getById() {
    }

    @Test
    void getKeys() {
    }

    @Test
    void deleteById() {
    }
}