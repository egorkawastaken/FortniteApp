package main.common.utils

interface Mapper<in I, out O> {
    fun map(from: I): O

    fun mapList(from: List<I>): List<O> = from.map { map(it) }
}