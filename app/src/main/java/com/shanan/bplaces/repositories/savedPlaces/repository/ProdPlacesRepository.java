package com.shanan.bplaces.repositories.savedPlaces.repository;

import android.util.Log;

import com.shanan.bplaces.rest.ApiEndPointInterface;
import com.shanan.bplaces.rest.ServiceGenerator;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Shanan on 23/09/2017.
 */

public class ProdPlacesRepository implements PlacesRepository {

    private static final String TAG = ProdPlacesRepository.class.getSimpleName();
    private boolean isLoading = false;

    public ProdPlacesRepository() {
    }

    @Override
    public void getSavedPlaces(int page, int perPage, long delay, OnPlacesResponse onPlacesResponse) {

        if (isLoading) {
            Log.d(TAG, "Loading.. please wait.");
            // Reject the order.
            return;
        }

        isLoading = true;

        ApiEndPointInterface apiEndPointInterface = ServiceGenerator.getLocalPlacesEndPointInterface();

        apiEndPointInterface.getSavedPlaces(page, perPage, delay)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe((savedPlaces, throwable) -> {
                    isLoading = false;

                    if (throwable == null) {
                        Log.d(TAG, "success");
                        onPlacesResponse.onSuccess(savedPlaces.getPlaces(), savedPlaces.isLast());

                    } else {
                        Log.d(TAG, "Failure");
                        onPlacesResponse.onFailure(throwable.getLocalizedMessage());
                    }
                });
    }

    public boolean isLoading() {
        return isLoading;
    }
}
