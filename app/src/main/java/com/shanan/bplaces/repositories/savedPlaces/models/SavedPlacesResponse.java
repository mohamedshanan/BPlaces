package com.shanan.bplaces.repositories.savedPlaces.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Shanan on 23/09/2017.
 */

public class SavedPlacesResponse {

    @SerializedName("content")
    @Expose
    private List<Place> content = null;
    @SerializedName("last")
    @Expose
    private Boolean last;
    @SerializedName("totalPages")
    @Expose
    private Integer totalPages;
    @SerializedName("totalElements")
    @Expose
    private Integer totalElements;
    @SerializedName("sort")
    @Expose
    private Object sort;
    @SerializedName("first")
    @Expose
    private Boolean first;
    @SerializedName("numberOfElements")
    @Expose
    private Integer numberOfElements;
    @SerializedName("size")
    @Expose
    private Integer size;
    @SerializedName("number")
    @Expose
    private Integer number;

    public List<Place> getPlaces() {
        return content;
    }

    public void setPlaces(List<Place> content) {
        this.content = content;
    }

    public Boolean isLast() {
        return last;
    }

    public void setLast(Boolean last) {
        this.last = last;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
    }

    public Object getSort() {
        return sort;
    }

    public void setSort(Object sort) {
        this.sort = sort;
    }

    public Boolean isFirst() {
        return first;
    }

    public void setFirst(Boolean first) {
        this.first = first;
    }

    public Integer getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(Integer numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

}
