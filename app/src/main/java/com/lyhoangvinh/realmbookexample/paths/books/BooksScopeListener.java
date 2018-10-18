package com.lyhoangvinh.realmbookexample.paths.books;

import android.support.v4.app.Fragment;

import com.lyhoangvinh.realmbookexample.application.RealmManager;


public class BooksScopeListener extends Fragment {
    BooksPresenter booksPresenter;

    public BooksScopeListener() {
        setRetainInstance(true);
        RealmManager.incrementCount();
        booksPresenter = new BooksPresenter();
    }

    @Override
    public void onDestroy() {
        RealmManager.decrementCount();
        super.onDestroy();
    }

    public BooksPresenter getPresenter() {
        return booksPresenter;
    }
}
