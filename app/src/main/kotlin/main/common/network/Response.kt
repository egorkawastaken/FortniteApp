package main.common.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import main.common.utils.Mapper
import main.common.network.status.Status

open class Response<T>(

    @Expose
    @SerializedName("status")
    val status: Status? = null,

    @Expose
    @SerializedName("data")
    val data: T? = null
) {

    val requireStatus: Status = requireNotNull(status) { "Response status is null" }

    fun requireData(): T = requireNotNull(data) { "Response data is null" }

    fun isSuccess() = status == Status.SUCCESS

    fun <R> map(mapper: Mapper<T, R>): Response<R> {
        val newData = this.data?.let { mapper.map(it) }
        return Response(
            data = newData,
            status = this.status
        )
    }
}