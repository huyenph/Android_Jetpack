package com.utildev.androidjetpack.presentation.fragment.question

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.utildev.androidjetpack.R
import com.utildev.androidjetpack.data.remote.response.question.QuestionItemResponse
import com.utildev.androidjetpack.presentation.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel
import com.utildev.androidjetpack.databinding.FragmentQuestionBinding
import com.utildev.androidjetpack.presentation.adapter.QuestionAdapter
import com.utildev.androidjetpack.presentation.base.BaseAdapter
import kotlinx.android.synthetic.main.fragment_question.view.*

@Suppress("UNCHECKED_CAST", "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class QuestionFragment : BaseFragment(), BaseAdapter.AdapterListener {
    private val vm: QuestionViewModel by viewModel()
    private lateinit var mView: View

    private var questionLm: GridLayoutManager? = null
    private var questionAdapter: QuestionAdapter? = null
    private var questions: ArrayList<QuestionItemResponse>? = null
    private var siteParam = "stackoverflow"
    private var page = 0
    private var prePos = 0

    companion object {
        fun newInstance(site: String) = QuestionFragment().apply {
            arguments = Bundle().apply {
                putString("site", site)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.questionLive.observe(this, Observer {
            if (it != null) {
                if (it.size == 0) {
                    questionAdapter!!.isEndList = true
                    questionAdapter!!.notifyDataSetChanged()
                } else {
                    questions!!.addAll(it)
                    questionAdapter!!.set(questions!!)
                    questionAdapter!!.isLoading = true
                }
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentQuestionBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_question, container, false)
        binding.vm = vm
        mView = binding.root
        init()
        return mView
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val position = questionLm!!.findFirstVisibleItemPosition()
        outState.putInt("question_position", position)
        outState.putInt("question_page", page + 1)
        outState.putSerializable("question", questions)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            prePos = savedInstanceState.getInt("question_position")
            page = savedInstanceState.getInt("question_page")
            questions!!.addAll(savedInstanceState.getSerializable("question") as ArrayList<QuestionItemResponse>)
            questionAdapter!!.set(questions!!)
            mView.fmQuestion_rv.smoothScrollToPosition(prePos)
        } else {
            prePos = 0
            page = 1
        }
        vm.getQuestion(siteParam, page, true)
    }

    override fun onItemClick(`object`: Any, position: Int) {}

    override fun onItemLongClick(`object`: Any, position: Int): Boolean {
        return false
    }

    override fun onLoadMore() {
        vm.getQuestion(siteParam, ++page, false)
    }

    private fun init() {
        if (arguments != null) {
            siteParam = arguments!!.getString("site")
        }
        questions = ArrayList()
        questionLm = GridLayoutManager(context, 1)
        questionAdapter = QuestionAdapter(mView.fmQuestion_rv, questionLm, this)

        mView.fmQuestion_rv.run {
            layoutManager = questionLm
            adapter = questionAdapter
            setHasFixedSize(true)
        }

        mView.fmQuestion_srl.setOnRefreshListener {
            page = 1
            questions!!.clear()
            questionAdapter!!.set(questions!!)
            vm.getQuestion(siteParam, page, true)
            mView.fmQuestion_srl.isRefreshing = false
        }
    }
}