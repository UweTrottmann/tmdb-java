package com.uwetrottmann.tmdb2.services.rx;

import static com.uwetrottmann.tmdb2.assertions.CertificationAssertions.assertCertifications;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.entities.Certifications;
import io.reactivex.Observable;
import java.io.IOException;
import org.junit.Test;

public class CertificationsServiceTest extends BaseTestCase {

    @Test
    public void test_certification_movie() throws IOException {
        Observable<Certifications> call = getUnauthenticatedInstance().rx.certificationsService().movie();

        Certifications certifications = call.singleOrError().blockingGet();

        assertCertifications(certifications);
    }

    @Test
    public void test_certification_tv() throws IOException {
        Observable<Certifications> call = getUnauthenticatedInstance().rx.certificationsService().tv();

        Certifications certifications = call.singleOrError().blockingGet();

        assertCertifications(certifications);
    }
}
