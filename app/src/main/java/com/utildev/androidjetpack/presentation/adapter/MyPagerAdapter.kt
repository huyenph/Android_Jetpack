package com.utildev.androidjetpack.presentation.adapter

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.utildev.androidjetpack.presentation.base.BaseFragment
import com.utildev.androidjetpack.presentation.fragment.question.QuestionFragment
import com.utildev.androidjetpack.presentation.fragment.tag.TagFragment
import com.utildev.androidjetpack.presentation.fragment.user.UserFragment

class MyPagerAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    var siteParam = "stackoverflow"

    companion object {
        const val NUM_ITEM = 3
    }

    override fun getItem(position: Int): BaseFragment {
        return when (position) {
            0 -> TagFragment.newInstance(siteParam)
            1 -> QuestionFragment.newInstance(siteParam)
            else -> UserFragment.newInstance(siteParam)
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
}