package dev.emg.mvi.ui.main

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dev.emg.mvi.R
import dev.emg.mvi.ui.main.state.MainStateEvent
import java.lang.Exception

class MainFragment : Fragment() {

  private val TAG = MainFragment::class.java.name
  private lateinit var viewModel: MainViewModel

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_main, container, false)
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    setHasOptionsMenu(true)

    viewModel = activity?.let {
      ViewModelProvider(this).get(MainViewModel::class.java)
    } ?: throw Exception("Invalid activity")
  }

  override fun onCreateOptionsMenu(
    menu: Menu,
    inflater: MenuInflater
  ) {
    super.onCreateOptionsMenu(menu, inflater)
    inflater.inflate(R.menu.main_menu, menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.action_get_blogs -> triggerGetBlogsEvent()
      R.id.action_get_user -> triggerGetUserEvent()
    }

    return super.onOptionsItemSelected(item)
  }

  fun subscribeObservers() {
    viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->
      Log.d(TAG, "subscribeObservers(): DataState: ${dataState}")
      dataState.blogPosts?.let { blogPosts ->
        viewModel.setBlogListData(blogPosts)
      }

      dataState.user?.let { user ->
        viewModel.setUser(user)
      }
    })

    viewModel.viewState.observe(viewLifecycleOwner, Observer { viewState ->
      viewState.blogPosts?.let {
        Log.d(TAG, "subscribeObserver(): ViewState(): Setting blog to posts to RecyclerView $it")
      }
      viewState.user?.let {
        Log.d(TAG, "subscribeObserver(): ViewState(): Setting user data: $it")
      }
    })
  }

  private fun triggerGetBlogsEvent() {
    viewModel.setStateEvent(MainStateEvent.GetBlogPostEvent())
  }

  private fun triggerGetUserEvent() {
    viewModel.setStateEvent(MainStateEvent.GetUserEvent("1"))
  }
}