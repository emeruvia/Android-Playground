package com.example.android.devbyteviewer.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android.devbyteviewer.viewmodels.DevByteViewModel;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class FragmentDevByteBinding extends ViewDataBinding {
  @NonNull
  public final ProgressBar loadingSpinner;

  @NonNull
  public final RecyclerView recyclerView;

  @Bindable
  protected DevByteViewModel mViewModel;

  protected FragmentDevByteBinding(Object _bindingComponent, View _root, int _localFieldCount,
      ProgressBar loadingSpinner, RecyclerView recyclerView) {
    super(_bindingComponent, _root, _localFieldCount);
    this.loadingSpinner = loadingSpinner;
    this.recyclerView = recyclerView;
  }

  public abstract void setViewModel(@Nullable DevByteViewModel viewModel);

  @Nullable
  public DevByteViewModel getViewModel() {
    return mViewModel;
  }

  @NonNull
  public static FragmentDevByteBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_dev_byte, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static FragmentDevByteBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<FragmentDevByteBinding>inflateInternal(inflater, com.example.android.devbyteviewer.R.layout.fragment_dev_byte, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentDevByteBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_dev_byte, null, false, component)
   */
  @NonNull
  @Deprecated
  public static FragmentDevByteBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<FragmentDevByteBinding>inflateInternal(inflater, com.example.android.devbyteviewer.R.layout.fragment_dev_byte, null, false, component);
  }

  public static FragmentDevByteBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.bind(view, component)
   */
  @Deprecated
  public static FragmentDevByteBinding bind(@NonNull View view, @Nullable Object component) {
    return (FragmentDevByteBinding)bind(component, view, com.example.android.devbyteviewer.R.layout.fragment_dev_byte);
  }
}
