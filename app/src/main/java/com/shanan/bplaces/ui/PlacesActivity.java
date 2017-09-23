package com.shanan.bplaces.ui;

import android.os.Bundle;
import android.widget.Toast;

import com.shanan.bplaces.R;
import com.shanan.bplaces.ui.base.BaseActivity;

/**
 * Created by Shanan on 23/09/2017.
 */

public class PlacesActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);
    }

    @Override
    protected void showNoConnection(boolean isConnected) {
        Toast.makeText(this, "Internet Connection? " + isConnected, Toast.LENGTH_SHORT).show();
    }
}
