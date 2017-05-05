package com.sys1yagi.mastodon.android.ui.navigation.home.toot

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import com.jakewharton.rxbinding2.widget.RxTextView
import com.sys1yagi.mastodon.android.R
import com.sys1yagi.mastodon.android.databinding.ActivityTootBinding
import com.sys1yagi.mastodon.android.extensions.*
import com.sys1yagi.mastodon.android.ui.navigation.NavigationActivity
import com.sys1yagi.mastodon4j.api.entity.Status
import dagger.android.AndroidInjection
import io.reactivex.disposables.Disposables
import javax.inject.Inject

class TootActivity : AppCompatActivity(), TootContract.View {

    companion object {
        const val ARGS_INSTANCE_NAME = "instance_name"
        const val ARGS_REPLY_TO_STATUS = "reply_to_status"
        fun createIntent(context: Context, instanceName: String, targetStatus: Status? = null): Intent {
            return Intent(context, TootActivity::class.java).apply {
                putExtra(ARGS_INSTANCE_NAME, instanceName)
                targetStatus?.let {
                    putExtra(ARGS_REPLY_TO_STATUS, context.gson().toJson(targetStatus))
                }
            }
        }
    }

    @Inject
    lateinit var presenter: TootContract.Presenter

    val primaryInstanceName by lazy<String> { intent.getRequired(ARGS_INSTANCE_NAME) }
    val replyToStatus by lazy {
        intent.getOptional<String?>(ARGS_REPLY_TO_STATUS, null)?.let {
            gson().fromJson(it, Status::class.java)
        }
    }

    var disposable = Disposables.empty()

    val binding: ActivityTootBinding by contentViewBinding(R.layout.activity_toot)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = getString(R.string.toot)
            setDisplayHomeAsUpEnabled(true)
        }

        replyToStatus?.let {
            it.account?.let {
                binding.status.setText("@${it.acct} ")
                binding.status.setSelection(binding.status.length())
            }
        }
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
        binding.apply {
            RxTextView.afterTextChangeEvents(status)
                    .map { it.editable()?.length ?: 0 }
                    .subscribe {
                        remain.text = "${500 - it}"
                    }

            toot.setOnClickListener {
                presenter.toot(status.text.toString(), replyToStatus)
            }
            attachment.setOnClickListener {
                presenter.onClickChooseAttachment()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
        disposable.dispose()
    }

    override fun onSupportNavigateUp(): Boolean {
        // TODO confirm when inputting
        finish()
        return true
    }

    override fun showProgress() {
        binding.progressBar.visible()
    }

    override fun showError(message: String) {
        binding.progressBar.gone()
    }

    override fun showAttachment(viewModel: TootViewModel) {
        binding.progressBar.gone()
        binding.attachmentContainer.visible()
        binding.attachedFile.setImageURI(viewModel.media.first().previewUrl)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        presenter.onActivityResult(requestCode, resultCode, data)
    }

}
