package com.zubokoff.demoredis.repository.redis;

import com.zubokoff.demoredis.entity.redis.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, String> {

}
