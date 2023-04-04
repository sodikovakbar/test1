package com.oil.bottomnavigationview

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.oil.bottomnavigationview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.openMenu.setOnClickListener{
            binding.container.open()
        }
        binding.drawerLayout.setNavigationItemSelectedListener {
            when(it.itemId){
             R.id.home->Toast.makeText(this,"HOME",Toast.LENGTH_SHORT).show()
             R.id.settings->Toast.makeText(this,"Setting",Toast.LENGTH_SHORT).show()
             R.id.favouret->Toast.makeText(this,"Favorite",Toast.LENGTH_SHORT).show()
             R.id.about->{
                 val intent=Intent(this,MainActivity2::class.java)
                 startActivity(intent)
             }
            }
            true
        }
        binding.myBottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> binding.itemImg.setImageResource(R.drawable.ic_baseline_home_24)
                R.id.settings -> binding.itemImg.setImageResource(R.drawable.ic_baseline_settings_24)
                R.id.favouret -> binding.itemImg.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                R.id.about -> binding.itemImg.setImageResource(R.drawable.ic_baseline_info_24)
            }
            true
        }
    }
}