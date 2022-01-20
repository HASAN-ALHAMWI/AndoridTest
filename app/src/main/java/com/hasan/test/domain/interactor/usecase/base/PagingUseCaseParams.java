package com.hasan.test.domain.interactor.usecase.base;

public class PagingUseCaseParams {

    private int pageNumber;

    private PagingUseCaseParams(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public static PagingUseCaseParams create(int pageNumber){
        return new PagingUseCaseParams(pageNumber);
    }

    public int getPageNumber() {
        return pageNumber;
    }
}
