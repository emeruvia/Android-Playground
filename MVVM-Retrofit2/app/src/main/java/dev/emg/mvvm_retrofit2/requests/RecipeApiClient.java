package dev.emg.mvvm_retrofit2.requests;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import dev.emg.mvvm_retrofit2.AppExecutors;
import dev.emg.mvvm_retrofit2.model.Recipe;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static dev.emg.mvvm_retrofit2.util.Constants.NETWORK_TIMEOUT;

/**
 * Created by emeruvia on 5/6/2019.
 */
public class RecipeApiClient {

  private static RecipeApiClient instance;
  private MutableLiveData<List<Recipe>> mRecipes;

  public static RecipeApiClient getInstance() {
    if (instance == null) {
      instance = new RecipeApiClient();
    }
    return instance;
  }

  private RecipeApiClient() {
    mRecipes = new MutableLiveData<>();
  }

  public LiveData<List<Recipe>> getRecipes() {
    return mRecipes;
  }

  public void searchRecipesApi() {
    final Future handler = AppExecutors.getInstance().networkIO().submit(new Runnable() {
      @Override public void run() {
        // retrieve data from REST API
        //mRecipes.postValue();
      }
    });

    AppExecutors.getInstance().networkIO().schedule(new Runnable() {
      @Override public void run() {
        // let the user know its timed out
        // interrupts background thread from making a request if the timeout occurs
        handler.cancel(true);
      }
    }, NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);
  }
}
