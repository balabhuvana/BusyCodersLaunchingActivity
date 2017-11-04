package lambda;

/**
 * Created by bala on 28/10/17.
 */

public class MyCustomObjectForLambda {

    private MyCustomObjectListenerForLambda listener;

    // Constructor where listener events are ignored
    public MyCustomObjectForLambda() {
        // set null or default listener or accept as argument to constructor
        this.listener = null;
    }


    // Assign the listener implementing events interface that will receive the events
    public void setCustomObjectListener(MyCustomObjectListenerForLambda listener) {
        this.listener = listener;
        //loadDataAsync();
    }



    public interface MyCustomObjectListenerForLambda {
        // These methods are the different events and
        // need to pass relevant arguments related to the event triggered
        public void onObjectReady();

    }
}
