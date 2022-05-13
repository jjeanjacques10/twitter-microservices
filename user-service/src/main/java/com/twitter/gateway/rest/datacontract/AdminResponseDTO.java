package com.twitter.gateway.rest.datacontract;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminResponseDTO implements Serializable {

    private String email;

    private UUID id;

    private String password;

    private LocalDateTime lastLoginTime;
}
