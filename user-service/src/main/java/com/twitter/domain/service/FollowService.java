package com.twitter.domain.service;

import com.twitter.domain.entity.Relationship;

import java.util.List;
import java.util.UUID;

public interface FollowService {
    Relationship follow(Relationship relationship);

    List<Relationship> getFollowing(UUID followerId);

    List<Relationship> getFollowers(UUID followedId);

    void unfollow(UUID followedId, UUID followerId);
}
