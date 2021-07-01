package com.example.secondhomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.secondhomework.fragments.FirstFragment
import com.example.secondhomework.fragments.SecondFragment
import com.example.secondhomework.fragments.ThirdFragment
import com.example.secondhomework.interfaces.OnUserItemClick
import kotlin.concurrent.fixedRateTimer

class MainActivity : AppCompatActivity(), OnUserItemClick{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadInitialFragment()
    }

    fun loadInitialFragment(){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.frame_layout,FirstFragment());
        fragmentTransaction.commit()
    }

    override fun expand() {
        TODO("Not yet implemented")
    }

    override fun moveToSecondFragment(choosenPos: String) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,SecondFragment.newInstance(""," ",choosenPos))
        fragmentTransaction.commit()

    }

    override fun moveToThirdFragment(choosenPos: String) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,ThirdFragment.newInstance("","",choosenPos))
        fragmentTransaction.commit()
    }
}