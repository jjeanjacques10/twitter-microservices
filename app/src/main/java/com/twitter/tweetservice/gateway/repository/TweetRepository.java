package com.twitter.tweetservice.gateway.repository;

import com.twitter.tweetservice.domain.entity.Tweet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TweetRepository extends JpaRepository<Tweet, String> {

    Page<Tweet> findByUserId(String userId, Pageable pageable);

}
