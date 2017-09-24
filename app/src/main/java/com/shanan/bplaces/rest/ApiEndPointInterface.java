package com.shanan.bplaces.rest;

import com.shanan.bplaces.repositories.savedPlaces.models.SavedPlacesResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Shanan on 23/09/2017.
 */

public interface ApiEndPointInterface {

    @GET(ApiConstants.GET_PLACES)
    Single<SavedPlacesResponse> getSavedPlaces(@Query(ApiConstants.PARAM_PAGE) int page, @Query(ApiConstants.PARAM_PER_PAGE) int perPage, @Query(ApiConstants.PARAM_DELAY) long delay);
}
