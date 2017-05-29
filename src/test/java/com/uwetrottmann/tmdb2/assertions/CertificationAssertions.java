package com.uwetrottmann.tmdb2.assertions;

import com.uwetrottmann.tmdb2.entities.Certifications;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class CertificationAssertions {
    public static void assertCertification(Certifications.Certification certification) {
        assertThat(certification).isNotNull();
        assertThat(certification.certification).isNotNull();
        assertThat(certification.meaning).isNotNull();
        assertThat(certification.order).isNotNull();
    }
    public static void assertCertifications(Certifications certifications) {
        for (Map.Entry<String,List<Certifications.Certification>> certificationSet : certifications.certifications.entrySet()) {
            assertThat(certificationSet.getKey()).isNotNull();
            assertThat(certificationSet.getValue()).isNotNull();
            assertThat(certificationSet.getValue()).isNotEmpty();
            for (Certifications.Certification certification : certificationSet.getValue()) {
                assertCertification(certification);
            }
        }
    }
}
