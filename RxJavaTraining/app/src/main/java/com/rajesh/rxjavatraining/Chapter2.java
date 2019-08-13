package com.rajesh.rxjavatraining;

import io.reactivex.Observable;

/**
 * Created by Rajesh on 14/08/2019
 **/
public class Chapter2 {

    public static void main(String[] args) {

        Observable<String> source = Observable.create( emitter -> {
            emitter.onNext("One");
            emitter.onNext("Two");
            //emitter.onError(new RuntimeException("Test"));
            emitter.onNext("Three");
            emitter.onComplete();
        });

        source.subscribe(s -> System.out.println(s),Throwable::printStackTrace);
    }
}
