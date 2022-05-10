package com.twitter.tweetservice.gateway.rest;

import com.twitter.tweetservice.domain.service.TweetService;
import com.twitter.tweetservice.gateway.rest.datacontract.PaginationDataContract;
import com.twitter.tweetservice.gateway.rest.datacontract.ResponseDataContract;
import com.twitter.tweetservice.gateway.rest.datacontract.TweetDataContract;
import com.twitter.tweetservice.gateway.rest.datacontract.TweetDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/tweet")
public class TweetController {

    @Autowired
    private TweetService tweetService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<TweetDataContract> listTweetsByUser(@RequestParam(value = "user_id") String userId, Pageable pageable) {
        var pageTweets = tweetService.listTweetsByUser(userId, pageable);
        var tweets = pageTweets.getContent().stream()
                .map(t -> mapper.map(t, TweetDto.class)).toList();

        TweetDataContract tweetDataContract = TweetDataContract.builder()
                .data(tweets)
                .pagination(PaginationDataContract.builder()
                        .size(pageTweets.getSize())
                        .number(pageTweets.getNumber())
                        .totalPages(pageTweets.getTotalPages())
                        .totalElements(pageTweets.getTotalElements())
                        .build()).build();

        return ResponseEntity.ok(tweetDataContract);
    }

    @PostMapping
    public ResponseEntity<ResponseDataContract> createTweet(@RequestBody TweetDto tweetDto) {
        tweetService.createTweet(tweetDto);
        return ResponseEntity.ok(ResponseDataContract.builder()
                .data("Tweet created - User " + tweetDto.getUserId())
                .build());
    }
}
