package com.zubokoff.demoredis.entity.redis;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zubokoff.demoredis.entity.redis.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@RedisHash(value = "cbti_conciliations", timeToLive = 1000)
public class Person implements Serializable {

    @Id
    private String id;

    @JsonProperty("nome")
    private String name;

    @JsonProperty("endereco")
    private Address address;
}
