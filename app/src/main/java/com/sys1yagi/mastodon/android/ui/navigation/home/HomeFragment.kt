package com.sys1yagi.mastodon.android.ui.navigation.home

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sys1yagi.fragmentcreator.annotation.FragmentCreator
import com.sys1yagi.mastodon.android.R
import com.sys1yagi.mastodon.android.databinding.FragmentHomeBinding
import com.sys1yagi.mastodon.android.extensions.gone
import com.sys1yagi.mastodon.android.extensions.visible
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

@FragmentCreator
class HomeFragment : Fragment(), HomeContract.View {

    @Inject
    lateinit var presenter: HomeContract.Presenter

    lateinit var binding: FragmentHomeBinding

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        binding.recyclerView.also {
            it.layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun showError(message: String) {

    }
}
