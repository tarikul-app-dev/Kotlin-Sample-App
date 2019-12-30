package ayaan.tarikul.kotlinsampleapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ayaan.tarikul.kotlinsampleapp.R
import ayaan.tarikul.kotlinsampleapp.adapter.ReposAdapter
import ayaan.tarikul.kotlinsampleapp.model.ReposDataModel
import ayaan.tarikul.kotlinsampleapp.retrofit.ApiClient
import ayaan.tarikul.kotlinsampleapp.utils.SpinDialog
import ayaan.tarikul.kotlinsampleapp.utils.UIContent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecyAndCardviewAct : AppCompatActivity() {
    var allReposList = ArrayList<ReposDataModel>()
    var uiContent : UIContent? = null
    var spinDialog:SpinDialog? = null
    lateinit var recyAllRepos: RecyclerView
    lateinit var adapter:ReposAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recy_and_cardview)
        initViews()
    }

    fun initViews(){
        uiContent = UIContent(this)
        spinDialog = SpinDialog(this)
        recyAllRepos = findViewById(R.id.rcv_all_repos)
        recyAllRepos.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        //setting up the adapter
        recyAllRepos.adapter= ReposAdapter(allReposList,this)


        if(uiContent!!.isConnectingToInternet()){
            spinDialog!!.showDialog(false)
            getData()
        }else{
            uiContent!!.showExitDialog("Internet","Your device is offline")
        }

    }

    private fun getData() {
        val call: Call<List<ReposDataModel>> = ApiClient.getClient.getAllReposInfo()
        call.enqueue(object : Callback<List<ReposDataModel>> {

            override fun onResponse(call: Call<List<ReposDataModel>>?, response: Response<List<ReposDataModel>>?) {
                spinDialog!!.dismis()
                allReposList.addAll(response!!.body()!!)
                recyAllRepos.adapter!!.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<ReposDataModel>>?, t: Throwable?) {
                spinDialog!!.dismis()
            }

        })
    }


}
