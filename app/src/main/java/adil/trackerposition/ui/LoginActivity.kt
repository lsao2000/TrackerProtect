package adil.trackerposition.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import adil.trackerposition.R
import adil.trackerposition.viewModelConnector.DatabaseFunctionallity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    lateinit var username:EditText
    lateinit var password:EditText
    lateinit var btnLogin:Button
    lateinit var register:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        username = findViewById(R.id.username)
        password = findViewById(R.id.pwd)
        btnLogin = findViewById(R.id.login)
        register = findViewById(R.id.register)
        btnLogin.setOnClickListener{
            var usernameValue = username.text.toString()
            var passwordValue = password.text.toString()
            var loginProcess = DatabaseFunctionallity(this@LoginActivity)
            try {
                loginProcess.checkLoginUser(usernameValue, passwordValue, username, password)
            }catch(ex:Exception){
                Toast.makeText(this, ex.message.toString(), Toast.LENGTH_LONG).show()
            }

        }
    }
}