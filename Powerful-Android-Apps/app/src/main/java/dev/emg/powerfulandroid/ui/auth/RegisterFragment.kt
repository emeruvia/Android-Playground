package dev.emg.powerfulandroid.ui.auth

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import dev.emg.powerfulandroid.databinding.FragmentRegisterBinding
import dev.emg.powerfulandroid.di.auth.AuthScope
import dev.emg.powerfulandroid.ui.auth.state.RegistrationFields
import javax.inject.Inject

class RegisterFragment : Fragment() {

//  @AuthScope
//  @Inject
//  lateinit var viewModelFactory: ViewModelProvider.Factory
//  private val viewModel: AuthViewModel by viewModels { viewModelFactory }

  @AuthScope
  @Inject lateinit var viewModel: AuthViewModel

  private var _binding: FragmentRegisterBinding? = null
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
    _binding = FragmentRegisterBinding.inflate(inflater, container, false)
    val view = binding.root
    return view
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)

    subscribeObservers()
  }

  override fun onDestroyView() {
    viewModel.setRegistrationFields(
      RegistrationFields(
        binding.inputEmail.toString(),
        binding.inputUsername.toString(),
        binding.inputPassword.toString(),
        binding.inputPasswordConfirm.toString()
      )
    )

    super.onDestroyView()
  }

  private fun subscribeObservers() {
    viewModel.viewState.observe(viewLifecycleOwner, Observer { result ->
      result.registrationFields?.let { registrationField ->
        registrationField.registrationEmail?.let { binding.inputEmail.setText(it) }
        registrationField.registrationUsername?.let { binding.inputUsername.setText(it) }
        registrationField.registrationPassword?.let { binding.inputPassword.setText(it) }
        registrationField.registrationConfirmPassword?.let { binding.inputPasswordConfirm.setText(it) }
      }
    })
  }

}
