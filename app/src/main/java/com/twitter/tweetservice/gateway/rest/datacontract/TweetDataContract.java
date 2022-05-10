package com.twitter.tweetservice.gateway.rest.datacontract;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class TweetDataContract {
    private List<TweetDto> data;
    private PaginationDataContract pagination;
}
