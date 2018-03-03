package com.example.api.tweeter.db

import io.requery.Column
import io.requery.Entity
import io.requery.Key
import java.time.Instant
import java.util.*


//@Entity(name="tweet", immutable = true)
@Entity
data class Tweet(
        @get:Key val id: UUID,
        @get:Column(name = "created_at") val createdAt: Instant,
        @get:Column(name = "updated_at") val modifiedAt: Instant,
        val version: Int,
        val message: String,
        val comment: String?
)