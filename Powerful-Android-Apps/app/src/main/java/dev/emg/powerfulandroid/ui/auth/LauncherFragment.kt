package dev.emg.powerfulandroid.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dev.emg.powerfulandroid.R
import dev.emg.powerfulandroid.databinding.FragmentLauncherBinding

class LauncherFragment : Fragment() {

  private var _binding: FragmentLauncherBinding? = null
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    _binding = FragmentLauncherBinding.inflate(inflater, container, false)
    val view = binding.root
    return view
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)

    binding.register.setOnClickListener {
      navRegistration()
    }

    binding.login.setOnClickListener {
      navLogin()
    }

    binding.forgotPassword.setOnClickListener {
      navForgotPassword()
    }

    binding.focusableView.requestFocus()
  }

  override fun onDestroyView() {
    _binding = null
    super.onDestroyView()
  }

  private fun navRegistration() {
    findNavController().navigate(R.id.action_launcherFragment_to_registerFragment)
  }

  private fun navLogin() {
    findNavController().navigate(R.id.action_launcherFragment_to_loginFragment)
  }

  private fun navForgotPassword() {
    findNavController().navigate(R.id.action_launcherFragment_to_forgotPasswordFragment)
  }
}
