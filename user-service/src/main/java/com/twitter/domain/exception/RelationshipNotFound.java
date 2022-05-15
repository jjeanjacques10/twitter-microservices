package com.twitter.domain.exception;

public class RelationshipNotFound extends RuntimeException {
    public RelationshipNotFound(String message) {
        super(message);
    }
}