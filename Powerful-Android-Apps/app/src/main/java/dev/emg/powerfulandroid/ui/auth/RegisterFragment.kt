package dev.emg.powerfulandroid.ui.auth

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import dev.emg.powerfulandroid.R
import dev.emg.powerfulandroid.di.auth.AuthScope
import dev.emg.powerfulandroid.utils.GenericApiResponse
import timber.log.Timber
import javax.inject.Inject

class RegisterFragment : Fragment() {

//  @AuthScope
//  @Inject
//  lateinit var viewModelFactory: ViewModelProvider.Factory
//  private val viewModel: AuthViewModel by viewModels { viewModelFactory }

  @AuthScope
  @Inject lateinit var viewModel: AuthViewModel

  override fun onAttach(context: Context) {
    super.onAttach(context)

    (activity as AuthActivity).authComponent.inject(this)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_register, container, false)
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)

    viewModel.testRegister()
        .observe(viewLifecycleOwner, Observer { response ->
          when (response) {
            is GenericApiResponse.ApiSuccessResponse -> {
              Timber.d("Register Response: ${response.body}")
            }
            is GenericApiResponse.ApiErrorResponse -> {
              Timber.e("Register Response: ${response.errorMessage}")
            }
            is GenericApiResponse.ApiEmptyResponse -> {
              Timber.d("Register Response: empty response")
            }
          }
        })
  }

}
