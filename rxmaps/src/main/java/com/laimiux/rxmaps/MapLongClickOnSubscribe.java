package com.laimiux.rxmaps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.laimiux.rxmaps.internal.MainThreadSubscription;
import com.laimiux.rxmaps.internal.Preconditions;

import rx.Observable;
import rx.Subscriber;

public class MapLongClickOnSubscribe implements Observable.OnSubscribe<LatLng> {
  private final GoogleMap map;

  public MapLongClickOnSubscribe(GoogleMap map) {
    this.map = map;
  }

  @Override public void call(final Subscriber<? super LatLng> subscriber) {
    Preconditions.checkUiThread();

    map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
      @Override public void onMapLongClick(LatLng latLng) {
        if (!subscriber.isUnsubscribed()) {
          subscriber.onNext(latLng);
        }
      }
    });

    subscriber.add(new MainThreadSubscription() {
      @Override protected void onUnsubscribe() {
        map.setOnMapLongClickListener(null);
      }
    });
  }
}
