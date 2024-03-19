package adil.trackerposition.data.Api

import com.google.gson.annotations.SerializedName

class ResponseApi(@SerializedName("msg") var message:String, @SerializedName("user") var user:User?)