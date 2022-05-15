package com.twitter.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@IdClass(RelationshipId.class)
@Table(name = "Relationship")
public class Relationship implements Serializable {

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(name = "follower_id", length = 36)
    private UUID followerId;

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(name = "followed_id", length = 36)
    private UUID followedId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

}