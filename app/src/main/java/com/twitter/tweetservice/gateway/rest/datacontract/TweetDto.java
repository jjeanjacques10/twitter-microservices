package com.twitter.tweetservice.gateway.rest.datacontract;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TweetDto {
    private UUID id;

    @NotNull
    private String userId;

    @NotNull
    private String content;
    private LocalDateTime createdAt;
}
