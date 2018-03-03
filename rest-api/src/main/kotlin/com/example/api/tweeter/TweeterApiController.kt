package com.example.api.tweeter

import com.example.api.tweeter.db.Tweet
import com.example.util.requery.toImmutableList
import io.requery.kotlin.eq
import io.requery.sql.KotlinEntityDataStore
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class TweeterApiController(private val store: KotlinEntityDataStore<Any>) {

    // see: https://github.com/requery/requery/blob/master/requery-test/kotlin-test/src/test/kotlin/io/requery/test/kt/FunctionalTest.kt
    @GetMapping("/api/tweeter")
    fun findAll(): List<TweetDto> =
            store {
                val result =
                        select(Tweet::class) limit 10
                result.get().toImmutableList()
            }.map { it.toTweetDto() }

    @PutMapping("/api/tweeter")
    fun createOne(@RequestBody req: CreateTweetRequest): TweetDto {
        val newTweet = req.toTweetEntity()
        return store.withTransaction {
            insert(newTweet)
            val result =
                    select(Tweet::class) where (
                            Tweet::id eq newTweet.id
                            ) limit 1
            result.get().first()
        }.toTweetDto()
    }
}
