package com.example.karma.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.karma.R
import com.example.karma.adapters.ViewPagerAdapter
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.fragment_main_screen.*


class mainScreenFragment : Fragment() {

    enum class ProviderType{
        BASIC
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpTabs()

    }

    private fun setUpTabs() {
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(ProfileFragment(),"Explora")
        adapter.addFragment(mainhomeFragment(),"Home")
        adapter.addFragment(favoresFragment(),"Favores")
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)

        tabLayout.getTabAt(0)!!.setIcon(R.drawable.explore_icon)
        tabLayout.getTabAt(1)!!.setIcon(R.drawable.home_icon)
        tabLayout.getTabAt(2)!!.setIcon(R.drawable.favores_icon)
    }

}