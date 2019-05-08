package dev.emg.mvvm_retrofit2.requests;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import dev.emg.mvvm_retrofit2.AppExecutors;
import dev.emg.mvvm_retrofit2.model.Recipe;
import dev.emg.mvvm_retrofit2.requests.responses.RecipeSearchResponse;
import dev.emg.mvvm_retrofit2.util.Constants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import retrofit2.Call;
import retrofit2.Response;

import static dev.emg.mvvm_retrofit2.util.Constants.NETWORK_TIMEOUT;

/**
 * Created by emeruvia on 5/6/2019.
 */
public class RecipeApiClient {

  private static final String TAG = "RecipeApiClient";

  private static RecipeApiClient instance;
  private MutableLiveData<List<Recipe>> mRecipes;
  private RetrieveRecipesRunnable mRetrieveRecipesRunnable;

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

  public void searchRecipesApi(String query, int pageNumber) {
    // if a query has already been made, then set it equal to null to avoid appending old queries.
    if (mRetrieveRecipesRunnable != null) {
      mRetrieveRecipesRunnable = null;
    }
    mRetrieveRecipesRunnable = new RetrieveRecipesRunnable(query, pageNumber);
    final Future handler = AppExecutors.getInstance().networkIO().submit(mRetrieveRecipesRunnable);

    AppExecutors.getInstance().networkIO().schedule(new Runnable() {
      @Override public void run() {
        // let the user know its timed out
        // interrupts background thread from making a request if the timeout occurs
        handler.cancel(true);
      }
    }, NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);
  }

  private class RetrieveRecipesRunnable implements Runnable {

    private String query;
    private int pageNumber;
    boolean cancelRequest;

    public RetrieveRecipesRunnable(String query, int pageNumber) {
      this.query = query;
      this.pageNumber = pageNumber;
      cancelRequest = false;
    }

    @Override public void run() {
      try {// This needs to be executed on background thread because it's executing a network request
        Response response = getRecipes(query, pageNumber).execute();
        if (cancelRequest) {
          return;
        }
        // Response code 200 represents a successful request. If it fails then it will let the
        // user know there is an error by setting the list value to null.
        if (response.code() == 200) {
          List<Recipe> list =
              new ArrayList<>(((RecipeSearchResponse) response.body()).getRecipes());
          if (pageNumber == 1) {
            mRecipes.postValue(list);
          } else {
            List<Recipe> currentRecipes = mRecipes.getValue();
            currentRecipes.addAll(list);
            mRecipes.postValue(list);
          }
        } else {
          String error = response.errorBody().string();
          Log.e(TAG, "run: " + error);
          mRecipes.postValue(null);
        }
      } catch (IOException e) {
        e.printStackTrace();
        mRecipes.postValue(null);
      }
    }

    private Call<RecipeSearchResponse> getRecipes(String query, int pageNumber) {
      return ServiceGenerator.getRecipeApi().searchRecipe(
          Constants.BASE_URL,
          query,
          String.valueOf(pageNumber));
    }

    private void cancelRequest() {
      Log.d(TAG, "cancelRequest: caneling the search request.");
      cancelRequest = true;
    }
  }
}
