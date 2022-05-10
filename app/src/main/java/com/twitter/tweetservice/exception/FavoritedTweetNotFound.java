package com.twitter.tweetservice.exception;

public class FavoritedTweetNotFound extends RuntimeException {

    public FavoritedTweetNotFound(String message) {
        super(message);
    }

}
