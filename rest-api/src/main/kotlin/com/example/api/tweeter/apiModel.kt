package com.example.api.tweeter

import com.example.api.tweeter.db.Tweet
import java.time.Instant
import java.util.*

data class CreateTweetRequest(val message: String, val comment: String?)

fun CreateTweetRequest.toTweetEntity() = Tweet(
        id = UUID.randomUUID(),
        createdAt = Instant.now(),
        modifiedAt = Instant.now(),
        version = 0,
        message = message,
        comment = comment
)

data class TweetDto(
        val id: UUID,
        val version: Int,
        val createdAt: Instant,
        val modifiedAt: Instant,
        val message: String,
        val comment: String?
)

fun Tweet.toTweetDto() = TweetDto(
        id = id,
        version = version,
        createdAt = createdAt,
        modifiedAt = modifiedAt,
        message = message,
        comment = comment
)