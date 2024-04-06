package adil.trackerposition.data.Api

import com.google.gson.annotations.SerializedName

class ResponseObject (@SerializedName("msg") var msg:String, @SerializedName("request_code") var request_code:Int, @SerializedName("user") var user:User?)
