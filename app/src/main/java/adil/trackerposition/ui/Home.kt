package adil.trackerposition.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import adil.trackerposition.R
import adil.trackerposition.data.Api.User
import adil.trackerposition.data.api.Event
import adil.trackerposition.viewModelConnector.EventAdapter
import android.app.Activity
import android.os.Build
import android.widget.ExpandableListView
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.time.LocalDate

class Home(var ctx:Activity) : Fragment() {
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
        var user1= User("sdoisfd", 44.4, 323.2,"klsdf","lahcen", event)
//        var user2=  User("sdoisfd", 44.4, 323.2,"klsdf","ali" , event )
//        var user3 = User("sdoisfd", 44.4, 323.2,"klsdf","smail" , event)
//        var user4=  User("sdoisfd", 44.4, 323.2,"klsdf","mahdi" , event)
//        var user5=  User("sdoisfd", 44.4, 323.2,"klsdf","hasan" , event)
//        var listUsers:List<User> = listOf(user1, user2, user3, user4, user5,user1, user2, user3, user4, user5, user1, user2, user3, user4, user5, user1, user2, user3, user4,
//            user1, user2, user3, user4, user5,user5, user1, user2, user3, user4, user5,user1, user2, user3, user4, user5,user1, user2, user3, user4, user5,user1, user2, user3, user4, user5,user1, user2, user3, user4, user5)
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
