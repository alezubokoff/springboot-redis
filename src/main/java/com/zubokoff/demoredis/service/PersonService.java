package com.zubokoff.demoredis.service;

import com.zubokoff.demoredis.entity.User;
import com.zubokoff.demoredis.entity.redis.Person;
import com.zubokoff.demoredis.repository.UserRepository;
import com.zubokoff.demoredis.repository.redis.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RequiredArgsConstructor
@Service
public class PersonService {

    final String KEY = "cbti_conciliations:";

    private final PersonRepository personRepository;

    private final UserRepository userRepository;

    private final RedisTemplate<String, Object> template;

    public boolean save(int qtde, Person person) {
        User user = new User(null, "Alexandre");
        this.userRepository.save(user);

        for (int i = 1; i <= qtde; i++) {
            String id = String.format("%s_%s_%s",
                    LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE),
                    person.getName(),
                    i);
            person.setId(id);
            this.personRepository.save(person);
        }
        return true;
    }

    public List<Person> getAll() {
        List<Person> persons = new ArrayList<>();
        for(Person p : this.personRepository.findAll()) {
            persons.add(p);
        }
        return persons;
    }

    public Person getById(String id) {
        Optional<Person> person = this.personRepository.findById(id);
        return person.orElse(null);
    }

    public List<String> getKeys(String patternKey) {
        var result = this.template.keys(KEY + patternKey + "*");
        if(result != null) {
            List<String> keys = new ArrayList<>(result);
            Collections.sort(keys);
            return keys;
        }
        return null;
    }

    public void deleteById(String id) {
        this.personRepository.deleteById(id);
    }
}
