package com.example.demo.global.oauth.oauthcode;

import com.example.demo.domain.oauth.dto.OauthServerType;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

@Component
public class OauthCodeRequestUrlProviderComposite {

    private final Map<OauthServerType, OauthCodeRequestUrlProvider> mapping;

    public OauthCodeRequestUrlProviderComposite(Set<OauthCodeRequestUrlProvider> providers) {
        mapping = providers.stream()
                .collect(toMap(
                        OauthCodeRequestUrlProvider::supportServer,
                        identity()
                ));
    }

    public String provide(OauthServerType oauthServerType) {
        return getProvider(oauthServerType).get().provide();
    }

    private Optional<OauthCodeRequestUrlProvider> getProvider(OauthServerType oauthServerType) throws RuntimeException {
        return Optional.ofNullable(mapping.get(oauthServerType));
    }
}