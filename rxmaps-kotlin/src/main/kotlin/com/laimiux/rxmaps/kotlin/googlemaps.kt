package com.laimiux.rxmaps.kotlin

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.laimiux.rxmaps.RxGoogleMaps
import rx.Observable


public fun GoogleMap.cameraPosition(): Observable<CameraPosition> =
    RxGoogleMaps.cameraPosition(this)


public fun GoogleMap.moveAndZoom(latLng: LatLng, zoom: Float) {
  this.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom))
}

