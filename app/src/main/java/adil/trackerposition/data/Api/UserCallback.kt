package adil.trackerposition.data.Api

interface UserCallback {
    fun onUserReceived(user:User?)
    fun onFailure(message:String)
}