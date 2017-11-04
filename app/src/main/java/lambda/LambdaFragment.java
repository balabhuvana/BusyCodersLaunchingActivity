package lambda;

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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bala.test.com.busycoders_launchingactivity.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LambdaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LambdaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LambdaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String TAG = LambdaFragment.class.getSimpleName();

    OnHeadlineSelectedListener mCallback;
    StateChangeListener stateChangeListener;


    public LambdaFragment() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LambdaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LambdaFragment newInstance(String param1, String param2) {
        LambdaFragment fragment = new LambdaFragment();
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
        return inflater.inflate(R.layout.fragment_lambda, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnLambda = (Button) view.findViewById(R.id.btnLambda);
        /*btnLambda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lambdaWithThread();
            }
        });*/
        btnLambda.setOnClickListener(view1 -> {
            lambdaWithEventChange();
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallback = (OnHeadlineSelectedListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void firstExample() {
        //mCallback.onArticleSelected(123);
        //mStateChangeListener.onStateChange(12, 24);
        // add(10, 300);

    }

    private void exampleInterfaceOne() {

        MyCustomObject myCustomObject = new MyCustomObject();
        myCustomObject.setCustomObjectListener(new MyCustomObject.MyCustomObjectListener() {
            @Override
            public void onObjectReady(String title) {
                Toast.makeText(getContext(), "" + title, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDataLoaded(String data) {
                Toast.makeText(getContext(), "" + data, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void exampleInterfaceTwo() {


        MyCustomObject.MyCustomObjectListener myCustomObjectListener = new MyCustomObject.MyCustomObjectListener() {
            @Override
            public void onObjectReady(String title) {
                Toast.makeText(getContext(), "" + title, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDataLoaded(String data) {
                Toast.makeText(getContext(), "" + data, Toast.LENGTH_SHORT).show();
            }
        };
        MyCustomObject myCustomObject = new MyCustomObject(myCustomObjectListener);
    }

    private void exampleInterfaceWithLambda() {


        MyCustomObjectForLambda myCustomObjectForLambda = new MyCustomObjectForLambda();

        myCustomObjectForLambda.setCustomObjectListener(
                () -> Log.d(TAG, "State Changed")
        );
    }

    public interface OnHeadlineSelectedListener {
        public void onArticleSelected(int position);
    }

    private void exampleLambdaDrawing() {
        /*DrawingInterface drawingInterface = new DrawingInterface() {
            @Override
            public void draw(String str) {
                Log.d(TAG, "" + str);
            }
        };
        drawingInterface.draw("Hello World");*/

        DrawingInterface drawingInterface = () -> Log.d(TAG, "Hello World With Lambda");
        drawingInterface.draw();
    }

    private void exampleLambdaSayable() {

     /*   Sayable sayable = new Sayable() {
            @Override
            public String say() {
                return "I have nothing to say.";
            }
        };*/

        Sayable sayable = () -> {
            return "I have nothing to say With Lambda .";
        };

        Log.d(TAG, "" + sayable.say());
    }

    private void exampleLambdaSayableWithSingleParams() {

        /*SayableWithSigleParams sayableWithSigleParams = new SayableWithSigleParams() {
            @Override
            public void say(String name) {
                Log.d(TAG, "Without Lambda" + name.toUpperCase());
            }
        };*/

        SayableWithSigleParams sayableWithSigleParams = (name) -> {
            Log.d(TAG, "Without Lambda - " + name.toUpperCase());
        };

        sayableWithSigleParams.say("arun kumar with lamda");
    }

    private void exampleLambdaAddableWithMultiParams() {

       /* Addable addable = new Addable() {
            @Override
            public int add(int a, int b) {
                return a + b;
            }
        };*/

        Addable addable = (a, b) -> {
            return a + b;
        };

        Log.d(TAG, "With Lambda" + addable.add(45, 65));
        Log.d(TAG, "With Lambda" + addable.add(245, 65));
        Log.d(TAG, "With Lambda" + addable.add(645, 685));
    }

    private void lambdaForEachExpressionExample() {
        List<String> list = new ArrayList<String>();
        list.add("ankit");
        list.add("mayank");
        list.add("irfan");
        list.add("jai");

        /*for (int i = 0; i < list.size(); i++) {
            Log.d(TAG, "Without Lambda - " + list.get(i));
        }*/

        //list.forEach((n) -> Log.d(TAG, ""));
    }

    private void lambdaWithMultiStatement() {
        Sayable sayable = () -> {
            String firstName = "arun ";
            String lastName = "kumar";
            return firstName.toUpperCase() + lastName.toUpperCase();
        };
        Log.d(TAG, "" + sayable.say());
    }

    private void lambdaWithThread() {
        /*Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "Hello Thread Without Lambda");
            }
        };*/
        Runnable runnable = () -> {
            Log.d(TAG, "Hello Thread With Lambda");
        };
        Thread thread = new Thread(runnable);
        thread.start();

    }

    private void lambdaWithComparator() {
        List<Product> list = new ArrayList<Product>();
        list.add(new Product(1, "Samsung A5", 17000f));
        list.add(new Product(3, "Iphone 6S", 65000f));
        list.add(new Product(2, "Sony Xperia", 25000f));
        list.add(new Product(4, "Nokia Lumia", 15000f));
        list.add(new Product(5, "Redmi4 ", 26000f));
        list.add(new Product(6, "Lenevo Vibe", 19000f));

        // using lambda to filter data
        //Stream<Product> filtered_data = list.stream().filter(p -> p.price > 20000);
    }

    private void lambdaWithEventChange() {
       /* EventChangeListener eventChangeListener = new EventChangeListener() {
            @Override
            public void onEventChange() {
                Log.d(TAG, "Hello Event Change Without Lambda");
            }
        };*/

        EventChangeListener eventChangeListener = () -> {
            Log.d(TAG, "Hello Event Change With Lambda");
        };
        eventChangeListener.onEventChange();
    }

    private void lambdaWithGreeting() {
        GreetingService greetingService = (message) -> {
            Log.d(TAG, "Hello Event Change With Lambda");
        };
    }


}
