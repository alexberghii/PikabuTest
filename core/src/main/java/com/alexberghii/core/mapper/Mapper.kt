package com.alexberghii.core.mapper


interface Mapper<I, O> {

    fun map(input: I): O
}