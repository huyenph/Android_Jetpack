package com.utildev.androidjetpack.presentation.adapter

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.utildev.androidjetpack.presentation.base.BaseFragment
import com.utildev.androidjetpack.presentation.fragment.*

class MyPagerAdapter(fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    companion object {
        const val NUM_ITEM = 10
    }

    override fun getItem(position: Int): BaseFragment {
        return when(position) {
            0 -> FirstFragment.newInstance(0)
            1 -> SecondFragment.newInstance(1)
            2 -> ThirdFragment.newInstance(2)
            3 -> FourthFragment.newInstance(3)
            4 -> FirstFragment.newInstance(0)
            5 -> SecondFragment.newInstance(1)
            6 -> ThirdFragment.newInstance(2)
            7 -> FourthFragment.newInstance(3)
            8 -> FirstFragment.newInstance(0)
            else -> FifthFragment.newInstance(4)
        }
    }

    override fun getCount(): Int = NUM_ITEM

    override fun getPageTitle(position: Int): CharSequence? {
        return "page $position"
    }


}