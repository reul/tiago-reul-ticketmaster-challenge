package space.reul.cleanarchitectureexample.data.webapi

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import space.reul.cleanarchitectureexample.domain.model.EventList
import space.reul.cleanarchitectureexample.domain.model.WebUrl
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import space.reul.cleanarchitectureexample.domain.model.Urls

private const val API_URL: String = "https://app.ticketmaster.com/"

class TicketmasterService {
    private val okHttpClient = OkHttpClient()
        .newBuilder()
// add if needed
//      .addInterceptor(AuthorizationInterceptor)
        .addInterceptor(HttpLoggingInterceptor().apply {
            val isDebug = true // TODO: inject retrofit instance, move this to DI module and use BuildConfig.DEBUG
            if (isDebug) level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(API_URL)
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .build()

    private val webApi = retrofit.create(TicketmasterWebApi::class.java)


    suspend fun listEvents(apiKey: String): EventList {
        val response = webApi.listEvents(apiKey)
        return response.body()?.embedded ?: EventList(listOf())
    }

    private interface TicketmasterWebApi {
        @GET("/discovery/v2/events.json")
        suspend fun listEvents(@Query("apikey") apiKey: String): Response<ListEventsResponse>
    }

    data class ListEventsResponse(
        @SerializedName("_embedded")
        val embedded: EventList? = null,
        val links: ResponseLinks? = null,
        val page: Page? = null
    )

    data class Page(
        val size: Long? = null,
        val totalElements: Long? = null,
        val totalPages: Long? = null,
        val number: Long? = null
    )

    data class ResponseLinks(
        val first: WebUrl? = null,
        val self: WebUrl? = null,
        val next: WebUrl? = null,
        val last: WebUrl? = null
    )
}
