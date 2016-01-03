/*
 * Copyright 2015 Oti Rowland
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.rowland.movies.rest.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.rowland.movies.ApplicationController;
import com.rowland.movies.BuildConfig;
import com.rowland.movies.data.callbacks.TrailerCallBack;
import com.rowland.movies.rest.collections.TrailerCollection;
import com.rowland.movies.rest.enums.EAPITypes;

import retrofit.Call;

/**
 * Created by Oti Rowland on 12/29/2015.
 */
public class TrailerIntentService extends IntentService {

    // The page no. string identifier
    public static final String REQUEST_PAGE_NO_INT = "PAGE_NO";
    // The movie remote id identifier
    public static final String REQUEST_MOVIE_REMOTE_ID = "MOVIE_REMOTE_ID";
    // The class Log identifier
    private static final String LOG_TAG = TrailerIntentService.class.getSimpleName();
    // The movie's remote id.
    private long requestMovieRemoteId;
    // The request page  no.
    private int requestPageNo;

    // Default Constructor
    public TrailerIntentService() {
        // Name worker thread
        super("trailer-intent-service");
    }

    @Override
    public void onCreate() {
        // Overriding onCreate() requires a call to super().
        super.onCreate();
    }

    // What will happen when service is triggered
    @Override
    protected void onHandleIntent(Intent intent) {
        //Acquire the page no.
        requestPageNo = intent.getIntExtra(REQUEST_PAGE_NO_INT, 1);
        //Acquire the movie's remote id
        requestMovieRemoteId = intent.getLongExtra(REQUEST_MOVIE_REMOTE_ID, 1);
        // Set the sort type to use
        setPageNo(requestPageNo);
        // Set the movie's remote id
        setMovieRemoteId(requestMovieRemoteId);
        // Go get some online data
        getOnlineData();
    }

    // Get the list of movies from online
    private void getOnlineData() {
        // Check whether we are in debug mode
        if (BuildConfig.IS_DEBUG_MODE) {
            Log.d(LOG_TAG, "Online data loaded ");
        }
        // Get the MoviesAPIService and use it to retrieve a list of movies
        IMoviesAPIService movieService = ApplicationController.getApplicationInstance().getMovieServiceOfApiType(EAPITypes.MOVIES_API);
        // Retrieve the movies data
        Call<TrailerCollection> createdCall = movieService.loadTrailerData(requestMovieRemoteId, requestPageNo, BuildConfig.IMDB_API_KEY);
        // Asynchronous access
        createdCall.enqueue(new TrailerCallBack(getApplicationContext()));
    }


    // Set the page no.
    private void setPageNo(int pageNo) {
        requestPageNo = pageNo;
    }

    public void setMovieRemoteId(long movieRemoteId) {
        this.requestMovieRemoteId = movieRemoteId;
    }
}