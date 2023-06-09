package com.example.moviedbapp.base.baseview

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.moviedbapp.base.exception.Failure

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    var viewBinding: VB? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = getUiBinding()
        return viewBinding?.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(initMenu(), menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onFirstLaunch(savedInstanceState, view)
        initUiListener()
    }

    override fun onStart() {
        super.onStart()
        onReExecute()
    }

    abstract fun getUiBinding(): VB
    abstract fun onFirstLaunch(savedInstanceState: Bundle?, view: View)
    abstract fun onReExecute()
    abstract fun initUiListener()
    abstract fun initMenu(): Int

    fun getParentFm() = requireActivity().supportFragmentManager

    fun getChildFm() = childFragmentManager

    fun onBackPressed() {
        requireActivity().onBackPressed()
    }

    fun showProgress() {
        (requireActivity() as BaseActivity<*>).showProgress()
    }

    fun hideProgress() {
        (requireActivity() as BaseActivity<*>).hideProgress()
    }

    fun showToast(message: String) {
        (requireActivity() as BaseActivity<*>).showToast(message)
    }

    open fun handleFailure(failure: Failure?) {
        (requireActivity() as BaseActivity<*>).handleFailure(failure)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }
}
