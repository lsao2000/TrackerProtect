package adil.trackerposition.viewModelConnector

import adil.trackerposition.data.Api.ApiDatabase
import adil.trackerposition.data.Api.GetDatabaseApi
import adil.trackerposition.data.Api.ResponseObject
import adil.trackerposition.data.Api.User
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
        var database:ApiDatabase = getApiInstance()
        var loginUser = database.loginUser(username, password)
        var responseObject:ResponseObject? = null
        loginUser.enqueue(object: Callback<ResponseObject>{
            override fun onResponse(p0: Call<ResponseObject>, p1: Response<ResponseObject>) {
                if(p1.isSuccessful ){
                   if(p1.body()!!.request_code == 200){
                      responseObject = p1.body()
                       var user = responseObject!!.user
                       val intent = Intent(context, MainActivity::class.java)
                        intent.putExtra("user_id", user!!.id)
                       context.startActivity(intent)
                   }
                   else if(p1.body()!!.request_code == 404){
                       var msg = p1.body()!!.msg
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
    fun getUserById(id:Int):ResponseObject?{
        val database = getApiInstance()
        val getuser = database.getUserById(id)
        var user:User? = null
        var responseObject:ResponseObject? = null
        getuser.enqueue(object: Callback<ResponseObject>{
            override fun onResponse(p0: Call<ResponseObject>, response: Response<ResponseObject>) {
                if(response.isSuccessful){
                    try {
                        responseObject = response.body()
                        if(responseObject?.request_code == 200){
                            user = response.body()!!.user
                            Toast.makeText(context, user!!.email, Toast.LENGTH_LONG).show()
                        }else{
                            Toast.makeText(context, "the user does not available", Toast.LENGTH_LONG).show()
                            return
                        }
                    }catch (ex:Exception){
                        Toast.makeText(context, ex.message, Toast.LENGTH_LONG).show()
                    }
                }else{
                    return
                }
            }
            override fun onFailure(p0: Call<ResponseObject>, throwable: Throwable) {
                Log.i("getUserFail", throwable.message.toString())
            }
        })
        return responseObject
    }

    fun getApiInstance():ApiDatabase{
        var retrofit:Retrofit = GetDatabaseApi.getRetrofit()
        var database:ApiDatabase = retrofit.create(ApiDatabase::class.java)
        return database
    }
}