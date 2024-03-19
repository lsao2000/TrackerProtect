package adil.trackerposition.data.Api

import com.google.gson.annotations.SerializedName

data class User(@SerializedName("id")
                var id:Int= 1){
    @SerializedName("email")
    lateinit var email:String

    @SerializedName("latitude")
    var latitude:Double=0.0

    @SerializedName("longitude")
    var longitude:Double=0.0

    @SerializedName("username")
    lateinit var username:String

    @SerializedName("imgProfile")
    lateinit var imgProfile:String

    @SerializedName("pwd")
    lateinit var password:String

    constructor(id:Int, password:String, longitude:Double, latitude:Double, imgProfile:String, username:String) : this() {
        this.password = password
        this.id = id
        this.longitude = longitude
        this.latitude = latitude
        this.imgProfile = imgProfile
        this.username = username
    }
}
