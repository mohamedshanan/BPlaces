package com.shanan.bplaces.ui.places;

import com.google.android.gms.common.api.GoogleApiClient;
import com.shanan.bplaces.repositories.savedPlaces.models.Place;

import java.util.List;

/**
 * Created by Shanan on 23/09/2017.
 */

public class PlacesContract {

    interface View {

        void showPlaces(boolean clearing, List<Place> places);

        void showLoader();

        void hideLoader();

        void showLoadMoreProgress();

        void dismissLoadMoreProgress();

        void clearPlaces();

        void showSnackBar(int resId);

        void showTryAgainLayout(String message);

        void addToPlaces(Place place);

        boolean isConnected();

        void showTryAgainLayout(int resId);
    }

    interface Presenter {

        void getSavedPlaces();

        void getGooglePlaces(String keyWord);
    }
}
