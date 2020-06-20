package dev.emg.powerfulandroid.ui.auth

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import dev.emg.powerfulandroid.databinding.FragmentLoggingBinding
import dev.emg.powerfulandroid.di.auth.AuthScope
import dev.emg.powerfulandroid.models.AuthToken
import dev.emg.powerfulandroid.ui.auth.state.LoginFields
import timber.log.Timber
import javax.inject.Inject

class LoginFragment : Fragment() {

//  @AuthScope
//  @Inject
//  lateinit var viewModelFactory: ViewModelProvider.Factory
//  private val viewModel: AuthViewModel by viewModels { viewModelFactory }

  @AuthScope
  @Inject lateinit var viewModel: AuthViewModel

  private var _binding: FragmentLoggingBinding? = null
  private val binding get() = _binding!!

  override fun onAttach(context: Context) {
    super.onAttach(context)

    (activity as AuthActivity).authComponent.inject(this)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    _binding = FragmentLoggingBinding.inflate(inflater, container, false)
    val view = binding.root
    return view
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)

    subscribeObservers()
    Timber.d("onViewCreated()!")
    binding.loginButton.setOnClickListener {
      Timber.d("loginButton(): was clicked")
      viewModel.setAuthToken(AuthToken(1, "asdfqwerasdfa "))
    }
  }

  override fun onDestroyView() {
    viewModel.setLoginFields(
      LoginFields(
        binding.inputEmail.toString(),
        binding.inputPassword.toString()
      )
    )

    super.onDestroyView()
  }

  fun subscribeObservers() {
    viewModel.viewState.observe(viewLifecycleOwner, Observer { result ->
      result.loginFields?.let { loginField ->
        loginField.login_email?.let { binding.inputEmail.setText(it) }
        loginField.login_password?.let { binding.inputPassword.setText(it) }
      }
    })
  }

}
