package com.molbulak.smartmoney.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(manager: FragmentManager) :
    FragmentPagerAdapter(manager) {
    private val fragments = ArrayList<Fragment>()
    private val titles = ArrayList<String>()

    override fun getCount(): Int {
        return fragments.size
    }

    fun addFragment(fragment: Fragment) {
        fragments.add(fragment)
    }

    fun addPageTitle(title: String) {
        titles.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return super.getPageTitle(position)

    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }
}