package adil.trackerposition.ui


import adil.trackerposition.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

class AddDevice : Fragment() {
    lateinit var addDevice: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view:View = inflater.inflate(R.layout.fragment_add_device, container, false)
        // Inflate the layout for this fragment
        addDevice = view.findViewById(R.id.add_device)
        addDevice.setOnClickListener {
            Toast.makeText(activity, "device addedd", Toast.LENGTH_LONG).show()
        }
        return view
    }

}