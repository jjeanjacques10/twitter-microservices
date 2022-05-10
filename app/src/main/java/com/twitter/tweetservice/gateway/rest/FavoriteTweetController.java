package com.twitter.tweetservice.gateway.rest;

import com.twitter.tweetservice.domain.enums.FavoriteAction;
import com.twitter.tweetservice.domain.service.FavoriteTweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/favorite")
public class FavoriteTweetController {

    @Autowired
    FavoriteTweetService favoriteTweetService;

    @GetMapping
    public ResponseEntity listFavoritesByTweet(@RequestParam(value = "tweet_id", required = false) UUID tweetId,
                                               @RequestParam(value = "user_id", required = false) UUID userId) {
        if (tweetId != null) {
            return ResponseEntity.ok(favoriteTweetService.listFavoritesByTweet(tweetId.toString()));
        } else if (userId != null) {
            return ResponseEntity.ok(favoriteTweetService.listFavoritesByUser(userId.toString()));
        }
        throw new IllegalArgumentException();
    }

    @PostMapping("/{tweet_id}/user/{user_id}")
    public ResponseEntity createTweet(@PathVariable(value = "tweet_id") UUID tweeId,
                                      @PathVariable(value = "user_id") UUID userId) {

        favoriteTweetService.favoriteTweet(userId.toString(), tweeId.toString(), FavoriteAction.LIKE);
        return ResponseEntity.ok("Tweet " + tweeId + " liked by " + userId);
    }

}
