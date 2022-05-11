package com.twitter.tweetservice.gateway.rest.datacontract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PaginationDataContract {

    @JsonProperty("total_pages")
    private Integer totalPages;

    @JsonProperty("total_elements")
    private Long totalElements;

    @JsonProperty("size")
    private Integer size;

    @JsonProperty("number")
    private Integer number;

}
