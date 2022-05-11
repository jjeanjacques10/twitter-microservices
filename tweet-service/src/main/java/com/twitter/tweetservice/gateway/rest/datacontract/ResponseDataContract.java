package com.twitter.tweetservice.gateway.rest.datacontract;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ResponseDataContract<T> {
    private T data;
}
