package adil.trackerposition.data.Api

import retrofit2.Call
import retrofit2.http.*

interface ApiDatabase {
    @GET("users.php")
    fun getAllUser():Call<List<User>>
    @FormUrlEncoded
    @POST("updateLocation.php")
    fun updateLocation(@Field("latitude") latitude:Double, @Field("longitude") longitude: Double, @Field("user_token") id:String):Call<User>

    @GET("getUser.php")
    fun getUser():Call<List<User>>

    @FormUrlEncoded
    @POST("login.php")
    fun loginUser(@Field("username") username:String, @Field("pwd") pwd:String):Call<ResponseObject>

    @FormUrlEncoded
    @POST("register_user.php")
    fun registerUser(@Field("username") username:String, @Field("password") password:String, @Field("email") emailUser:String, @Field("city") userCity:String):Call<ResponseObject>

    @GET("getUserByToken.php")
    fun getUserByToken(@Query("user_token") user_token:String):Call<ResponseObject>
}
