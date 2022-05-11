package com.twitter.tweetservice.gateway.rest;

import com.twitter.tweetservice.domain.entity.FavoriteTweet;
import com.twitter.tweetservice.domain.entity.Tweet;
import com.twitter.tweetservice.domain.service.FavoriteTweetService;
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

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/tweet")
public class TweetController {

    @Autowired
    private TweetService tweetService;

    @Autowired
    private FavoriteTweetService favoriteTweetService;

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

    @GetMapping("/{tweet_id}")
    public ResponseEntity<ResponseDataContract> listTweetsByUser(@PathVariable(value = "tweet_id") UUID tweetId,
                                                                 @RequestParam(value = "expand", defaultValue = "false", required = false) boolean expand) {
        var tweet = tweetService.getTweet(tweetId);
        TweetDto tweetDto = mapper.map(tweet, TweetDto.class);
        if (expand) {
            List<FavoriteTweet> likes = favoriteTweetService.listFavoritesByTweet(tweetId.toString());
            tweetDto.setLikes(likes);
        }
        return ResponseEntity.ok(ResponseDataContract.builder()
                .data(tweetDto)
                .build());
    }

    @PostMapping
    public ResponseEntity<ResponseDataContract> createTweet(@RequestBody TweetDto tweetDto) {
        Tweet tweet = tweetService.createTweet(tweetDto);
        return ResponseEntity.ok(ResponseDataContract.builder()
                .data(mapper.map(tweet, TweetDto.class))
                .build());
    }

    @DeleteMapping("/{tweet_id}")
    public ResponseEntity deleteTweet(@PathVariable(value = "tweet_id") UUID tweeId) {
        tweetService.deleteTweet(tweeId);
        return ResponseEntity.noContent().build();
    }
}
