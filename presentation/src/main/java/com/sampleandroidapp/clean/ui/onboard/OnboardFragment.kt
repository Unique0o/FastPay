package com.sampleandroidapp.clean.ui.onboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterViewFlipper
import androidx.recyclerview.widget.LinearLayoutManager
import com.sampleandroidapp.clean.R
import com.sampleandroidapp.clean.databinding.FragmentOnboardBinding
import com.sampleandroidapp.clean.entities.OnboardModel
import com.sampleandroidapp.clean.ui.adapter.movie.flipperAdapter.OnboardAdapterViewFlipper
import com.sampleandroidapp.clean.ui.adapter.movie.simpleAdapter.DotsAdapter
import com.sampleandroidapp.clean.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardFragment : BaseFragment<FragmentOnboardBinding>() {

    private val TOTAL_NUMBER: Int = 4
    private lateinit var dotsAdapter: DotsAdapter
    private val onboardScreensDataList: ArrayList<OnboardModel> by lazy {
        arrayListOf()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentOnboardBinding = FragmentOnboardBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        prepareOnboardScreensDataList()
        setupViews()
        setAdapterViewFlipper()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OnboardFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    private fun setupViews(){
        setupRecyclerView()
    }

    private fun setupRecyclerView() = with(binding.rvDot) {
        dotsAdapter = DotsAdapter(TOTAL_NUMBER){

        }.apply {
            adapter = this
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
            setItemViewCacheSize(0)
        }
    }

    private fun prepareOnboardScreensDataList(){
        onboardScreensDataList.add(
            OnboardModel(
                image = R.drawable.undraw_security_on,
                title = getString(R.string.definitely_safe),
                description = getString(R.string.lorem_ipsum)
            )
        )
        onboardScreensDataList.add(
            OnboardModel(
                image = R.drawable.undraw_security_on,
                title = getString(R.string.definitely_safe),
                description = getString(R.string.lorem_ipsum)
            )
        )
        onboardScreensDataList.add(
            OnboardModel(
                image = R.drawable.undraw_security_on,
                title = getString(R.string.definitely_safe),
                description = getString(R.string.lorem_ipsum)
            )
        )
        onboardScreensDataList.add(
            OnboardModel(
                image = R.drawable.undraw_security_on,
                title = getString(R.string.definitely_safe),
                description = getString(R.string.lorem_ipsum)
            )
        )
        onboardScreensDataList.add(
            OnboardModel(
                image = R.drawable.undraw_security_on,
                title = getString(R.string.definitely_safe),
                description = getString(R.string.lorem_ipsum)
            )
        )
    }

    private fun setAdapterViewFlipper(){
        val adapterViewFlipper: AdapterViewFlipper = binding.adapterViewFlipper
        val adapter = OnboardAdapterViewFlipper(requireContext(), onboardScreensDataList)
        adapterViewFlipper.adapter = adapter
    }
}