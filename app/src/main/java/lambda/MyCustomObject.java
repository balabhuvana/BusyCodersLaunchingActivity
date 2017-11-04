package lambda;

/**
 * Created by bala on 28/10/17.
 */

public class MyCustomObject {

    private MyCustomObjectListener listener;

    // Constructor where listener events are ignored
    public MyCustomObject() {
        // set null or default listener or accept as argument to constructor
        this.listener = null;
    }

    // Constructor where listener events are ignored
    public MyCustomObject(MyCustomObjectListener omyCustomObjectListener) {
        // set null or default listener or accept as argument to constructor
        this.listener = omyCustomObjectListener;
        listener.onDataLoaded("Hello Karthik");
        listener.onDataLoaded("Hello Guna");
    }

    // Assign the listener implementing events interface that will receive the events
    public void setCustomObjectListener(MyCustomObjectListener listener) {
        this.listener = listener;
        loadDataAsync();
    }

    public void loadDataAsync() {
        listener.onDataLoaded("Hello Chitra");
        listener.onDataLoaded("Hello Arun");
    }

    public interface MyCustomObjectListener {
        // These methods are the different events and
        // need to pass relevant arguments related to the event triggered
        public void onObjectReady(String title);

        // or when data has been loaded
        public void onDataLoaded(String data);
    }
}
