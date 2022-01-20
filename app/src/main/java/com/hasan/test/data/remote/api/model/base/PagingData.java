package com.hasan.test.data.remote.api.model.base;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PagingData<T> {
    private final List<T> _allData = new ArrayList<>();
    private final MutableLiveData<List<T>> allData = new MutableLiveData<>();
    private final MutableLiveData<List<T>> lastPage = new MutableLiveData<>();
    private boolean isRefreshing = false;
    private int currentPageNumber = 1, totalPages = 1;

    public void putPage(Page<T> page) {
        if (isRefreshing)
            _allData.clear();
        currentPageNumber = page.getPageNumber() + 1;
        totalPages = page.getTotalPages();
        _allData.addAll(page.getData());
        allData.setValue(_allData);
        lastPage.setValue(page.getData());
        isRefreshing = false;
    }

    public int getNextPageNumber() {
        return isRefreshing ? 1 : currentPageNumber;
    }

    public int getCurrentPageNumber() {
        return currentPageNumber;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public boolean canLoadMore() {
        return (currentPageNumber <= totalPages) || isRefreshing;
    }

    public boolean isRefreshing() {
        return isRefreshing;
    }

    public void refresh() {
        isRefreshing = true;
    }

    public void stopRefresh() {
        isRefreshing = false;
    }

    public LiveData<List<T>> getAllData() {
        return allData;
    }


    public static class Page<T> {
        @SerializedName("items")
        private List<T> data;
        @SerializedName("page")
        private int pageNumber;
        @SerializedName("total_pages")
        private int totalPages;
        @SerializedName("total")
        private int total;

        public Page(List<T> data, int pageNumber, int totalPages) {
            this.data = data;
            this.pageNumber = pageNumber;
            this.totalPages = totalPages;
        }

        public int getPageNumber() {
            return pageNumber;
        }

        public void setPageNumber(int pageNumber) {
            this.pageNumber = pageNumber;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public List<T> getData() {
            return data;
        }

        public void setData(List<T> data) {
            this.data = data;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }
    }

}
