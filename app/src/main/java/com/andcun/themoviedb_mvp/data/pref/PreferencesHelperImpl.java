package com.andcun.themoviedb_mvp.data.pref;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;


/**
 * Created by cuneytcarikci on 23/05/2017.
 */

public class PreferencesHelperImpl<T> implements PreferencesHelper<T> {

    SharedPreferences sharedPreferences;
    Gson gson;

    @Inject
    public PreferencesHelperImpl(SharedPreferences sharedPreferences, Gson gson) {
        this.sharedPreferences = sharedPreferences;
        this.gson = gson;
    }

    @Override
    public Observable<T> save(final String key, final T value) {

        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<T> subscriber) throws Exception {
                sharedPreferences.edit().putString(key, gson.toJson(value)).apply();
                subscriber.onNext(value);
            }
        });
    }

    @Override
    public Observable<T> get(final String key, final Class<T> generic) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<T> subscriber) throws Exception {
                String json = sharedPreferences.getString(key, "");
                T myClass = gson.fromJson(json, generic);
                subscriber.onNext(myClass);
            }
        });

    }

    @Override
    public Observable<Boolean> clear() {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Boolean> subscriber) throws Exception {
                sharedPreferences.edit().clear().apply();
                subscriber.onNext(true);
            }
        });
    }
}
