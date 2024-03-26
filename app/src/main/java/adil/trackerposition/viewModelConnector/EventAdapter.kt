package adil.trackerposition.viewModelConnector

import adil.trackerposition.R
import adil.trackerposition.data.Api.User
import adil.trackerposition.data.api.Event
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView

class EventAdapter(var context: Context, val listUser:List<User>) : BaseExpandableListAdapter() {
    override fun getGroupCount(): Int {
        return  listUser.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return listUser.get(groupPosition).lstEvent.size
    }

    override fun getGroup(groupPosition: Int): Any {
        return listUser.get(groupPosition)
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return listUser.get(groupPosition).lstEvent.get(childPosition)
    }

    override fun getGroupId(groupPosition: Int): Long = groupPosition.toLong()

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
       return false
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        var user:User = getGroup(groupPosition) as User
        var view= convertView
        if(view == null){
            val layoutInflater:LayoutInflater = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
             view = layoutInflater.inflate(R.layout.device_event_list_title, null)
        }
        val nameDevice:TextView = view!!.findViewById(R.id.name)
        val emiDevice:TextView = view.findViewById(R.id.emi)
        nameDevice.text = user.username
        emiDevice.text = user.password
        return view
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var user = getGroup(groupPosition) as User
       val events:Event = getChild(groupPosition, childPosition) as Event
        var view = convertView
        if(view == null) {
            val layoutInflater: LayoutInflater =
                this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = layoutInflater.inflate(R.layout.device_event_detail, null)
            val latitude: TextView = view!!.findViewById(R.id.latitude)
            val longitude: TextView = view.findViewById(R.id.longitude)
            val status: TextView = view.findViewById(R.id.status)
            val timeStamp: TextView = view.findViewById(R.id.timeStamp)
            val location: TextView = view.findViewById(R.id.location)
            latitude.text = user.latitude.toString()
            longitude.text = user.longitude.toString()
            status.text = events.status
            timeStamp.text = events.time.toString()
            location.text = events.location
        }
        return view
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean = true

}