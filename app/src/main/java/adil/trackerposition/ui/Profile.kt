package adil.trackerposition.ui

import adil.trackerposition.R
import adil.trackerposition.data.Api.User
import adil.trackerposition.viewModelConnector.DatabaseFunctionallity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

class Profile(var user_id:Int) : Fragment() {
    lateinit var nameUser:TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.fragment_profile, container, false)
        // Inflate the layout for this fragment
        var connectToDatabase = DatabaseFunctionallity(requireContext())
        var responseObject = connectToDatabase.getUserById(user_id)
//        Toast.makeText(requireContext(), User.id.toString(), Toast.LENGTH_LONG).show()
//        nameUser = view.findViewById(R.id.firstLetter)
//        nameUser.text = user.username.substring(0,2)

        return view
    }

}
