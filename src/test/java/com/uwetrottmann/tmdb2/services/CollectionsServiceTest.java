package com.uwetrottmann.tmdb2.services;

import static com.uwetrottmann.tmdb2.TestData.testCollection;
import static com.uwetrottmann.tmdb2.assertions.CollectionAssertions.assertCollection;
import static com.uwetrottmann.tmdb2.assertions.GenericAssertions.assertImages;
import static org.assertj.core.api.Assertions.assertThat;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.entities.AppendToResponse;
import com.uwetrottmann.tmdb2.entities.Collection;
import com.uwetrottmann.tmdb2.entities.Images;
import com.uwetrottmann.tmdb2.enumerations.AppendToResponseItem;
import io.reactivex.Observable;
import java.io.IOException;
import org.junit.Test;

public class CollectionsServiceTest extends BaseTestCase {
    public CollectionsServiceTest() {
    }

    @Test
    public void test_summary() throws IOException {
        Observable<Collection> call = getUnauthenticatedInstance().rx.collectionService().summary(
                testCollection.id
        );

        Collection collection = call.singleOrError().blockingGet();

        assertCollection(collection);
    }

    @Test
    public void test_summary_append_images() throws IOException {
        Observable<Collection> call = getUnauthenticatedInstance().rx.collectionService().summary(
                testCollection.id,
                null,
                new AppendToResponse(AppendToResponseItem.IMAGES)
        );

        Collection collection = call.singleOrError().blockingGet();

        assertCollection(collection);
    }

    @Test
    public void test_images() throws IOException {
        Observable<Images> call = getUnauthenticatedInstance().rx.collectionService().images(
                testCollection.id,
                null
        );

        Images images = call.singleOrError().blockingGet();

        assertThat(images).isNotNull();
        assertThat(images.id).isNotNull();
        assertImages(images.posters);
        assertImages(images.backdrops);
    }
}