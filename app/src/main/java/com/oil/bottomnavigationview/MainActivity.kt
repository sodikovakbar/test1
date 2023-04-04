package com.oil.bottomnavigationview

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.oil.bottomnavigationview.adabters.MyContactAdapter
import com.oil.bottomnavigationview.databinding.ActivityMainBinding
import com.oil.bottomnavigationview.databinding.ItemDialogBinding
import com.oil.bottomnavigationview.db.MyContact
import com.oil.bottomnavigationview.db.Mydbhelper

class MainActivity : AppCompatActivity() {
    private lateinit var mydbhelper: Mydbhelper
    private lateinit var myContactAdapter: MyContactAdapter
    private lateinit var list: ArrayList<MyContact>
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        loadData()
        loadData1()
        binding.openMenu.setOnClickListener{
            binding.container.open()
        }
        binding.apply {
            drawerLayout.setNavigationItemSelectedListener {
                when(it.itemId){
                    R.id.home->{
                        loadData()
                        Toast.makeText(this@MainActivity, "Tartiblash", Toast.LENGTH_SHORT).show()
                    }
                    R.id.favouret-> {
                        loadData1()
                        Toast.makeText(this@MainActivity, "ID bo'yicha tartiblash", Toast.LENGTH_SHORT).show()
                    }
                    R.id.about->{
                        val dialog= AlertDialog.Builder(this@MainActivity).create()
                        val itemDialogBinding= ItemDialogBinding.inflate(layoutInflater)
                        dialog.setView(itemDialogBinding.root)
                        dialog.show()

                        itemDialogBinding.btnSave.setOnClickListener {
                            val myContact=MyContact(
                                itemDialogBinding.myName.text.toString().trim(),
                                itemDialogBinding.myNumber.text.toString()
                            )
                            mydbhelper.addContact(myContact)
                            loadData()
                            dialog.cancel()
                        }
                    }
                }
                true
            }
        }

    }
    private fun loadData() {
        mydbhelper = Mydbhelper(this)
        list = ArrayList()
        list.addAll(mydbhelper.getAllSortName())
        myContactAdapter = MyContactAdapter(list, object : MyContactAdapter.RvAction {
            override fun deleteContact(contact: MyContact,position:Int) {
                mydbhelper.deleteContact(contact)
                loadData()
                Toast.makeText(this@MainActivity, "deleted", Toast.LENGTH_SHORT).show()
            }
        })
        binding.rv.adapter = myContactAdapter
    }
    private fun loadData1() {
        mydbhelper = Mydbhelper(this)
        list = ArrayList()
        list.addAll(mydbhelper.getAllSortId())
        myContactAdapter = MyContactAdapter(list, object : MyContactAdapter.RvAction {
            override fun deleteContact(contact: MyContact,position:Int) {
                mydbhelper.deleteContact(contact)
                loadData1()
                Toast.makeText(this@MainActivity, "deleted", Toast.LENGTH_SHORT).show()
            }
        })
        binding.rv.adapter = myContactAdapter
    }

}


