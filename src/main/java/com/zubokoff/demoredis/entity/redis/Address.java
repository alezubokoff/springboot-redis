package com.zubokoff.demoredis.entity.redis;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash
public class Address implements Serializable {

    @JsonProperty("logradouro")
    private String publicPlace;

    @JsonProperty("numero")
    private String number;
}
