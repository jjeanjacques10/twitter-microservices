package com.twitter.tweetservice.gateway.repository;

import com.twitter.tweetservice.domain.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, String> {

    List<Tweet> findByUserId(String userId);

}
