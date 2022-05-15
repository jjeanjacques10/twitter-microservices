package com.twitter.gateway.rest.datacontract;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FollowDataContract {

    private UUID followerId;

    private UUID followedId;

}
