package com.wise99.primrose_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

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
        when(item.itemId)
        {
            R.id.home -> Toast.makeText(applicationContext, "홈", Toast.LENGTH_SHORT).show()
            R.id.name -> Toast.makeText(applicationContext, "이름 찾기", Toast.LENGTH_SHORT).show()
            R.id.mean -> Toast.makeText(applicationContext, "꽃말 찾기", Toast.LENGTH_SHORT).show()
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