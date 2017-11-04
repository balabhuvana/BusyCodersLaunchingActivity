package learn.rxandroid;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bala.test.com.busycoders_launchingactivity.R;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public class SampleRXAndroidFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String TAG = SampleRXAndroidFragment.class.getSimpleName();
    private TextView mTextView;
    private TextView mTextViewEvent;


    public SampleRXAndroidFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SampleRXAndroidFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SampleRXAndroidFragment newInstance(String param1, String param2) {
        SampleRXAndroidFragment fragment = new SampleRXAndroidFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sample_rxandroid, container, false);
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    private void rxAndroidMayBeExampleWithoutLambda() {
        Maybe<List<String>> maybe = Maybe.create(new MaybeOnSubscribe<List<String>>() {
            @Override
            public void subscribe(MaybeEmitter<List<String>> emitter) throws Exception {
                try {
                    List<String> todos = getListItem();
                    if (todos != null && !todos.isEmpty()) {
                        emitter.onSuccess(todos);
                    } else {
                        emitter.onComplete();
                    }
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        });

        MaybeObserver<List<String>> listMaybeObserver = new MaybeObserver<List<String>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(List<String> strings) {
                for (String str : strings) {
                    Log.d(TAG, "May Be - Without Lambda - " + str);
                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "May Be - Without Lambda - onComplete");
            }
        };
        maybe.subscribe(listMaybeObserver);
    }

    private void rxAndroidMayBeExampleWithLambda() {


        Maybe<List<String>> maybe = Maybe.create(emitter -> {
            try {
                List<String> todos = getListItem();
                if (todos != null && !todos.isEmpty()) {
                    emitter.onSuccess(todos);
                } else {
                    emitter.onComplete();
                }
            } catch (Exception e) {
                emitter.onError(e);
            }
        });

        maybe.subscribe(list -> {
            for (String str : list) {
                Log.d(TAG, "May Be - WithLambda - " + str);
            }
        });
    }

    private void rxAndroidExampleOne() {
        /*Observable observable = Observable.create(new ObservableOnSubscribe<String>() {

            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                List<String> stringList = getListItem();
                for (String str : stringList) {
                    e.onNext(str);
                }
                e.onComplete();
            }
        });*/

        Observable<String> observable = Observable.create(emitter -> {
            List<String> stringList = getListItem();
            for (String str : stringList) {
                emitter.onNext(str);
            }
            emitter.onComplete();
        });

        DisposableObserver disposableObserver = new DisposableObserver<String>() {
            @Override
            public void onNext(String o) {
                Log.d(TAG, "Name : " + o);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                Log.d(TAG, " onComplete ");
            }
        };
        observable.subscribe(disposableObserver);
    }


    private List<String> getListItem() {
        List list = new ArrayList();
        list.add("Arun kumar");
        list.add("Chitra");
        list.add("Karthik");
        list.add("Chinna");
        return list;
    }

    private void rxAndroidSingleExampleWithoutLambda() {
        Single<List<String>> listSingleObservable = Single.create(new SingleOnSubscribe<List<String>>() {
            @Override
            public void subscribe(SingleEmitter<List<String>> emitter) throws Exception {
                emitter.onSuccess(getListItem());
            }
        });

        SingleObserver<List<String>> listSingleObserver = new SingleObserver<List<String>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(List<String> strings) {
                for (String s : strings) {
                    Log.d(TAG, "Single - Without Lambda - " + s);
                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }
        };

        listSingleObservable.subscribe(listSingleObserver);
    }

    private void rxAndroidSingleExampleWithLambda() {
        Single<List<String>> singleObservable = Single.create(emitter -> {
            emitter.onSuccess(getListItem());
        });

       /* new SingleObserver<List<String>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(List<String> o) {

            }

            @Override
            public void onError(Throwable e) {

            }
        };*/

        singleObservable.subscribe(list -> {
            for (String str : list) {
                Log.d(TAG, "Single - WithLambda - " + str);
            }
        });
    }

    private void rxAndroidSingleExampleWithLambdaCache() {
        Single<List<String>> singleObservable = Single.create(emitter -> {
            emitter.onSuccess(getListItem());
        });

        SingleObserver<List<String>> singleObserverOne = new SingleObserver<List<String>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(List<String> o) {
                Log.d(TAG, "Am called for first time");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }
        };

        SingleObserver<List<String>> singleObserverTwo = new SingleObserver<List<String>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(List<String> o) {
                Log.d(TAG, "Am called for second time");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }
        };

        SingleObserver<List<String>> singleObserverThird = new SingleObserver<List<String>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(List<String> o) {
                Log.d(TAG, "Am called for third time");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }
        };
/*
        singleObservable.subscribe(singleObserverOne);
        singleObservable.subscribe(singleObserverTwo);
        singleObservable.subscribe(singleObserverThird);*/

        Single<List<String>> singleObservableWithCache = singleObservable.cache();
        singleObservableWithCache.subscribe(singleObserverOne);
        singleObservableWithCache.subscribe(singleObserverOne);
        singleObservableWithCache.subscribe(singleObserverOne);
    }

    private void exampleUIUpdatesWithThreadDisposalObserver() {
        Observable<String> downloadableObservable = Observable.create(emitter -> {
            emitter.onNext("Hello World");
            emitter.onComplete();
        });

        DisposableObserver<String> disposableObserver = new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                Log.d(TAG, "" + s);
                mTextViewEvent.setText(s);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        };

        downloadableObservable.observeOn(AndroidSchedulers.mainThread());
        downloadableObservable.subscribeOn(Schedulers.io());
        downloadableObservable.subscribe(disposableObserver);

        downloadableObservable.
                subscribe(string -> {
                    mTextView.setText(string);
                });
    }

    private void exampleSingleObserverWithThread() {
        Single<String> singleObservable = Single.create(emitter -> {
            emitter.onSuccess("Hello Arun");
        });
        SingleObserver<String> singleObserver = new SingleObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "");
            }

            @Override
            public void onSuccess(String s) {
                Log.d(TAG, "" + s);
                mTextView.setText(s);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }
        };

        singleObservable.subscribe(singleObserver);

        singleObservable.observeOn(AndroidSchedulers.mainThread());
        singleObservable.subscribeOn(Schedulers.io());
        singleObservable.subscribe(string -> {
            mTextViewEvent.setText(string);
        });

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTextView = (TextView) view.findViewById(R.id.tvEvent);
        mTextViewEvent = (TextView) view.findViewById(R.id.tvEvent1);
        Button button = (Button) view.findViewById(R.id.btnRxAndroid);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exampleSingleObserverWithThread();
            }
        });
    }


}
