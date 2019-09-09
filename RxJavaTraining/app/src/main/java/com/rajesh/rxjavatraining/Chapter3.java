package com.rajesh.rxjavatraining;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

/**

 * Created by Rajesh on 07/09/2019
 * OPERATORS
 **/
public class Chapter3 {

    public static void main(String[] args) throws InterruptedException {

        /* Suppressing operators */
        System.out.println("Filter");
        Observable.just("One","two","Three","FOur").filter(s-> s.length() > 3).
                subscribe(s -> System.out.println(s));
        System.out.println("***********************************");

        System.out.println("take");
        Observable.just("One","two","Three","FOur").take(2)//.filter(s->s.startsWith("O"))
                .subscribe(s-> System.out.println(s));
        //.subscribe(s-> System.out.println(s),Throwable::printStackTrace,()-> System.out.println("Done"));

        /*Observable.interval(300, TimeUnit.MILLISECONDS).take(3)
                .subscribe(s-> System.out.println(s));
        Thread.sleep(5000);*/
        System.out.println("***********************************");

        System.out.println("skip");
        Observable.range(1,100).skip(90).subscribe(s-> System.out.println(s));
        System.out.println("***********************************");

        System.out.println("takeWhile & skipWhile");
        Observable.just("One","two","Three","FOur").takeWhile(s-> s.startsWith("O"))
                .subscribe(s-> System.out.println(s));

        Observable.range(1,100).skipWhile( s-> s < 95).subscribe(s-> System.out.println(s));
        System.out.println("***********************************");

        System.out.println("distinct");
        Observable.just("One","Two","Three","Four").distinct(String::length).subscribe(s-> System.out.println(s));
        //Observable.just("One","Two","Three","Four").map(s -> s.length()).distinct().subscribe(s-> System.out.println(s));
        System.out.println("***********************************");

        System.out.println("distinctUntilChanged");
        //Observable.just(1,1,2,2,2,3,5,2,1,5).distinctUntilChanged().subscribe(s-> System.out.println(s));
        Observable.just("One","Two","Three","Four").distinctUntilChanged(String::length).subscribe(s-> System.out.println(s));
        System.out.println("***********************************");

        System.out.println("elementAt");
        Observable.just("One","Two","Three","Four").elementAt(7).subscribe(s -> System.out.println(s),
                Throwable::printStackTrace,()-> System.out.println("Done"));
        Observable.just("One","Two","Three","Four").elementAtOrError(7).subscribe(s -> System.out.println(s),
                Throwable::printStackTrace);
        Observable.just("One").singleElement().subscribe(s -> System.out.println(s),
                Throwable::printStackTrace,()-> System.out.println("Done"));
        Observable.just("One","Two","Three","Four").firstElement().subscribe(s -> System.out.println(s),
                Throwable::printStackTrace,()-> System.out.println("Done"));
        Observable.just("One","Two","Three","Four").lastElement().subscribe(s -> System.out.println(s),
                Throwable::printStackTrace,()-> System.out.println("Done"));
    }
}
