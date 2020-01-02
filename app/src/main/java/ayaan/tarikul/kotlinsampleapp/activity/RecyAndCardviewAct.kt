package ayaan.tarikul.kotlinsampleapp.activity


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ayaan.tarikul.kotlinsampleapp.R
import ayaan.tarikul.kotlinsampleapp.adapter.AllReposAdapter
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
    lateinit var toolbar: Toolbar
   // lateinit var adapter:ReposAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recy_and_cardview)
        toolbar = findViewById(R.id.toolbar_recy_card) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { onBackPressed() }

        initViews()
    }

    fun initViews(){
        uiContent = UIContent(this)
        spinDialog = SpinDialog(this)
        recyAllRepos = findViewById(R.id.rcv_all_repos)


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
                setDataToAdapter(allReposList)
                //recyAllRepos.adapter!!.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<ReposDataModel>>?, t: Throwable?) {
                spinDialog!!.dismis()
            }

        })
    }


    private fun setDataToAdapter(allReposList:ArrayList<ReposDataModel> )
    {
        recyAllRepos.apply {

//            recyAllRepos.layoutManager = LinearLayoutManager(this@RecyAndCardviewAct, LinearLayout.VERTICAL, false)
//            var adapter = AllReposAdapter(allReposList,this@RecyAndCardviewAct)
//            recyAllRepos.adapter = adapter

            var adapter = AllReposAdapter(allReposList,this@RecyAndCardviewAct)
            val layoutManager = LinearLayoutManager(this@RecyAndCardviewAct)
            recyAllRepos?.layoutManager = layoutManager
            recyAllRepos?.itemAnimator = DefaultItemAnimator()

            recyAllRepos?.adapter = adapter
            adapter.notifyDataSetChanged()

        }




    }


}
