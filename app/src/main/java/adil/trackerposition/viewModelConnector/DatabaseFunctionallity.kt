package adil.trackerposition.viewModelConnector

import adil.trackerposition.data.Api.*
import adil.trackerposition.ui.MainActivity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle.Delegate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import kotlin.properties.Delegates

class DatabaseFunctionallity(val context:Context){
    fun checkLoginUser(username:String, password:String, usernameField:EditText, passwordField:EditText):ResponseObject?{
        val database:ApiDatabase = getApiInstance()
        val loginUser = database.loginUser(username, password)
        var responseObject:ResponseObject? = null
        loginUser.enqueue(object: Callback<ResponseObject>{
            override fun onResponse(p0: Call<ResponseObject>, p1: Response<ResponseObject>) {
                if(p1.isSuccessful ){
                   if(p1.body()!!.request_code == 200){
                      responseObject = p1.body()
                       if(responseObject?.user != null){
                           Toast.makeText(context, "user token is : ${responseObject?.user?.user_token}", Toast.LENGTH_LONG).show()
                           val intent = Intent(context, MainActivity::class.java)
                           val user = responseObject?.user
                           intent.putExtra("user_token", user?.user_token)
                           context.startActivity(intent)
                       }else{
                           Toast.makeText(context, "user isn't available", Toast.LENGTH_LONG).show()
                       }

                   }
                   else if(p1.body()!!.request_code == 404){
                       val msg = p1.body()!!.msg
                       if(msg.equals("password")){
                           Toast.makeText(context, "password not correct", Toast.LENGTH_LONG).show()
                          passwordField.error = "password not correct"
                       }else{
                           usernameField.error = "username doesn't exist"
                           Toast.makeText(context, "username doesn't exist", Toast.LENGTH_LONG).show()
                       }
                   }
               }else{
                    Log.i("okhttpError", p1.code().toString())
                    Toast.makeText(context, p1.errorBody().toString(), Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(p0: Call<ResponseObject>, p1: Throwable) {
                Log.i("failureError", p1.message.toString())
               Toast.makeText(context,"failure: "+ p1.message.toString(), Toast.LENGTH_LONG).show()
            }
        })
        return  responseObject
    }
    fun getUserByToken(user_token:String, callback:UserCallback):User?{
        val database = getApiInstance()
        val getuser = database.getUserByToken(user_token)
        var user:User? = null
        var responseObject:ResponseObject? = null
        getuser.enqueue(object: Callback<ResponseObject>{
            override fun onResponse(p0: Call<ResponseObject>, response: Response<ResponseObject>) {
                if(response.isSuccessful){
                    responseObject = response.body()
                    try {
                        if(responseObject?.request_code == 200){
                            user = responseObject!!.user
                            callback.onUserReceived(user)
                        }else{
                            callback.onFailure("user does not exist")
                        }
                    }catch (ex:Exception){
                        callback.onFailure(ex.message.toString() ?: "error while calling")
                    }
                }else{
                    callback.onFailure("faill to fetch the user")
                }
            }
            override fun onFailure(p0: Call<ResponseObject>, throwable: Throwable) {
                Log.i("getUserFail", throwable.message.toString())
                callback.onFailure(throwable.message.toString() ?: "faill to make request")
            }
        })
        return user
    }

    fun getApiInstance():ApiDatabase{
        var retrofit:Retrofit = GetDatabaseApi.getRetrofit()
        var database:ApiDatabase = retrofit.create(ApiDatabase::class.java)
        return database
    }
}
