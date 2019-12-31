package ayaan.tarikul.kotlinsampleapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ayaan.tarikul.kotlinsampleapp.R
import ayaan.tarikul.kotlinsampleapp.model.ReposDataModel

class ReposAdapter (private var allReposList: List<ReposDataModel>, private val context: Context) : RecyclerView.Adapter<ReposAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.all_repos_list_item, parent, false))
    }
    override fun getItemCount(): Int {
        return allReposList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataModel=allReposList.get(position)
        holder.txvId.text= dataModel.id.toString()
        holder.txvNodeId.text= dataModel.nodeId.toString()
        holder.txvName.text= dataModel.name.toString()
        holder.txvFullName.text= dataModel.fullName.toString()
        holder.private.text= dataModel._private.toString()
    }


    class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {
         var txvId: TextView
         var txvNodeId: TextView
         var txvName: TextView
         var txvFullName: TextView
         var private: TextView

        init {
            txvId=itemLayoutView.findViewById(R.id.txv_id)
            txvNodeId=itemLayoutView.findViewById(R.id.txv_node_id)
            txvName =itemLayoutView.findViewById(R.id.txv_name)
            txvFullName =itemLayoutView.findViewById(R.id.txv_full_name)
            private =itemLayoutView.findViewById(R.id.txv_private)
        }

    }
}