package adil.trackerposition.ui

import adil.trackerposition.R
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentManager
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class Tracking(var user_token:String) : Fragment() , OnMapReadyCallback{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_tracking, container, false)
        val mapFragment = childFragmentManager.findFragmentById(R.id.trackingFragment) as? SupportMapFragment
        mapFragment?.getMapAsync(OnMapReadyCallback {googleMap ->
            val sudney = LatLng(-33.852, 151.211)
            googleMap.addMarker(
                MarkerOptions()
                    .position(sudney)
                    .title("sudeny")
            )
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(sudney))
        }) // Inflate the layout for this fragment
        return  view
    }

    override fun onMapReady(p0: GoogleMap) {
        val sudney = LatLng(-33.852, 151.211)
        p0.addMarker(
            MarkerOptions()
                .position(sudney)
                .title("sudeny")
        )
        p0.moveCamera(CameraUpdateFactory.newLatLng(sudney))
    }

}
