package com.twitter.tweetservice.gateway.repository;

import com.twitter.tweetservice.domain.entity.Tweet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface TweetRepository extends JpaRepository<Tweet, UUID> {

    Optional<Tweet> findById(final UUID id);

    Page<Tweet> findByUserId(String userId, Pageable pageable);

}
