package adil.trackerposition.ui

import adil.trackerposition.R
import adil.trackerposition.data.Api.User
import adil.trackerposition.data.Api.UserCallback
import adil.trackerposition.viewModelConnector.DatabaseFunctionallity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class Profile(var user_token:String) : Fragment() {
    lateinit var nameUser:TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view:View = inflater.inflate(R.layout.fragment_profile, container, false)
        // Inflate the layout for this fragment
        nameUser = view.findViewById(R.id.firstLetter)
        var connectToDatabase = DatabaseFunctionallity(requireContext())
        var user = connectToDatabase.getUserByToken(user_token, object: UserCallback{
            override fun onUserReceived(user: User?) {
                val username = user?.username
                nameUser.text = username?.substring(0,1).toString()
                Toast.makeText(activity, user?.email ?: "failed", Toast.LENGTH_LONG ).show()
            }
            override fun onFailure(message: String) {
                Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
            }
        })
        return view
    }
}
