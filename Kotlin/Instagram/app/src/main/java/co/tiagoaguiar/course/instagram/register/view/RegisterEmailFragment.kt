package co.tiagoaguiar.course.instagram.register.view

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.commom.base.DependencyInjector
import co.tiagoaguiar.course.instagram.commom.util.TxtWatcher
import co.tiagoaguiar.course.instagram.databinding.FragmentRegisterEmailBinding
import co.tiagoaguiar.course.instagram.login.presentation.LoginPresenter
import co.tiagoaguiar.course.instagram.register.RegisterEmail
import co.tiagoaguiar.course.instagram.register.presentation.RegisterEmailPresenter

class RegisterEmailFragment : Fragment(R.layout.fragment_register_email), RegisterEmail.View {

    private var binding: FragmentRegisterEmailBinding? = null
    private var fragmentAttachListener: FragmentAttachListener? = null
    override lateinit var presenter: RegisterEmail.Presenter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterEmailBinding.bind(view)
        presenter = RegisterEmailPresenter(this, DependencyInjector.registerEmailRepository())
        binding?.let {
            with(it) {
                when(resources.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
                    Configuration.UI_MODE_NIGHT_YES -> {
                        imgRegisterLogo.imageTintList = ColorStateList.valueOf(Color.WHITE)
                    }
                }
                editRegisterEmail.addTextChangedListener(watcher)
                textRegisterLogin.setOnClickListener {
                    activity?.finish()
                }
                buttonEmailNext.setOnClickListener {
                    presenter.create(editRegisterEmail.text.toString())
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FragmentAttachListener){
            fragmentAttachListener = context
        }
    }

    override fun onDestroy() {
        binding = null
        fragmentAttachListener = null
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun showProgress(enabled: Boolean) {
        binding?.buttonEmailNext?.showProgress(enabled)
    }

    override fun displayEmailFailure(emailError: Int?) {
        binding?.editRegisterEmail?.error = emailError?.let { getString(it) }
    }

    override fun onEmailFailure(msg: String) {
        binding?.editRegisterEmail?.error = msg
    }

    override fun goToNextScreen(email: String) {
        fragmentAttachListener?.goToNameAndPasswordScreen(email)
    }

    private val watcher = TxtWatcher {
        binding?.buttonEmailNext?.isEnabled = binding?.editRegisterEmail?.text.toString().isNotEmpty()
    }

}