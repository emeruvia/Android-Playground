package com.example.android.devbyteviewer.ui;

import java.lang.System;

/**
 * Click listener for Videos. By giving the block a name it helps a reader understand what it does.
 */
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0019\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0004R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\u000b"}, d2 = {"Lcom/example/android/devbyteviewer/ui/VideoClick;", "", "block", "Lkotlin/Function1;", "Lcom/example/android/devbyteviewer/domain/DevByteVideo;", "", "(Lkotlin/jvm/functions/Function1;)V", "getBlock", "()Lkotlin/jvm/functions/Function1;", "onClick", "video", "app_debug"})
public final class VideoClick {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<com.example.android.devbyteviewer.domain.DevByteVideo, kotlin.Unit> block = null;
    
    /**
     * Called when a video is clicked
     *
     * @param video the video that was clicked
     */
    public final void onClick(@org.jetbrains.annotations.NotNull()
    com.example.android.devbyteviewer.domain.DevByteVideo video) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.jvm.functions.Function1<com.example.android.devbyteviewer.domain.DevByteVideo, kotlin.Unit> getBlock() {
        return null;
    }
    
    public VideoClick(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.example.android.devbyteviewer.domain.DevByteVideo, kotlin.Unit> block) {
        super();
    }
}