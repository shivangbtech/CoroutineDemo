package com.coroutinedemo.base

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.coroutinedemo.R
import com.coroutinedemo.component.SingleEvent
import com.coroutinedemo.entity.Entity
import com.coroutinedemo.extentions.observe
import com.coroutinedemo.extentions.toast
import com.coroutinedemo.navigation.NavigationContract

/**
 * Created by shivanggoel
 */
abstract class BaseFragment<VB : ViewDataBinding, VM : BaseViewModel> : Fragment() {

    abstract fun provideViewModelClass(): Class<VM>
    abstract fun layoutId(): Int
    abstract val bindingVariable: Int

    protected lateinit var viewBinding: VB
    protected lateinit var viewModel: VM
    private lateinit var progressAlertDialog: AlertDialog
    open val navigationContract: NavigationContract? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(provideViewModelClass())
        handleObserver()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        val view = inflater.inflate(layoutId(), container, false)
        viewBinding = DataBindingUtil.bind(view)!!
        viewBinding.lifecycleOwner = this
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.setVariable(bindingVariable, viewModel)
        viewBinding.executePendingBindings()
    }


    private fun handleObserver() {
        observe(viewModel.mErrorLiveData, ::observeError)
        observe(viewModel.mLoadingLiveData, ::observeLoading)
        navigationContract?.subscribeNavigationEvent()
        navigationContract?.subscribeNetworkResponse()
    }

    internal fun observeError(error: SingleEvent<Entity.ErrorEntity>) {
        // handle error here
        error.getContentIfNotHandled()?.let {
            it.errors?.let {
                context?.let { it1 ->
                    it[0].errorMessage.toast(it1)

                }

            }
        }
    }

    /**
     * Method to observer loading
     */
    internal fun observeLoading(loadingInProgress: Boolean) {
        if (loadingInProgress) {
            showLoading()
        } else {
            hideLoading()
        }
    }

    protected fun showLoading() {
        hideLoading()
        val dialogBuilder = AlertDialog.Builder(context)
        val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?
        val dialogView = inflater?.inflate(R.layout.dialog_progress, null)
        dialogBuilder.setView(dialogView)
        dialogBuilder.setCancelable(false)
        progressAlertDialog = dialogBuilder.create()
        progressAlertDialog.show()
        progressAlertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

    }

    protected fun hideLoading() {
        if (::progressAlertDialog.isInitialized)
            progressAlertDialog.dismiss()
    }
}