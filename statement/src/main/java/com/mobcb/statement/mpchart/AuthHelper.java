package com.mobcb.statement.mpchart;

import rx.Observable;

/**
 * Created by Sanguine on 2018/3/26.
 */

public class AuthHelper {
    public static <T> Observable<T> auth(final Class<T> clazz) {
        return Observable.just(ApiFactory.create(clazz));
    }
}
