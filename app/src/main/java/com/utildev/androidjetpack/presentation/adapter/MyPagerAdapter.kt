package com.utildev.androidjetpack.presentation.adapter

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.utildev.androidjetpack.presentation.base.BaseFragment
import com.utildev.androidjetpack.presentation.fragment.question.QuestionFragment
import com.utildev.androidjetpack.presentation.fragment.tag.TagFragment
import com.utildev.androidjetpack.presentation.fragment.user.UserFragment

class MyPagerAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        const val NUM_ITEM = 3
    }

    override fun getItem(position: Int): BaseFragment {
        return when (position) {
//            0 -> TagFragment()
//            1 -> QuestionFragment()
//            else -> UserFragment()
            0 -> TagFragment()
            1 -> TagFragment()
            else -> TagFragment()
        }
    }

    override fun getCount(): Int = NUM_ITEM

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Tags"
            1 -> "Questions"
            else -> "Users"
        }
    }

    override fun getItemPosition(`object`: Any): Int {
        when(val fm = `object` as BaseFragment) {
            is TagFragment -> fm.onUpdate()
            is QuestionFragment -> fm.onUpdate()
            is UserFragment -> fm.onUpdate()
        }
        return super.getItemPosition(`object`)
    }

    interface FragmentUpdateListener {
        fun onUpdate()
    }
}