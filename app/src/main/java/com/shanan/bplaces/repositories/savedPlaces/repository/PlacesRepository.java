package com.shanan.bplaces.repositories.savedPlaces.repository;

/**
 * Created by Shanan on 23/09/2017.
 */

public interface PlacesRepository {
    void getSavedPlaces(int page, int perPage, long delay, OnPlacesResponse onPlacesResponse);
    boolean isLoading();
}
