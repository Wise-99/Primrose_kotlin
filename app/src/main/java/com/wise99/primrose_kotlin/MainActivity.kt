package com.wise99.primrose_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.wise99.primrose_kotlin.fragment.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var layoutDrawer: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val naviBtn = findViewById<ImageView>(R.id.btn_navi)
        naviBtn.setOnClickListener {
            layoutDrawer = findViewById<DrawerLayout>(R.id.layout_drawer)
            layoutDrawer.openDrawer(GravityCompat.START) // START : left , END : right
        }

        val naviView = findViewById<NavigationView>(R.id.navi_view)
        naviView.setNavigationItemSelectedListener(this) // 네비게이션 메뉴 아이템에 클릭 속성 부여

        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean { // 네비게이션 메뉴 아이템 클릭 시 수행

        val fragmentMain = MainFragment()
        val fragmentMap = MapFragment()
        val fragmentMean = MeanFragment()
        val fragmentName = NameFragment()
        val fragmentAll = AllFragment()

        when(item.itemId)
        {
            R.id.home ->
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.host_fragment, fragmentMain)
                    .commit()

            R.id.all ->
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.host_fragment, fragmentAll)
                    .commit()

            R.id.name ->
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.host_fragment, fragmentName)
                    .commit()
            R.id.mean ->
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.host_fragment, fragmentMean)
                    .commit()
            R.id.map ->
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.host_fragment, fragmentMap)
                    .commit()
        }

        layoutDrawer.closeDrawers() // 네비게이션 뷰 닫기
        return false
    }

    // back버튼 누를 시 수행하는 메소드
    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if(layoutDrawer.isDrawerOpen(GravityCompat.START)){
                layoutDrawer.closeDrawers()
            }
            else {
                finish()
            }
        }
    }
}