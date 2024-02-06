package com.zubokoff.demoredis.entity.redis;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@AllArgsConstructor
@Data
@RedisHash
public class Address implements Serializable {

    @JsonProperty("logradouro")
    private String publicPlace;

    @JsonProperty("numero")
    private String number;
}
