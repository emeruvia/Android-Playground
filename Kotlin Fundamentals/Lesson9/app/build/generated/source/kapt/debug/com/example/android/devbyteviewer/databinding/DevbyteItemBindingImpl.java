package com.example.android.devbyteviewer.databinding;
import com.example.android.devbyteviewer.R;
import com.example.android.devbyteviewer.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class DevbyteItemBindingImpl extends DevbyteItemBinding implements com.example.android.devbyteviewer.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.left_well, 5);
        sViewsWithIds.put(R.id.right_well, 6);
        sViewsWithIds.put(R.id.play_icon, 7);
    }
    // views
    @NonNull
    private final com.google.android.material.card.MaterialCardView mboundView0;
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback1;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public DevbyteItemBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds));
    }
    private DevbyteItemBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.view.View) bindings[4]
            , (android.widget.TextView) bindings[2]
            , (androidx.constraintlayout.widget.Guideline) bindings[5]
            , (android.widget.ImageView) bindings[7]
            , (androidx.constraintlayout.widget.Guideline) bindings[6]
            , (android.widget.TextView) bindings[1]
            , (android.widget.ImageView) bindings[3]
            );
        this.clickableOverlay.setTag(null);
        this.description.setTag(null);
        this.mboundView0 = (com.google.android.material.card.MaterialCardView) bindings[0];
        this.mboundView0.setTag(null);
        this.title.setTag(null);
        this.videoThumbnail.setTag(null);
        setRootTag(root);
        // listeners
        mCallback1 = new com.example.android.devbyteviewer.generated.callback.OnClickListener(this, 1);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
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
        if (BR.videoCallback == variableId) {
            setVideoCallback((com.example.android.devbyteviewer.ui.VideoClick) variable);
        }
        else if (BR.video == variableId) {
            setVideo((com.example.android.devbyteviewer.domain.DevByteVideo) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setVideoCallback(@Nullable com.example.android.devbyteviewer.ui.VideoClick VideoCallback) {
        this.mVideoCallback = VideoCallback;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.videoCallback);
        super.requestRebind();
    }
    public void setVideo(@Nullable com.example.android.devbyteviewer.domain.DevByteVideo Video) {
        this.mVideo = Video;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.video);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
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
        java.lang.String videoShortDescription = null;
        com.example.android.devbyteviewer.ui.VideoClick videoCallback = mVideoCallback;
        com.example.android.devbyteviewer.domain.DevByteVideo video = mVideo;
        java.lang.String videoTitle = null;
        java.lang.String VideoThumbnail1 = null;

        if ((dirtyFlags & 0x6L) != 0) {



                if (video != null) {
                    // read video.shortDescription
                    videoShortDescription = video.getShortDescription();
                    // read video.title
                    videoTitle = video.getTitle();
                    // read video.thumbnail
                    VideoThumbnail1 = video.getThumbnail();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x4L) != 0) {
            // api target 1

            this.clickableOverlay.setOnClickListener(mCallback1);
        }
        if ((dirtyFlags & 0x6L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.description, videoShortDescription);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.title, videoTitle);
            com.example.android.devbyteviewer.util.BindingAdaptersKt.setImageUrl(this.videoThumbnail, VideoThumbnail1);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        // localize variables for thread safety
        // videoCallback
        com.example.android.devbyteviewer.ui.VideoClick videoCallback = mVideoCallback;
        // video
        com.example.android.devbyteviewer.domain.DevByteVideo video = mVideo;
        // videoCallback != null
        boolean videoCallbackJavaLangObjectNull = false;



        videoCallbackJavaLangObjectNull = (videoCallback) != (null);
        if (videoCallbackJavaLangObjectNull) {



            videoCallback.onClick(video);
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): videoCallback
        flag 1 (0x2L): video
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}