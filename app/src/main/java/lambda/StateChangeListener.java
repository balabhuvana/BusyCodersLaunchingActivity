package lambda;

/**
 * Created by bala on 28/10/17.
 */

public interface StateChangeListener {
    public void onStateChange(int oldState, int newState);
}