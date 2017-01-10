package events;

/**
 * Created by sai on 1/9/17.
 */

public class ToggleProgressDisplayEvent {

    public final boolean showProgress;

    public ToggleProgressDisplayEvent(boolean showProgress) {
        this.showProgress = showProgress;
    }
}
