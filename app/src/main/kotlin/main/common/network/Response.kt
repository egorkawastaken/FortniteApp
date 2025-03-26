package main.common.network

import kotlinx.serialization.Serializable
import main.common.network.status.Status
import main.common.utils.Mapper

@Serializable
open class Response<T> (
    val status: Status? = null,
    val data: T? = null
) {

    val requireStatus: Status
        get() = requireNotNull(status) { "Response status is null" }

    fun requireData(): T = requireNotNull(data) { "Response data is null" }

    fun isSuccess() = status == Status.SUCCESS

    fun <R> map(mapper: Mapper<T, R>): Response<R> {
        val newData = mapper.map(requireData())
        return Response(
            data = newData,
            status = this.status
        )
    }
}