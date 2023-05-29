package com.example.coffee.utils;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subjects.BehaviorSubject;

public class Debounce {
    CompositeDisposable disposable = new CompositeDisposable();
    BehaviorSubject<String> _textInput = BehaviorSubject.create();
    Flowable<String> textInput = _textInput.toFlowable(BackpressureStrategy.LATEST);
}
