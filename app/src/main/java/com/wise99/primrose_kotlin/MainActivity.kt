package com.wise99.primrose_kotlin

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.wise99.primrose_kotlin.databinding.ActivityMainBinding
import com.wise99.primrose_kotlin.fragment.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var activityMainBinding: ActivityMainBinding
    lateinit var layoutDrawer: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val naviBtn = findViewById<ImageView>(R.id.btn_navi)
        layoutDrawer = findViewById<DrawerLayout>(R.id.layout_drawer)

        naviBtn.setOnClickListener {
            layoutDrawer.openDrawer(GravityCompat.START) // START : left , END : right
        }

        val naviView = findViewById<NavigationView>(R.id.navi_view)
        naviView.setNavigationItemSelectedListener(this) // 네비게이션 메뉴 아이템에 클릭 속성 부여

        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean { // 네비게이션 메뉴 아이템 클릭 시 수행

        val fragmentMain = MainFragment()
        val fragmentSearch = SearchFragment()
        val fragmentAll = AllFragment()
        val fragmentMap = MapFragment()

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

            R.id.search ->
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.host_fragment, fragmentSearch)
                    .commit()
            R.id.map -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.host_fragment, fragmentMap)
                    .commit()
            }
        }

        layoutDrawer.closeDrawers() // 네비게이션 뷰 닫기
        return false
    }

    private var backPressedTime = 0L

    // back버튼 누를 시 수행하는 메소드
    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {

            // 좌측 layoutDrawer가 열려있을 때
            if(layoutDrawer.isDrawerOpen(GravityCompat.START)){
                layoutDrawer.closeDrawers()
            }

            // 뒤로가기 두번 눌렀을 때
            else if(System.currentTimeMillis() - backPressedTime <= 2000) {
                finish()
            }

            // 뒤로가기 한번 눌렀을 때
            else {
                backPressedTime = System.currentTimeMillis()
                Toast.makeText(this@MainActivity, "한 번 더 누르면 종료됩니다.",Toast.LENGTH_SHORT).show()
            }
        }
    }
}

