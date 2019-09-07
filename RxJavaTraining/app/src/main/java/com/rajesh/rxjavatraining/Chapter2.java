package com.rajesh.rxjavatraining;

import io.reactivex.*;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.observers.BlockingBaseObserver;
import io.reactivex.internal.observers.FutureObserver;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.observers.ResourceObserver;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by Rajesh on 14/08/2019
 **/
public class Chapter2 {

    static int start = 1;
    static int count = 5;

    public static void main(String[] args) throws InterruptedException {

        Observable<String> source = Observable.create(emitter -> {
            emitter.onNext("One");
            emitter.onNext("Two");
            //emitter.onError(new RuntimeException("Test"));
            emitter.onNext("Three");
            emitter.onComplete();
        });

        source.subscribe(s -> System.out.println(s), Throwable::printStackTrace);
        System.out.println("##################");

        /*Example 2*/
        Observable<String> source1 = Observable.create(emitter -> {
            emitter.onNext("Rajesh");
            emitter.onNext("Kumar");
            emitter.onNext("Selvaraj");
            emitter.onComplete();
        });

        Observable<Integer> lengths = source1.map(String::length);

        Observable<Integer> filtered = lengths.filter(s -> s > 5);
        filtered.subscribe(s -> System.out.println(s));
        System.out.println("##################");

        /*Example 3*/
        Observable<String> source2 = Observable.create(emitter -> {
            emitter.onNext("Rajesh");
            emitter.onNext("Kumar");
            emitter.onNext("Selvaraj");
        });
        source2.map(String::length).filter(s -> s > 5).subscribe(s -> System.out.println(s));
        System.out.println("##################");

        /*Example 4*/
        Observable<String> source3 = Observable.just("Rajesh", "Kumar", "Selvaraj");
        source3.map(String::length).filter(s -> s > 4).
                subscribe(s -> System.out.println(s));
        System.out.println("##################");

        /*Example 4*/
        List<String> list = Arrays.asList("Rajesh", "Kumar", "Selvaraj");
        Observable<String> source4 = Observable.fromIterable(list);
        source4.map(String::length).filter(s -> s > 5).subscribe(s -> System.out.println(s));
        System.out.println("##################");

        /*Example 5*/
        Observable<String> source5 = Observable.just("Rajesh", "Kumar", "Selvaraj","Name777");
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("Subscribed");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(integer+ "");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        };
        source5.map(String::length).filter(s-> s >5).subscribe(observer);
        System.out.println("##################");

        /*Example 6*/
        Observable<String> source6 = Observable.just("Rajesh", "Kumar", "Selvaraj","Name777");
        source6.filter(s-> s.startsWith("Se")).subscribe(i -> System.out.println(i),Throwable::printStackTrace,
                ()-> System.out.println("Completed"));
        System.out.println("##################");

        /*Example 7*/
        System.out.println("Connectable Observable ");
        Observable<String> source7 = Observable.just("Rajesh", "Kumar", "Selvaraj","Name777").publish();
        source7.subscribe(s-> System.out.println(s));
        source7.map(String::length).subscribe(s-> System.out.println(s));
        ((ConnectableObservable<String>) source7).connect(); //fire!!
        System.out.println("##################");

        /*Example 8*/
        System.out.println("Observable.range");
        Observable.range(5,10).subscribe(s-> System.out.println(s));
        Observable.rangeLong(99,5).subscribe(s-> System.out.println(s));
        System.out.println("##################");

        /*Example 9*/
        System.out.println("Observable.interval");
        Observable.interval(1, TimeUnit.SECONDS).subscribe(s-> System.out.println("interval "+s));
        Thread.sleep(5000);

        /*Example 10*/
        System.out.println("Observable.interval");
        Observable<Long> source8 = Observable.interval(1, TimeUnit.SECONDS);
        source8.subscribe(s-> System.out.println("interval "+s));
        Thread.sleep(5000);
        source8.subscribe(s-> System.out.println("intervalafter "+s));
        Thread.sleep(5000);
        /*Example 11*/
        ConnectableObservable<Long> source9 = Observable.interval(1, TimeUnit.SECONDS).publish();
        source9.subscribe(s-> System.out.println("interval "+s));
        source9.connect();
        Thread.sleep(5000);
        source9.subscribe(s-> System.out.println("intervalafter "+s));
        Thread.sleep(5000);
        System.out.println("##################");

