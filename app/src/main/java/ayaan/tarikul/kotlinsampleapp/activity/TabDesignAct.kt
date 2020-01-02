package ayaan.tarikul.kotlinsampleapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import ayaan.tarikul.kotlinsampleapp.R
import ayaan.tarikul.kotlinsampleapp.adapter.ViewPagerAdapter
import ayaan.tarikul.kotlinsampleapp.fragments.JavaFragment
import ayaan.tarikul.kotlinsampleapp.fragments.KotlinFragment
import ayaan.tarikul.kotlinsampleapp.fragments.AndroidFragment
import com.google.android.material.tabs.TabLayout

class TabDesignAct : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var viewPager:ViewPager
    lateinit var tabs:TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_design)
        toolbar = findViewById(R.id.toolbar_tab_design) as Toolbar
        setSupportActionBar(toolbar)
        initViews()
    }

    fun initViews(){
        viewPager = findViewById(R.id.viewPager)
        tabs = findViewById(R.id.tabs)
        setupViewerPage()
    }

    fun setupViewerPage(){
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(AndroidFragment(), "ANDROID")
        adapter.addFragment(KotlinFragment(), "KOTLIN")
        adapter.addFragment(JavaFragment(), "JAVA")

        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }



}
