package com.twitter.tweetservice.gateway.rest;

import com.twitter.tweetservice.domain.entity.FavoriteTweet;
import com.twitter.tweetservice.domain.enums.FavoriteAction;
import com.twitter.tweetservice.domain.service.FavoriteTweetService;
import com.twitter.tweetservice.gateway.rest.datacontract.ResponseDataContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/favorite")
public class FavoriteTweetController {

    @Autowired
    FavoriteTweetService favoriteTweetService;

    @GetMapping
    public ResponseEntity<ResponseDataContract> listFavoritesByTweet(@RequestParam(value = "tweet_id", required = false) UUID tweetId,
                                                                     @RequestParam(value = "user_id", required = false) UUID userId) {
        List<FavoriteTweet> favoriteTweets;

        if (tweetId == null && userId == null)
            throw new IllegalArgumentException();

        if (tweetId != null) {
            favoriteTweets = favoriteTweetService.listFavoritesByTweet(tweetId.toString());
        } else {
            favoriteTweets = favoriteTweetService.listFavoritesByUser(userId.toString());
        }
        return ResponseEntity.ok(ResponseDataContract.builder()
                .data(favoriteTweets).build());
    }

    @PostMapping("/{tweet_id}/user/{user_id}")
    public ResponseEntity<ResponseDataContract> createFavoriteTweet(@PathVariable(value = "tweet_id") UUID tweeId,
                                                                    @PathVariable(value = "user_id") UUID userId) {

        FavoriteTweet favoriteTweet = favoriteTweetService.favoriteTweet(userId.toString(), tweeId.toString(), FavoriteAction.LIKE);
        return ResponseEntity.ok(ResponseDataContract.builder()
                .data(favoriteTweet).build());
    }

    @DeleteMapping("/{tweet_id}/user/{user_id}")
    public ResponseEntity deleteFavoriteTweet(@PathVariable(value = "tweet_id") UUID tweeId,
                                              @PathVariable(value = "user_id") UUID userId) {

        favoriteTweetService.deleteTweet(userId.toString(), tweeId.toString());
        return ResponseEntity.noContent().build();
    }

}
