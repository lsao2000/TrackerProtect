package adil.trackerposition.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import adil.trackerposition.R
import adil.trackerposition.data.Api.User
import adil.trackerposition.data.api.Event
import adil.trackerposition.viewModelConnector.DatabaseFunctionallity
import adil.trackerposition.viewModelConnector.EventAdapter
import android.app.Activity
import android.os.Build
import android.widget.ExpandableListView
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.time.LocalDate

class Home(var ctx:Activity, var user_token:String) : Fragment() {
    lateinit var myExpandList:ExpandableListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       var view:View =  inflater.inflate(R.layout.fragment_home, container, false)
       myExpandList = view.findViewById(R.id.listEvent)
        var date = LocalDate.now()
        var event1 = Event("mansour el youccoub", date, "moving")
        var event2 = Event("hay riad", date, "stop")
        var event3 = Event("agdal", date, "moving back")
        var event4 = Event("bab elhad", date, "moving")
        var event5 = Event("sale", date, "moving")
        var event6 = Event("temara", date, "stop")
        var event:List<Event> = listOf(event1, event2, event3, event4, event5, event6)
//        var user = DatabaseFunctionallity(this.context)
        var user1= User(user_token,"lksdf","lahcenenligne@gmail.com","lahcen","rabat", event, 33.2002, 2.2002)
        var listUsers = listOf(user1, user1)
        try {
            val eventAdapter:EventAdapter = EventAdapter(ctx, listUsers)
            myExpandList.setAdapter(eventAdapter)
        }catch (ex:Exception){
            Toast.makeText(ctx, ex.message, Toast.LENGTH_LONG).show()
        }
        // Inflate the layout for this fragment
        return view
    }
}
