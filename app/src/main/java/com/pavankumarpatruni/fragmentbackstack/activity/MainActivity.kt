package com.pavankumarpatruni.fragmentbackstack.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.pavankumarpatruni.fragmentaddvsreplace.fragments.*
import com.pavankumarpatruni.fragmentbackstack.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var fragmentTransaction: FragmentTransaction? = null
    private var fragmentManager: FragmentManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.add(R.id.fragmentContainer, DefaultFragment(), "fragments")
        fragmentTransaction?.commit()

        fragmentManager?.addOnBackStackChangedListener {

            val length = fragmentManager?.backStackEntryCount
            println("Total stack length $length")

            for (index in 0 until length!!) {
                println(fragmentManager?.getBackStackEntryAt(index)?.name)
            }
        }

        buttonAddFragment.setOnClickListener {
            fragmentTransaction = fragmentManager?.beginTransaction()

            val length = fragmentManager?.backStackEntryCount
            val count = length?.rem(4)?.plus(1)

            when (count) {
                0 -> fragmentTransaction?.add(
                    R.id.fragmentContainer,
                    DefaultFragment(),
                    "fragments"
                )
                1 -> fragmentTransaction?.replace(
                    R.id.fragmentContainer,
                    OneFragment(),
                    "fragments"
                )
                2 -> fragmentTransaction?.replace(
                    R.id.fragmentContainer,
                    TwoFragment(),
                    "fragments"
                )
                3 -> fragmentTransaction?.replace(
                    R.id.fragmentContainer,
                    ThreeFragment(),
                    "fragments"
                )
                4 -> fragmentTransaction?.replace(
                    R.id.fragmentContainer,
                    FourFragment(),
                    "fragments"
                )
            }
            fragmentTransaction?.addToBackStack("$count fragment")
            fragmentTransaction?.commit()
        }

        buttonPopFragment.setOnClickListener {
            fragmentManager?.popBackStack()
        }

        buttonRemoveFragment.setOnClickListener {
            val fragment = fragmentManager?.findFragmentById(R.id.fragmentContainer)
            if (fragment != null) {
                fragmentTransaction = fragmentManager?.beginTransaction()
                fragmentTransaction?.remove(fragment)
                fragmentTransaction?.commit()
            }
        }

    }
}