        /*Example 12*/
        System.out.println("Observable.empty");
        Observable<String> observable = Observable.empty();
        observable.subscribe(System.out::println,Throwable::printStackTrace,()-> System.out.println("Done"));
        System.out.println("##################");

        /*Example 13*/
        System.out.println("Observable.error");
        Observable.error(new RuntimeException("Error on the way"))
                .subscribe(System.out::println,Throwable::printStackTrace,
                        ()-> System.out.println("Done"));
        System.out.println("##################");
        /*Example 14*/
        System.out.println("Observable.defer");
        Observable<Integer> source11 = Observable.range(start,count);
        source11.subscribe(s -> System.out.println(s));
        count = 10;
        source11.subscribe(s -> System.out.println(s));

        /*Example 15*/
        System.out.println("Observable.defer1");
        Observable<Integer> source12 = Observable.defer(() -> Observable.range(start,count));
        source12.subscribe(s -> System.out.println(s));
        count = 10;
        source12.subscribe(s -> System.out.println(s));
        System.out.println("##################");

        /*Example 15*/
        System.out.println("Observable.fromCallable");
        /*Observable.just(1/0).subscribe(System.out::println,Throwable::printStackTrace,
                ()-> System.out.println("DOne"));*/

        Observable.fromCallable(() -> 1/0).subscribe(System.out::println,e -> System.out.println("Error" + e.getMessage()),
                ()-> System.out.println("DOne"));
        System.out.println("##################");

        /*Example 16*/
        System.out.println("Single");
        SingleObserver observer1 = new SingleObserver() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onSuccess(Object o) {
                System.out.println("onSuccess "+ o.toString());
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError");
            }
        };
        Single.just(1).subscribe(observer1);

        Single.just("Hello").map(String::length).subscribe(System.out::println);

        Observable<String> source16 = Observable.just("One","two","three");
        source16.first("Nil").subscribe(s-> System.out.println(s));

        Observable<String> source15 = Observable.empty();
        source15.first("Nil").subscribe(s-> System.out.println(s));
        System.out.println("##################");

        /*Example 17*/
        System.out.println("Maybe");
        Maybe<String> source17 = Maybe.just("One");
        MaybeObserver observer2 = new MaybeObserver() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onSuccess(Object o) {
                System.out.println("onSuccess "+ o.toString());
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        };
        source17.subscribe(observer2);

        source16.firstElement().subscribe(s-> System.out.println(s),Throwable::printStackTrace,
                () -> System.out.println("DOne"));

        source15.firstElement().subscribe(s-> System.out.println(s),Throwable::printStackTrace,
                () -> System.out.println("DOne"));
        System.out.println("##################");

        /*Example 18*/
        System.out.println("Completable");
        Completable.complete().subscribe(() -> System.out.println("Done"));

        Completable.fromRunnable(() -> System.out.println("Uploading")).subscribe(() -> System.out.println("Uploaded"));
        System.out.println("##################");

        /*Example 19*/
        System.out.println("Disposable");
        Observable interval  = Observable.interval(1,TimeUnit.SECONDS);
        Disposable disposable = interval.subscribe(s-> System.out.println(s));
        Thread.sleep(5000);
        disposable.dispose();
        Thread.sleep(5000);
        System.out.println("##################");

        /*Example 19*/
        System.out.println("Resource Observer");
        Observable resource = Observable.interval(1,TimeUnit.SECONDS);
        ResourceObserver<Long> observer3 = new ResourceObserver<Long>() {
            @Override
            public void onNext(Long integer) {
                System.out.println(integer+"");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        resource.subscribeWith(observer3);
        Thread.sleep(5000);
        System.out.println("##################");


        /*Example 19*/
        System.out.println("Composite Disposable");
        CompositeDisposable cd = new CompositeDisposable();
        Observable<Long> observable1 = Observable.interval(1,TimeUnit.SECONDS);
        cd.add(observable1.subscribe(s-> System.out.println(s)));
        Thread.sleep(3000);
        cd.add(observable1.subscribe(s-> System.out.println(s)));
        Thread.sleep(3000);
        cd.dispose();
        Thread.sleep(3000);

    }
}
