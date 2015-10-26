package com.laimiux.rxmaps.internal;

import android.os.Looper;

public class Preconditions {

  private Preconditions() {
  }

  public static void checkUiThread() {
    if (Looper.getMainLooper() != Looper.myLooper()) {
      throw new IllegalStateException(
          "Must be called from the main thread. Was: " + Thread.currentThread());
    }
  }
}
