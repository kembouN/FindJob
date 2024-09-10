package com.jobfinder.config;

import com.jobfinder.entities.user.UserJobFinder;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import java.time.Instant;

public class JwtUtil {
    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.issuer}")
    private String jwtIssuer;

    private String createJwtToken(UserJobFinder user){
        String role = user.isEnterprise() ? "ENTREPRISE" : "CHERCHEUR";
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(jwtIssuer)
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(24 * 3600))
                .subject(user.getUsername())
                .claim("role", role)
                .build();

        var encoder = new NimbusJwtEncoder(
                new ImmutableSecret<>(jwtSecret.getBytes())
        );
        var params = JwtEncoderParameters.from(
                JwsHeader.with(MacAlgorithm.HS256).build(),
                claims
        );

        return encoder.encode(params).getTokenValue();
    }
}
