package com.sys1yagi.mastodon.android.ui.navigation.home.notification

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sys1yagi.fragmentcreator.annotation.FragmentCreator
import com.sys1yagi.mastodon.android.R
import com.sys1yagi.mastodon.android.databinding.FragmentNotificationBinding
import com.sys1yagi.mastodon.android.extensions.gone
import com.sys1yagi.mastodon.android.extensions.visible
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

@FragmentCreator
class NotificationFragment : Fragment(), NotificationContract.View {

    @Inject
    lateinit var presenter: NotificationContract.Presenter

    lateinit var binding: FragmentNotificationBinding

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_notification, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNotificationBinding.bind(view)
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

    override fun showNotifications(viewModel: NotificationViewModel) {

    }

    override fun showError(message: String) {

    }
}
