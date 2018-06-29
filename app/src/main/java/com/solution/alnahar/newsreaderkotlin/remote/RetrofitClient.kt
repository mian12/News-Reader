package com.solution.alnahar.newsreaderkotlin.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import java.security.cert.CertificateException
import javax.net.ssl.*


object RetrofitClient {


    private var retrofit: Retrofit? = null


    fun getClient(baseUrl: String): Retrofit
    {
        if (retrofit == null)
        {
            retrofit= Retrofit.Builder()
                    .baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
                    .build()


        }
        return retrofit!!
    }


//    private fun getUnsafeOkHttpClient(): OkHttpClient {
//        try {
//            // Create a trust manager that does not validate certificate chains
//            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager() {
//
//                val acceptedIssuers: Array<java.security.cert.X509Certificate>
//                    get() = arrayOf()
//
//                @Throws(CertificateException::class)
//                fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
//                }
//
//                @Throws(CertificateException::class)
//                fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
//                }
//            })
//
//            // Install the all-trusting trust manager
//            val sslContext = SSLContext.getInstance("SSL")
//            sslContext.init(null, trustAllCerts, java.security.SecureRandom())
//            // Create an ssl socket factory with our all-trusting manager
//            val sslSocketFactory = sslContext.getSocketFactory()
//
//            val builder = OkHttpClient.Builder()
//            builder.sslSocketFactory(sslSocketFactory)
//            builder.hostnameVerifier(object : HostnameVerifier() {
//                fun verify(hostname: String, session: SSLSession): Boolean {
//                    return true
//                }
//            })
//
//            return builder.build()
//        } catch (e: Exception) {
//            throw RuntimeException(e)
//        }
//
//    }



}

