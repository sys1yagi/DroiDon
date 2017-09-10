package com.sys1yagi.mastodon.android.ui.auth.setinstancename

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jakewharton.rxbinding2.widget.RxTextView
import com.sys1yagi.mastodon.android.R
import com.sys1yagi.mastodon.android.databinding.ActivitySetInstanceNameBinding
import com.sys1yagi.mastodon.android.extensions.contentViewBinding
import com.sys1yagi.mastodon.android.extensions.gone
import com.sys1yagi.mastodon.android.extensions.visible
import com.sys1yagi.mastodon.android.ui.entrypoint.EntryPointActivity
import dagger.android.AndroidInjection
import io.reactivex.disposables.Disposables
import javax.inject.Inject

class SetInstanceNameActivity : AppCompatActivity(), SetInstanceNameContract.View, SetInstanceNameContract.Router {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, SetInstanceNameActivity::class.java)
        }
    }

    @Inject
    lateinit var presenter: SetInstanceNameContract.Presenter

    val binding: ActivitySetInstanceNameBinding by contentViewBinding(R.layout.activity_set_instance_name)

    var disposable = Disposables.empty()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        presenter.attachView(this)
        presenter.attachRouter(this)
        binding.apply {
            addInstanceNameButton.setOnClickListener {
                presenter.saveInstanceName(binding.instanceName.text.toString())
            }
        }
    }

    override fun onResume() {
        super.onResume()
        disposable = RxTextView.afterTextChangeEvents(binding.instanceName)
                .map { !it.editable().toString().isEmpty() }
                .subscribe {
                    binding.addInstanceNameButton.isEnabled = it
                }
        presenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
        disposable.dispose()
    }

    override fun showError(message: String) {
        binding.progressBar.gone()
        binding.errorText.visible()
        binding.errorText.text = message
    }

    override fun openEntryPointActivity() {
        startActivity(EntryPointActivity.createIntent(this))
    }
}
