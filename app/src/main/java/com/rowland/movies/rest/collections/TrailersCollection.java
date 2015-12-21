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
 *
 */

package com.rowland.movies.rest.collections;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rowland.movies.rest.pojos.Trailers;

import java.util.List;

/**
 * Created by Rowland on 12/11/2015.
 */
public class TrailersCollection {

    // Gson annotations
    @SerializedName("results")
    @Expose
    public List<Trailers> results;

    /**
     *
     * @return
     * The results
     */
    public List<Trailers> getResults() {
        return results;
    }

    /**
     *
     * @param results
     * The results
     */
    public void setResults(List<Trailers> results) {
        this.results = results;
    }

}
