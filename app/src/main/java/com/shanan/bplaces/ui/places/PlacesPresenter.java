package com.shanan.bplaces.ui.places;

import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.AutocompletePredictionBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.hwangjr.rxbus.RxBus;
import com.shanan.bplaces.R;
import com.shanan.bplaces.repositories.savedPlaces.models.Place;
import com.shanan.bplaces.repositories.savedPlaces.repository.OnPlacesResponse;
import com.shanan.bplaces.repositories.savedPlaces.repository.PlacesRepository;
import com.shanan.bplaces.repositories.savedPlaces.repository.ProdPlacesRepository;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Shanan on 23/09/2017.
 */

public class PlacesPresenter implements PlacesContract.Presenter {

    private static final String TAG = PlacesPresenter.class.getSimpleName();
    private final static int FIRST_PAGE = 0;
    private final static int PER_PAGE = 10;

    private final PlacesContract.View view;
    private final PlacesRepository savedPlacesRepository = new ProdPlacesRepository();
    private final GoogleApiClient mGoogleApiClient;
    private Observable<List<AutocompletePrediction>> listObservable;

    private PublishSubject<String> mSearchResultsSubject;
    private Subscription mTextWatchSubscription;

    private int pageIndex = 0;
    private boolean isNoMoreData;

    public PlacesPresenter(PlacesContract.View view, GoogleApiClient googleApiClient) {
        mGoogleApiClient = googleApiClient;
        this.view = view;
    }

    @Override
    public void getSavedPlaces() {

        if (!view.isConnected()) {
            return;
        }

        if (!isNoMoreData) {
            if (pageIndex == FIRST_PAGE) {
                view.showLoader();
            }
            getPlaces();
        } else {
            Log.i(TAG, "No more places");
        }
    }

    public void getPlaces() {

        savedPlacesRepository.getSavedPlaces(pageIndex, PER_PAGE, 0, new OnPlacesResponse() {
            @Override
            public void onSuccess(List<Place> savedPlaces, Boolean isLastPage) {
                view.hideLoader();

                if (savedPlaces.isEmpty()) {
                    isNoMoreData = true;
                    if (isLastPage && pageIndex == 0) {
                        view.showTryAgainLayout(R.string.no_data);
                    }
                    return;
                }
                if (pageIndex == FIRST_PAGE) {
                    view.showPlaces(true, savedPlaces);
                } else {
                    view.showPlaces(false, savedPlaces);
                }

                // prepare pageIndex for the second page
                pageIndex++;
            }

            @Override
            public void onFailure(String errorMessage) {
                view.hideLoader();
                view.showTryAgainLayout(errorMessage);
            }
        });


    }

    @Override
    public void getGooglePlaces(String keyWord) {

        if (!mGoogleApiClient.isConnected()) {
            mGoogleApiClient.connect();
        }

        LatLngBounds bounds = new LatLngBounds(
                new LatLng(-85, 180),    // bottom right corner
                new LatLng(85, -180)    // top left corner of map
        );

        AutocompleteFilter filter = new AutocompleteFilter.Builder()
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_NONE)
                .build();

        PendingResult<AutocompletePredictionBuffer> results = Places.GeoDataApi
                .getAutocompletePredictions(mGoogleApiClient, keyWord, bounds, filter);

        results.setResultCallback(autocompletePredictions -> {

            Status status = autocompletePredictions.getStatus();

            if (status.isSuccess() && autocompletePredictions != null) {

                Iterator<AutocompletePrediction> iterator = autocompletePredictions.iterator();

                ArrayList<AutocompletePrediction> predictions = new ArrayList<>();

                while (iterator.hasNext()) {
                    AutocompletePrediction prediction = iterator.next();
                    Log.d(TAG, prediction.getPlaceId());
                }


            } else {
                Log.d(TAG, status.getStatusMessage());
            }

            autocompletePredictions.release();
            mGoogleApiClient.disconnect();

        });
    }
}
