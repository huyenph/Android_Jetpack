package com.utildev.androidjetpack.presentation.fragment.question

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.utildev.androidjetpack.BR
import com.utildev.androidjetpack.R
import com.utildev.androidjetpack.data.remote.response.question.QuestionItemResponse
import com.utildev.androidjetpack.presentation.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel
import com.utildev.androidjetpack.databinding.FragmentQuestionBinding
import com.utildev.androidjetpack.presentation.activity.MainActivity
import com.utildev.androidjetpack.presentation.adapter.MyPagerAdapter
import com.utildev.androidjetpack.presentation.adapter.QuestionAdapter
import com.utildev.androidjetpack.presentation.base.BaseAdapter

@Suppress("UNCHECKED_CAST", "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class QuestionFragment : BaseFragment<FragmentQuestionBinding, QuestionViewModel>(), BaseAdapter.AdapterListener,
    MyPagerAdapter.FragmentUpdateListener {

    private val vm: QuestionViewModel by viewModel()
    private lateinit var binding: FragmentQuestionBinding

    private lateinit var questionLm: GridLayoutManager
    private lateinit var questionAdapter: QuestionAdapter
    private val questions: ArrayList<QuestionItemResponse> = ArrayList()

    private var page = 0
    private var prePos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.questionLive.observe(this, Observer {
            if (it != null) {
                if (it.size == 0) {
                    questionAdapter.isEndList = true
                    questionAdapter.notifyDataSetChanged()
                } else {
                    questions.addAll(it)
                    questionAdapter.set(questions)
                    questionAdapter.isLoading = true
                }
            }
        })
    }

    override fun getLayoutId(): Int = R.layout.fragment_question

    override fun getBindingVariable(): Int? = BR.vm

    override fun getViewModel(): QuestionViewModel? = vm

    override fun init(view: View) {
        binding = getViewDataBinding() as FragmentQuestionBinding

        questionLm = GridLayoutManager(context, 1)
        questionAdapter = QuestionAdapter(binding.fmQuestionRv, questionLm, this)

        binding.fmQuestionRv.run {
            layoutManager = questionLm
            adapter = questionAdapter
            setHasFixedSize(true)
        }

        binding.fmQuestionSrl.setOnRefreshListener {
            page = 1
            questions.clear()
            questionAdapter.set(questions)
            vm.getQuestion((activity as MainActivity).siteParam, page, true)
            binding.fmQuestionSrl.isRefreshing = false
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val position = questionLm.findFirstVisibleItemPosition()
        outState.putString("question_site", (activity as MainActivity).siteParam)
        outState.putInt("question_position", position)
        outState.putInt("question_page", page + 1)
        outState.putSerializable("question", questions)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            val preSite = savedInstanceState.getString("question_site")
            if (preSite == (activity as MainActivity).siteParam) {
                prePos = savedInstanceState.getInt("question_position")
                page = savedInstanceState.getInt("question_page")
                questions.addAll(savedInstanceState.getSerializable("question") as ArrayList<QuestionItemResponse>)
                questionAdapter.set(questions)
                binding.fmQuestionRv.smoothScrollToPosition(prePos)
            } else {
                prePos = 0
                page = 1
            }
        } else {
            prePos = 0
            page = 1
        }
        vm.getQuestion((activity as MainActivity).siteParam, page, true)
    }

    override fun onItemClick(`object`: Any, position: Int) {}

    override fun onItemLongClick(`object`: Any, position: Int): Boolean {
        return false
    }

    override fun onLoadMore() {
        vm.getQuestion((activity as MainActivity).siteParam, ++page, false)
    }

    override fun onUpdate() {
        page = 1
        questions.clear()
        questionAdapter.set(questions)
        vm.getQuestion((activity as MainActivity).siteParam, page, true)
        binding.fmQuestionSrl.isRefreshing = false
    }
}