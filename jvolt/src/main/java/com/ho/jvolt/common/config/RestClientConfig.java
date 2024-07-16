package com.ho.jvolt.common.config;

import com.ho.jvolt.auth.oauth2.kakao.KakaoComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Slf4j
@Configuration
public class RestClientConfig {

    @Bean
    public KakaoComponent kakaoRestClient(){
        RestClient restClient = RestClient.builder()
                .baseUrl("https://kauth.kakao.com")
                .defaultRequest((requestHeadersSpec -> {
                    log.info(requestHeadersSpec.toString());
                }))
                .defaultStatusHandler(HttpStatusCode::isError,
                        (request, response) -> {

                            log.info(request.getURI().toString());
                            log.error("Client Error Status " + response.getStatusCode());
                            log.error("Client Error Body "+ new String(response.getBody().readAllBytes()));
                })
                .build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

        return factory.createClient(KakaoComponent.class);
    }
}
