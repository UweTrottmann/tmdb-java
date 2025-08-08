// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.entities.Certifications;
import org.junit.Test;
import retrofit2.Call;

import java.io.IOException;

import static com.uwetrottmann.tmdb2.assertions.CertificationAssertions.assertCertifications;

public class CertificationsServiceTest extends BaseTestCase {

    @Test
    public void test_certification_movie() throws IOException {
        Call<Certifications> call = getUnauthenticatedInstance().certificationsService().movie();

        Certifications certifications = call.execute().body();

        assertCertifications(certifications);
    }

    @Test
    public void test_certification_tv() throws IOException {
        Call<Certifications> call = getUnauthenticatedInstance().certificationsService().tv();

        Certifications certifications = call.execute().body();

        assertCertifications(certifications);
    }
}
