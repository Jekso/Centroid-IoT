package Apis;

import java.util.concurrent.ExecutionException;

/**
 * Created by ahmed ezz on 3/6/2016.
 */
public interface OnTaskComplete {
    public void onComplete() throws ExecutionException, InterruptedException;
}
