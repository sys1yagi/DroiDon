package ${packageName}

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sys1yagi.fragmentcreator.annotation.FragmentCreator
import ${applicationId}.R
import ${applicationId}.databinding.Fragment${className}Binding
import ${applicationId}.extensions.gone
import ${applicationId}.extensions.visible
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

@FragmentCreator
class ${className}Fragment : Fragment(), ${className}Contract.View {

    @Inject
    lateinit var presenter: ${className}Contract.Presenter

    lateinit var binding: Fragment${className}Binding

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment${className?replace("[A-Z]", "_$0", "r")?lower_case}, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = Fragment${className}Binding.bind(view)
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
