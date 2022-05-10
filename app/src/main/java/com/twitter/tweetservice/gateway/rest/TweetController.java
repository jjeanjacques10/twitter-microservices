package com.twitter.tweetservice.gateway.rest;

import com.twitter.tweetservice.gateway.rest.datacontract.TweetDto;
import com.twitter.tweetservice.domain.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tweet")
public class TweetController {

    @Autowired
    private TweetService tweetService;

    @GetMapping
    public ResponseEntity listTweetsByUser(@RequestParam(value = "user_id") String userId) {
        List<TweetDto> tweets = tweetService.listTweetsByUser(userId);
        return ResponseEntity.ok(tweets);
    }

    @PostMapping
    public ResponseEntity createTweet(@RequestBody TweetDto tweetDto) {
        tweetService.createTweet(tweetDto);
        return ResponseEntity.ok("created");
    }
}
