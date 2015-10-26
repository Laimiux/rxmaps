package com.laimiux.rxmaps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.laimiux.rxmaps.internal.MainThreadSubscription;
import com.laimiux.rxmaps.internal.Preconditions;

import rx.Observable;
import rx.Subscriber;

public class CameraChangeOnSubscribe implements Observable.OnSubscribe<CameraPosition> {
  private final GoogleMap map;

  public CameraChangeOnSubscribe(GoogleMap map) {
    this.map = map;
  }

  @Override public void call(final Subscriber<? super CameraPosition> subscriber) {
    Preconditions.checkUiThread();

    GoogleMap.OnCameraChangeListener listener = new GoogleMap.OnCameraChangeListener() {
      @Override public void onCameraChange(CameraPosition cameraPosition) {
        if (!subscriber.isUnsubscribed()) {
          subscriber.onNext(cameraPosition);
        }
      }
    };
    map.setOnCameraChangeListener(listener);

    subscriber.add(new MainThreadSubscription() {
      @Override protected void onUnsubscribe() {
        map.setOnCameraChangeListener(null);
      }
    });
  }
}
