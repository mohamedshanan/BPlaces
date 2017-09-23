package com.shanan.bplaces.ui.places;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.shanan.bplaces.R;
import com.shanan.bplaces.repositories.Models.Place;
import com.shanan.bplaces.repositories.Repository.OnPlacesResponse;
import com.shanan.bplaces.repositories.Repository.PlacesRepository;
import com.shanan.bplaces.repositories.Repository.ProdPlacesRepository;
import com.shanan.bplaces.ui.base.BaseActivity;
import com.shanan.bplaces.utils.Utilities;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Shanan on 23/09/2017.
 */

public class PlacesActivity extends BaseActivity implements PlacesContract.View {

    @BindView(R.id.tv)
    TextView DummyTv;

    private static final String TAG = PlacesActivity.class.getSimpleName();
    PlacesContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);
        mPresenter = new PlacesPresenter(this);
        mPresenter.getSavedPlaces();

    }

    @OnClick(R.id.tv) void onClick(){
        mPresenter.getSavedPlaces();
    }

    @Override
    protected void showNoConnection() {
        Toast.makeText(this, "No Internet Connection !!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showPlaces(boolean clearing, List<Place> places) {
        if (clearing){
            Log.d(TAG, "Setting " + places.size()+ " Places");
        } else {
            Log.d(TAG, "Adding " + places.size()+ " Places");
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
}