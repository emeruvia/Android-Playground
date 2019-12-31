package com.example.android.devbyteviewer.util;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"\u0000(\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\"\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0007\u001a\u0018\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007\u00a8\u0006\r"}, d2 = {"hideIfNetworkError", "", "view", "Landroid/view/View;", "isNetWorkError", "", "playlist", "", "setImageUrl", "imageView", "Landroid/widget/ImageView;", "url", "", "app_debug"})
public final class BindingAdaptersKt {
    
    /**
     * Binding adapter used to hide the spinner once data is available.
     */
    @androidx.databinding.BindingAdapter(value = {"isNetworkError", "playlist"})
    public static final void hideIfNetworkError(@org.jetbrains.annotations.NotNull()
    android.view.View view, boolean isNetWorkError, @org.jetbrains.annotations.Nullable()
    java.lang.Object playlist) {
    }
    
    /**
     * Binding adapter used to display images from URL using Glide
     */
    @androidx.databinding.BindingAdapter(value = {"imageUrl"})
    public static final void setImageUrl(@org.jetbrains.annotations.NotNull()
    android.widget.ImageView imageView, @org.jetbrains.annotations.NotNull()
    java.lang.String url) {
    }
}