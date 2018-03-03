package com.example.api.tweeter

import com.example.api.tweeter.db.Tweet
import com.example.util.requery.toImmutableList
import io.requery.query.Result
import io.requery.sql.KotlinEntityDataStore
import javafx.beans.binding.Bindings.select
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TweeterApiController(private val store: KotlinEntityDataStore<Any>) {

    // see: https://github.com/requery/requery/blob/master/requery-test/kotlin-test/src/test/kotlin/io/requery/test/kt/FunctionalTest.kt
    @GetMapping("/api/tweeter")
    fun findAll():List<Tweet> {
        return store {
            val result =
                    select(Tweet::class) limit 10
            result.get().toImmutableList()
        }
    }
}
