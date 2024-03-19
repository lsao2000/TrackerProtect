package adil.trackerposition.data.Api

import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded

interface ApiDatabase {
    @GET("users.php")
    fun getAllUser():Call<List<User>>
    @FormUrlEncoded
    @POST("updateLocation.php")
    fun updateLocation(@Field("latitude") latitude:Double, @Field("longitude") longitude: Double, @Field("id") id:Int):Call<User>

    @GET("getUser.php")
    fun getUser():Call<List<User>>

}
