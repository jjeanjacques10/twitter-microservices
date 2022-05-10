package com.twitter.tweetservice.gateway.rest.datacontract;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @Size(max = 140, message = "Invalid content size, the limit is 140 characters")
    private String content;

    private LocalDateTime createdAt;
}
