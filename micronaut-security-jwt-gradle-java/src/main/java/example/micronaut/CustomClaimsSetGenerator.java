package example.micronaut;

import com.nimbusds.jwt.JWTClaimsSet;
import io.micronaut.context.annotation.Replaces;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.HttpRequest;
import io.micronaut.runtime.ApplicationConfiguration;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.token.config.TokenConfiguration;
import io.micronaut.security.token.jwt.generator.claims.ClaimsAudienceProvider;
import io.micronaut.security.token.jwt.generator.claims.JWTClaimsSetGenerator;
import io.micronaut.security.token.jwt.generator.claims.JwtIdGenerator;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

@Singleton
@Replaces(bean = JWTClaimsSetGenerator.class)
public class CustomClaimsSetGenerator extends JWTClaimsSetGenerator {

    public CustomClaimsSetGenerator(TokenConfiguration tokenConfiguration,
                                    @Nullable JwtIdGenerator jwtIdGenerator,
                                    @Nullable ClaimsAudienceProvider claimsAudienceProvider,
                                    @Nullable ApplicationConfiguration applicationConfiguration) {
        super(tokenConfiguration, jwtIdGenerator, claimsAudienceProvider, applicationConfiguration);
    }

    @Override
    protected void populateWithAuthentication(JWTClaimsSet.Builder builder, Authentication authentication) {
        super.populateWithAuthentication(builder, authentication);
        populateKey(builder);
    }

    protected void populateKey(JWTClaimsSet.Builder builder) {
        builder.claim("key", "main");
    }
}