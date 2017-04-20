package com.sys1yagi.mastodon.android.ui.auth.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jakewharton.rxbinding2.widget.RxTextView
import com.sys1yagi.mastodon.android.R
import com.sys1yagi.mastodon.android.databinding.ActivityLoginBinding
import com.sys1yagi.mastodon.android.extensions.contentViewBinding
import com.sys1yagi.mastodon.android.extensions.gone
import com.sys1yagi.mastodon.android.extensions.visible
import dagger.android.AndroidInjection
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), LoginContract.View {

    companion object {
        const val ARGS_INSTANCE_NAME = "instance_name"
        fun createIntent(context: Context, instanceName: String): Intent {
            return Intent(context, LoginActivity::class.java).apply {
                putExtra(ARGS_INSTANCE_NAME, instanceName)
            }
        }
    }

    @Inject
    lateinit var presenter: LoginContract.Presenter

    val binding: ActivityLoginBinding by contentViewBinding(R.layout.activity_login)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "Login"
        }
        binding.apply {
            startOauth.setOnClickListener {
                presenter.startOAuth()
            }
            saveToken.setOnClickListener {
                presenter.saveAccessToken(code.text.toString())
            }
            RxTextView.afterTextChangeEvents(code)
                    .map { !it.editable().isNullOrEmpty() }
                    .subscribe {
                        saveToken.isEnabled = it
                    }
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

    override fun showLoginMessage(message: String) {
        binding.title.text = message
    }

    override fun showError(message: String) {
        binding.progressBar.gone()
        binding.errorText.visible()
        binding.errorText.text = message
    }
}
