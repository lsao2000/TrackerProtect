package adil.trackerposition.data.Api

import adil.trackerposition.data.api.Event
import com.google.gson.annotations.SerializedName

class User(){
	@SerializedName("user_token")
	lateinit var user_token:String

	@SerializedName("email")
	lateinit var email:String

	@SerializedName("latitude")
	var latitude:Double=0.0

	@SerializedName("longitude")
	var longitude:Double=0.0

	@SerializedName("username")
	lateinit var username:String

	@SerializedName("user_city")
	lateinit var city:String

	@SerializedName("password")
	lateinit var password:String

	@SerializedName("event")
	lateinit var lstEvent:List<Event>

	constructor(user_token:String, password:String, email:String, username:String,  city:String):this(){
		this.user_token = user_token 
		this.email = email
		this.password = password
		this.username = username
		this.city = city
	}
	constructor(user_token:String, password:String, email:String, username:String,  city:String, lstEvent: List<Event>, latitude:Double, longitude:Double) : this() {
		this.password = password
		this.user_token = user_token
		this.username = username
		this.city = city
		this.email = email
		this.lstEvent = lstEvent
		this.latitude = latitude
		this.longitude = longitude
	}
}
