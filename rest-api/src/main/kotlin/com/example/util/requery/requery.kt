package com.example.util.requery

import io.requery.query.Result

fun <T> Result<T>.toImmutableList()=this.toList().toList()