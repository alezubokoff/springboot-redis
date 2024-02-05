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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PersonService {

    final String KEY = "cbti_conciliations:";

    private final PersonRepository personRepository;

    private final UserRepository userRepository;

    private final RedisTemplate<String, Object> template;

    public void save(Person person) {
        String id = String.format("%s_%s", LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE), person.getName());
        person.setId(id);

        User user = new User(null, "Alexandre");
        this.userRepository.save(user);

        this.personRepository.save(person);
    }

    public List<Person> getAll() {
        return (List<Person>) this.personRepository.findAll();
    }

    public Person getById(String id) {
        Optional<Person> person = this.personRepository.findById(id);
        return person.orElse(null);
    }

    public List<String> getKeys(String patternKey) {
        var result = this.template.keys(KEY + patternKey + "*");
        if(result != null) {
            return new ArrayList<>(result);
        }
        return null;
    }

    public void deleteById(String id) {
        this.personRepository.deleteById(id);
    }
}
