package com.shanan.bplaces.ui.places;

import android.util.Log;

import com.shanan.bplaces.R;
import com.shanan.bplaces.repositories.Models.Place;
import com.shanan.bplaces.repositories.Repository.OnPlacesResponse;
import com.shanan.bplaces.repositories.Repository.PlacesRepository;
import com.shanan.bplaces.repositories.Repository.ProdPlacesRepository;

import java.util.List;

/**
 * Created by Shanan on 23/09/2017.
 */

public class PlacesPresenter implements PlacesContract.Presenter {

    private static final String TAG = PlacesPresenter.class.getSimpleName();
    private final static int FIRST_PAGE = 0;
    private final static int PER_PAGE = 10;

    private final PlacesContract.View view;
    private final PlacesRepository savedPlacesRepository = new ProdPlacesRepository();
    private int pageIndex = 0;
    private boolean isNoMoreData;

    public PlacesPresenter(PlacesContract.View view) {
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

                if (savedPlaces.isEmpty()){
                    isNoMoreData = true;
                    if (isLastPage && pageIndex == 0){
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
    public void getGooglePlaces() {

    }
}
