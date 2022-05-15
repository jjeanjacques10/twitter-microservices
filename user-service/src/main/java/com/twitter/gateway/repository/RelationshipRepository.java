package com.twitter.gateway.repository;

import com.twitter.domain.entity.Relationship;
import com.twitter.domain.entity.RelationshipId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RelationshipRepository extends JpaRepository<Relationship, RelationshipId> {

    Optional<Relationship> findByFollowerIdAndFollowedId(UUID followerId, UUID followedId);

    @Query("SELECT r FROM Relationship r WHERE r.followerId = :followerId")
    List<Relationship> findByFollowerId(UUID followerId);

    @Query("SELECT r FROM Relationship r WHERE r.followedId = :followedId")
    List<Relationship> findByFollowedId(UUID followedId);

}
