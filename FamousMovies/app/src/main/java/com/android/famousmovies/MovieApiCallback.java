package com.android.famousmovies;

/**
 * Created by Hakimi on 25/2/2020.
 */
public interface MovieApiCallback<T> {
    void onResponse(T result);

    void onCancel();
}
