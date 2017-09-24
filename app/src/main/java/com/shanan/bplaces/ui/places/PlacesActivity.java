package com.shanan.bplaces.ui.places;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.Places;
import com.hwangjr.rxbus.RxBus;
import com.shanan.bplaces.R;
import com.shanan.bplaces.repositories.savedPlaces.models.Place;
import com.shanan.bplaces.ui.base.BaseActivity;
import com.shanan.bplaces.utils.Utilities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shanan on 23/09/2017.
 */

public class PlacesActivity extends BaseActivity implements PlacesContract.View, GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {

//    @BindView(R.id.tv)
//    TextView DummyTv;

    private static final String TAG = PlacesActivity.class.getSimpleName();
    private PlacesContract.Presenter mPresenter;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);
        initGoogleApiClient();
        mPresenter = new PlacesPresenter(this, mGoogleApiClient);
        mPresenter.getSavedPlaces();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_address_autocomplete, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        item.expandActionView(); // get focus
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mPresenter.getGooglePlaces(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mPresenter.getGooglePlaces(newText);
                return true;
            }
        });

        return true;
    }

    private void initGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Places.GEO_DATA_API)
                .build();
        mGoogleApiClient.connect();
    }

//    @OnClick(R.id.tv) void onClick(){
//        mPresenter.getSavedPlaces();
//    }

    @Override
    protected void showNoConnection() {
        Toast.makeText(this, "No Internet Connection !!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showPlaces(boolean clearing, List<Place> places) {
        if (clearing) {
            Log.d(TAG, "Setting " + places.size() + " Places");
        } else {
            Log.d(TAG, "Adding " + places.size() + " Places");
        }
    }

    @Override
    public void showLoader() {
        Log.d(TAG, "Loading.....");
    }

    @Override
    public void hideLoader() {
        Log.d(TAG, "Loading finished.");
    }

    @Override
    public void showLoadMoreProgress() {
        Log.d(TAG, "Loading more places...");
    }

    @Override
    public void dismissLoadMoreProgress() {
        Log.d(TAG, "Loading more places finished.");
    }

    @Override
    public void clearPlaces() {

    }

    @Override
    public void showSnackBar(int resId) {

    }

    @Override
    public void showTryAgainLayout(String message) {
        Log.d(TAG, "showTryAgainLayout." + message);
    }

    @Override
    public void addToPlaces(Place place) {

    }

    @Override
    public boolean isConnected() {
        return Utilities.getNetworkState(this);
    }

    @Override
    public void showTryAgainLayout(int resId) {
        Log.d(TAG, "showTryAgainLayout." + getString(resId));
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}