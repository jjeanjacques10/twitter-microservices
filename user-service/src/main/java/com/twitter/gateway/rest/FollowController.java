package com.twitter.gateway.rest;

import com.twitter.domain.entity.Relationship;
import com.twitter.domain.service.FollowService;
import com.twitter.gateway.rest.datacontract.FollowDataContract;
import com.twitter.gateway.rest.datacontract.ResponseDataContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/user/follow")
public class FollowController {

    @Autowired
    private FollowService followService;

    @GetMapping("/{userid}/following")
    public ResponseEntity<ResponseDataContract> getFollowing(@PathVariable(name = "userid") UUID userId) {
        var follows = followService.getFollowing(userId);
        return ResponseEntity.ok(ResponseDataContract.builder()
                .data(follows)
                .build());
    }

    @GetMapping("/{userid}/followers")
    public ResponseEntity<ResponseDataContract> getFollowers(@PathVariable(name = "userid") UUID userId) {
        var follows = followService.getFollowers(userId);
        return ResponseEntity.ok(ResponseDataContract.builder()
                .data(follows)
                .build());
    }

    @PostMapping
    public ResponseEntity<ResponseDataContract> createRelationship(@RequestBody FollowDataContract followDataContract) {
        Relationship relationship = Relationship.builder()
                .followedId(followDataContract.getFollowedId())
                .followerId(followDataContract.getFollowerId())
                .createdAt(LocalDateTime.now())
                .build();
        var followed = followService.follow(relationship);
        return ResponseEntity.created(URI.create("")).body(ResponseDataContract.builder()
                .data(followed)
                .build());
    }

    @DeleteMapping
    public ResponseEntity<ResponseDataContract> deleteRelationship(@RequestBody FollowDataContract followDataContract) {
        followService.unfollow(followDataContract.getFollowedId(), followDataContract.getFollowerId());
        return ResponseEntity.ok(ResponseDataContract.builder()
                .data("Follow deleted")
                .build());
    }
}
