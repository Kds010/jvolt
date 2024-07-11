package com.ho.jvolt.common.config;

import com.ho.jvolt.auth.oauth2.kakao.KakaoComponent;
import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class RestClientConfig {

    private Logger logger =

    @Bean
    public KakaoComponent kakaoRestClient(){
        RestClient restClient = RestClient.builder()
                .baseUrl("https://kauth.kakao.com")
                .defaultStatusHandler(HttpStatusCode::isError,
                        (request, response) -> {

                })
                .build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

        return factory.createClient(KakaoComponent.class);
    }
}
