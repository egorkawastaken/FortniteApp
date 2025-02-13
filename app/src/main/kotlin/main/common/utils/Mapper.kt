package main.common.utils

interface Mapper<in I, out O> {
    fun map(from: I): O
}