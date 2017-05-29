/*
 * Copyright 2015 Manuel Laggner
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
 */
package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.entities.AppendToResponse;
import com.uwetrottmann.tmdb2.entities.Collection;
import com.uwetrottmann.tmdb2.entities.Images;
import com.uwetrottmann.tmdb2.enumerations.AppendToResponseItem;
import org.junit.Test;
import retrofit2.Call;

import java.io.IOException;

import static com.uwetrottmann.tmdb2.TestData.testCollection;
import static com.uwetrottmann.tmdb2.assertions.CollectionAssertions.assertCollection;
import static com.uwetrottmann.tmdb2.assertions.GenericAssertions.assertImages;
import static org.assertj.core.api.Assertions.assertThat;

public class CollectionsServiceTest extends BaseTestCase {
    public CollectionsServiceTest() {
    }

    @Test
    public void test_summary() throws IOException {
        Call<Collection> call = this.getUnauthenticatedInstance().collectionService().summary(
                testCollection.id
        );

        Collection collection = call.execute().body();

        assertCollection(collection);
    }

    @Test
    public void test_summary_append_images() throws IOException {
        Call<Collection> call = this.getUnauthenticatedInstance().collectionService().summary(
                testCollection.id,
                new AppendToResponse(AppendToResponseItem.IMAGES)
        );

        Collection collection = call.execute().body();

        assertCollection(collection);
    }

    @Test
    public void test_images() throws IOException {
        Call<Images> call = this.getUnauthenticatedInstance().collectionService().images(
                testCollection.id,
                null
        );

        Images images = call.execute().body();

        assertThat(images).isNotNull();
        assertThat(images.id).isNotNull();
        assertImages(images.posters);
        assertImages(images.backdrops);
    }
}