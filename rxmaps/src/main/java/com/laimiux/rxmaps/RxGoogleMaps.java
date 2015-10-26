package com.laimiux.rxmaps;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import rx.Observable;

/**
 * Static factory methods for creating {@linkplain Observable observables} for {@link GoogleMap}
 */
public class RxGoogleMaps {

  /**
   * Create an observable of camera position change events for {@code map}
   * <p/>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code map}. Unsubscribe
   * to free this reference.
   * <p/>
   * <em>Warning:</em> The created observable uses {@link GoogleMap#setOnCameraChangeListener}
   * to observe camera position changes. Only one observable can be used for a map at a time.
   */
  @CheckResult @NonNull
  public static Observable<CameraPosition> cameraPosition(@NonNull GoogleMap map) {
    return Observable.create(new CameraChangeOnSubscribe(map));
  }

  /**
   * Create an observable of map click events for {@code map}
   * <p/>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code map}. Unsubscribe
   * to free this reference.
   * <p/>
   * <em>Warning:</em> The created observable uses {@link GoogleMap#setOnMapLongClickListener}
   * to observe map long clicks. Only one observable can be used for a map at a time.
   */
  @CheckResult @NonNull
  public static Observable<LatLng> longClicks(@NonNull GoogleMap map) {
    return Observable.create(new MapLongClickOnSubscribe(map));
  }


  /**
   * Create an observable of map long click events for {@code map}
   * <p/>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code map}. Unsubscribe
   * to free this reference.
   * <p/>
   * <em>Warning:</em> The created observable uses {@link GoogleMap#setOnMapClickListener}
   * to observe map clicks. Only one observable can be used for a map at a time.
   */
  @CheckResult @NonNull
  public static Observable<LatLng> clicks(@NonNull GoogleMap map) {
    return Observable.create(new MapClickOnSubscribe(map));
  }

  private RxGoogleMaps() {
    throw new AssertionError("No instances.");
  }
}
