package com.laimiux.rxmaps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.laimiux.rxmaps.internal.MainThreadSubscription;
import com.laimiux.rxmaps.internal.Preconditions;

import rx.Observable;
import rx.Subscriber;

public class MapClickOnSubscribe implements Observable.OnSubscribe<LatLng> {
  private final GoogleMap map;

  public MapClickOnSubscribe(GoogleMap map) {
    this.map = map;
  }

  @Override public void call(final Subscriber<? super LatLng> subscriber) {
    Preconditions.checkUiThread();

    map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
      @Override public void onMapClick(LatLng latLng) {
        if (!subscriber.isUnsubscribed()) {
          subscriber.onNext(latLng);
        }
      }
    });

    subscriber.add(new MainThreadSubscription() {
      @Override protected void onUnsubscribe() {
        map.setOnMapClickListener(null);
      }
    });
  }
}
