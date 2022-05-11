package com.twitter.tweetservice.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Tweet")
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(nullable = false, length = 36)
    private UUID id;

    @Column(nullable = false, length = 36)
    private String userId;

    private LocalDateTime createdAt;

    @Column(nullable = false, length = 140)
    private String content;

}
