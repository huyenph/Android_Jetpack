package com.utildev.androidjetpack.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.utildev.androidjetpack.presentation.base.BaseFragment
import com.utildev.androidjetpack.presentation.fragment.FirstFragment
import com.utildev.androidjetpack.presentation.fragment.SecondFragment
import com.utildev.androidjetpack.presentation.fragment.ThirdFragment

class MyPagerAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    companion object {
        const val NUM_ITEM = 3
    }

    override fun getItem(position: Int): BaseFragment {
        return when(position) {
            0 -> FirstFragment.newInstance(0)
            1 -> SecondFragment.newInstance(1)
            2 -> ThirdFragment.newInstance(2)
            else -> FirstFragment.newInstance(0)
        }
    }

    override fun getCount(): Int = NUM_ITEM

    override fun getPageTitle(position: Int): CharSequence? {
        return "page $position"
    }
}