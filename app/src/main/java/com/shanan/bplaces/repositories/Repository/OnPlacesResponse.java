package com.shanan.bplaces.repositories.Repository;

import com.shanan.bplaces.repositories.Models.Place;

import java.util.List;

/**
 * Created by Shanan on 23/09/2017.
 */

public interface OnPlacesResponse {
    void onSuccess(List<Place> savedPlaces, Boolean isLast);
    void onFailure(String errorMessage);
}
