package com.example.android.devbyteviewer.databinding;
import com.example.android.devbyteviewer.R;
import com.example.android.devbyteviewer.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentDevByteBindingImpl extends FragmentDevByteBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.recycler_view, 2);
    }
    // views
    @NonNull
    private final android.widget.FrameLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentDevByteBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }
    private FragmentDevByteBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2
            , (android.widget.ProgressBar) bindings[1]
            , (androidx.recyclerview.widget.RecyclerView) bindings[2]
            );
        this.loadingSpinner.setTag(null);
        this.mboundView0 = (android.widget.FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x8L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.viewModel == variableId) {
            setViewModel((com.example.android.devbyteviewer.viewmodels.DevByteViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewModel(@Nullable com.example.android.devbyteviewer.viewmodels.DevByteViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x4L;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeViewModelEventNetworkError((androidx.lifecycle.LiveData<java.lang.Boolean>) object, fieldId);
            case 1 :
                return onChangeViewModelPlaylist((androidx.lifecycle.LiveData<java.util.List<com.example.android.devbyteviewer.domain.DevByteVideo>>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewModelEventNetworkError(androidx.lifecycle.LiveData<java.lang.Boolean> ViewModelEventNetworkError, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelPlaylist(androidx.lifecycle.LiveData<java.util.List<com.example.android.devbyteviewer.domain.DevByteVideo>> ViewModelPlaylist, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        boolean androidxDatabindingViewDataBindingSafeUnboxViewModelEventNetworkError = false;
        androidx.lifecycle.LiveData<java.lang.Boolean> viewModelEventNetworkError = null;
        java.lang.Boolean viewModelEventNetworkErrorGetValue = null;
        com.example.android.devbyteviewer.viewmodels.DevByteViewModel viewModel = mViewModel;
        androidx.lifecycle.LiveData<java.util.List<com.example.android.devbyteviewer.domain.DevByteVideo>> viewModelPlaylist = null;
        java.util.List<com.example.android.devbyteviewer.domain.DevByteVideo> viewModelPlaylistGetValue = null;

        if ((dirtyFlags & 0xfL) != 0) {



                if (viewModel != null) {
                    // read viewModel.eventNetworkError
                    viewModelEventNetworkError = viewModel.getEventNetworkError();
                    // read viewModel.playlist
                    viewModelPlaylist = viewModel.getPlaylist();
                }
                updateLiveDataRegistration(0, viewModelEventNetworkError);
                updateLiveDataRegistration(1, viewModelPlaylist);


                if (viewModelEventNetworkError != null) {
                    // read viewModel.eventNetworkError.getValue()
                    viewModelEventNetworkErrorGetValue = viewModelEventNetworkError.getValue();
                }
                if (viewModelPlaylist != null) {
                    // read viewModel.playlist.getValue()
                    viewModelPlaylistGetValue = viewModelPlaylist.getValue();
                }


                // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.eventNetworkError.getValue())
                androidxDatabindingViewDataBindingSafeUnboxViewModelEventNetworkError = androidx.databinding.ViewDataBinding.safeUnbox(viewModelEventNetworkErrorGetValue);
        }
        // batch finished
        if ((dirtyFlags & 0xfL) != 0) {
            // api target 1

            com.example.android.devbyteviewer.util.BindingAdaptersKt.hideIfNetworkError(this.loadingSpinner, androidxDatabindingViewDataBindingSafeUnboxViewModelEventNetworkError, viewModelPlaylistGetValue);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewModel.eventNetworkError
        flag 1 (0x2L): viewModel.playlist
        flag 2 (0x3L): viewModel
        flag 3 (0x4L): null
    flag mapping end*/
    //end
}