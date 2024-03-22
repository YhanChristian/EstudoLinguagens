package co.tiagoaguiar.course.instagram.register.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.commom.base.DependencyInjector
import co.tiagoaguiar.course.instagram.commom.util.TxtWatcher
import co.tiagoaguiar.course.instagram.databinding.FragmentRegisterNamePasswordBinding
import co.tiagoaguiar.course.instagram.register.RegisterEmail
import co.tiagoaguiar.course.instagram.register.RegisterNamePassword
import co.tiagoaguiar.course.instagram.register.presentation.RegisterEmailPresenter
import co.tiagoaguiar.course.instagram.register.presentation.RegisterNamePasswordPresenter

class RegisterNamePasswordFragment : Fragment(R.layout.fragment_register_name_password), RegisterNamePassword.View {
    private var binding: FragmentRegisterNamePasswordBinding? = null
    override lateinit var presenter: RegisterNamePassword.Presenter
    private var fragmentAttachListener: FragmentAttachListener? = null
    companion object {
        const val KEY_EMAIL = "key_email"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterNamePasswordBinding.bind(view)
        presenter = RegisterNamePasswordPresenter(this, DependencyInjector.registerEmailRepository())

        val email = arguments?.getString(KEY_EMAIL) ?: throw IllegalArgumentException("Email not found")

        binding?.let {
            with(it) {
                editRegisterName.addTextChangedListener(watcher)
                editRegisterPassword.addTextChangedListener(watcher)
                editRegisterConfirmPassword.addTextChangedListener(watcher)
                buttonNameNext.setOnClickListener {
                    presenter.create(
                        email,
                        editRegisterName.text.toString(),
                        editRegisterPassword.text.toString(),
                        editRegisterConfirmPassword.text.toString()
                    )
                }
                textRegisterLogin.setOnClickListener {
                    activity?.finish()
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
        presenter.onDestroy()
        fragmentAttachListener = null
        super.onDestroy()
    }

    override fun showProgress(enabled: Boolean) {
        binding?.buttonNameNext?.showProgress(enabled)
    }

    override fun displayNameFailure(namError: Int?) {
        binding?.editRegisterName?.error = namError?.let { getString(it) }
    }

    override fun displayPasswordFailure(passwordError: Int?) {
        binding?.editRegisterPassword?.error = passwordError?.let { getString(it) }
    }

    override fun onCreateSuccess(msg: String) {
        fragmentAttachListener?.goToWelcomeScreen(msg)
    }

    override fun onCreateFailure(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }


    private val watcher = TxtWatcher {
        binding?.buttonNameNext?.isEnabled = binding?.editRegisterName?.text.toString().isNotEmpty()
                && binding?.editRegisterPassword?.text.toString().isNotEmpty()
                && binding?.editRegisterConfirmPassword?.text.toString().isNotEmpty()
    }

}