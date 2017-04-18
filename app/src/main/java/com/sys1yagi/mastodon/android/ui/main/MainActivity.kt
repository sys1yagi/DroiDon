package com.sys1yagi.mastodon.android.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.sys1yagi.mastodon.android.R
import com.sys1yagi.mastodon.android.databinding.ActivityMainBinding
import com.sys1yagi.mastodon.android.extensions.contentViewBinding
import com.sys1yagi.mastodon.android.extensions.gone
import com.sys1yagi.mastodon.android.extensions.visible
import com.sys1yagi.mastodon.android.ui.auth.setinstancename.SetInstanceNameActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

    companion object {
        fun createIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    @Inject
    lateinit var presenter: MainContract.Presenter

    val binding: ActivityMainBinding by contentViewBinding(R.layout.activity_main)

    val adapter: TimelineAdapter = TimelineAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = getString(R.string.app_name)
        }

        binding.recyclerView.also {
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(this)
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onPause() {
        presenter.onPause()
        super.onPause()
    }

    override fun showTimeline(viewModel: MainViewModel) {
        binding.progressBar.gone()
        binding.recyclerView.visible()
        adapter.addAll(viewModel.statuses)
    }

    override fun showError(message: String) {
        binding.progressBar.gone()
        binding.errorText.visible()
        binding.errorText.text = message
    }
}
