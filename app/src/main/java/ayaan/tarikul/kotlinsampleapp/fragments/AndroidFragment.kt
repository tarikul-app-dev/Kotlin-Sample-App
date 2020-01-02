package ayaan.tarikul.kotlinsampleapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView

import ayaan.tarikul.kotlinsampleapp.R
import ayaan.tarikul.kotlinsampleapp.utils.UIContent


class AndroidFragment : Fragment() {
    lateinit var webView: WebView
    lateinit var rootView: View
    var uiContent : UIContent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_android, container, false)

        initViews(rootView)
        return  rootView;
    }

    fun initViews(rootView:View){
        webView = rootView.findViewById(R.id.webview)
        uiContent = UIContent(requireContext())

        if(uiContent!!.isConnectingToInternet()){
            webView.loadUrl("https://www.android.com/")
        }else{
            uiContent!!.showExitDialog("Internet","No Internet Connect")
        }

    }
         
}
