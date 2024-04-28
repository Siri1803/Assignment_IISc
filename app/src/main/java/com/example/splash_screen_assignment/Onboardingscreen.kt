package com.example.splash_screen_assignment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import android.view.WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_onboardingscreen.*


class Onboardingscreen : AppCompatActivity() {

    private lateinit var myAdapter: MyAdapter
    private lateinit var dotsTv: Array<TextView?>
    private lateinit var layouts : IntArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(!isFirstTimeAppStart()){
            setAppStartStatus(false)
            startActivity(Intent(this , AnotherActvity::class.java))
            finish()
        }
        setContentView(R.layout.activity_onboardingscreen)


        val actionBar: ActionBar? = supportActionBar
        actionBar!!.hide()

        statusBarTransparent()

        btn_next.setOnClickListener {
            val currentPage: Int = viewPager.currentItem + 1
            if(currentPage < layouts.size){
                viewPager.currentItem =currentPage
            }
            else{
                setAppStartStatus(false)
                startActivity(Intent(this , AnotherActvity::class.java))
                finish()
            }
        }
        btn_skip.setOnClickListener {
            setAppStartStatus(false)
            startActivity(Intent(this , AnotherActvity::class.java))
            finish()

        }

        layouts = intArrayOf(R.layout.slide_1,R.layout.slide_2)
        myAdapter = MyAdapter(layouts, applicationContext)
        viewPager.adapter = myAdapter
        viewPager.addOnPageChangeListener(object :ViewPager.OnPageChangeListener
        {
            override fun onPageScrollStateChanged(state: Int) {
                TODO("Not yet implemented")
            }
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                TODO("Not yet implemented")
            }

            override fun onPageSelected(position: Int) {
                if (position == layouts.size - 1){
                    btn_next.text = "START"
                    btn_skip.visibility = View.GONE
                }
                else{
                    btn_next.text = "NEXT"
                    btn_skip.visibility = View.VISIBLE
                }
                setDots(position)

            }



        })
        setDots(0)


    }

    private fun isFirstTimeAppStart() : Boolean{
        val pref = applicationContext.getSharedPreferences("SLIDER_APP", Context.MODE_PRIVATE)
        return pref.getBoolean("APP_START", true )

    }
    private fun setAppStartStatus(status : Boolean){
        val pref = applicationContext.getSharedPreferences("SLIDER_APP", Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = pref.edit()
        editor.putBoolean("APP_START", status)
        editor.apply()

    }

    private fun statusBarTransparent()
    {
        if (Build.VERSION.SDK_INT >= 21)
        {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            val window = window
            window.addFlags(FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        }
    }


}