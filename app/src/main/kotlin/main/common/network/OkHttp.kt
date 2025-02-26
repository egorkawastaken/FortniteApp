package main.common.network

import okhttp3.OkHttpClient
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.X509TrustManager

fun OkHttpClient.Builder.applyTrustAllSslSocketFactory(): OkHttpClient.Builder {

    val trustAllCert = object : X509TrustManager {
        override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) = Unit

        override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) = Unit

        override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
    }

    return try {
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, arrayOf(trustAllCert), SecureRandom())
        val factory = sslContext.socketFactory
        sslSocketFactory(factory, trustAllCert)
    } catch (e: Exception) {
        this
    }
}
