package com.twitter.domain.service.impl;

import com.twitter.domain.entity.Relationship;
import com.twitter.domain.exception.RelationshipNotFound;
import com.twitter.domain.service.FollowService;
import com.twitter.gateway.repository.RelationshipRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class FollowServiceImpl implements FollowService {

    @Autowired
    private RelationshipRepository relationshipRepository;

    @Override
    public Relationship follow(Relationship relationship) {
        return relationshipRepository.save(relationship);
    }

    @Override
    public List<Relationship> getFollowing(UUID userID) {
        log.info("list user's following - {}", userID);
        return relationshipRepository.findByFollowerId(userID);
    }

    @Override
    public List<Relationship> getFollowers(UUID userID) {
        log.info("list user's followers - {}", userID);
        return relationshipRepository.findByFollowedId(userID);
    }

    @Override
    public void unfollow(UUID followedId, UUID followerId) {
        var follow = relationshipRepository.findByFollowerIdAndFollowedId(followedId, followerId)
                .orElseThrow(() -> new RelationshipNotFound("Follow not found"));
        relationshipRepository.delete(follow);
        log.info("user {} unfollowed by {}", followedId, followerId);
    }

}
