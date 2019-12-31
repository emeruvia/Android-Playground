package com.example.android.devbyteviewer.ui;

import java.lang.System;

/**
 * RecyclerView Adapter for setting up data binding on the items in the list.
 */
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0011H\u0016J\u0018\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0011H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R0\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f\u00a8\u0006\u001a"}, d2 = {"Lcom/example/android/devbyteviewer/ui/DevByteAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/example/android/devbyteviewer/ui/DevByteViewHolder;", "callback", "Lcom/example/android/devbyteviewer/ui/VideoClick;", "(Lcom/example/android/devbyteviewer/ui/VideoClick;)V", "getCallback", "()Lcom/example/android/devbyteviewer/ui/VideoClick;", "value", "", "Lcom/example/android/devbyteviewer/domain/DevByteVideo;", "videos", "getVideos", "()Ljava/util/List;", "setVideos", "(Ljava/util/List;)V", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "app_debug"})
public final class DevByteAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.example.android.devbyteviewer.ui.DevByteViewHolder> {
    
    /**
     * The videos that our Adapter will show
     */
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.example.android.devbyteviewer.domain.DevByteVideo> videos;
    @org.jetbrains.annotations.NotNull()
    private final com.example.android.devbyteviewer.ui.VideoClick callback = null;
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.example.android.devbyteviewer.domain.DevByteVideo> getVideos() {
        return null;
    }
    
    public final void setVideos(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.android.devbyteviewer.domain.DevByteVideo> value) {
    }
    
    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     */
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.example.android.devbyteviewer.ui.DevByteViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     */
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.example.android.devbyteviewer.ui.DevByteViewHolder holder, int position) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.android.devbyteviewer.ui.VideoClick getCallback() {
        return null;
    }
    
    public DevByteAdapter(@org.jetbrains.annotations.NotNull()
    com.example.android.devbyteviewer.ui.VideoClick callback) {
        super();
    }
}