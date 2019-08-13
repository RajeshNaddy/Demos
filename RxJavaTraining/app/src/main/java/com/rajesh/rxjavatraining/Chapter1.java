package com.rajesh.rxjavatraining;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

/**
 * Created by Rajesh on 14/08/2019
 **/
public class Chapter1 {

    public static void main(String[] args) {
        Observable<String> myStrings = Observable.just("One","Two","Three");
        myStrings.subscribe( s -> System.out.print(s));


        Observable<String> mystring = Observable.just("One","Two","Three","Four");
        mystring.map(s -> s.length()).subscribe(s -> System.out.print(s));



        Observable<Long> timedelay = Observable.interval(1, TimeUnit.SECONDS);
        timedelay.subscribe(s -> System.out.println(s));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
