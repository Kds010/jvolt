package com.ho.jvolt.common.config;

import com.ho.jvolt.auth.oauth2.kakao.KakaoComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class RestClientConfig {

    private final Logger logger = LoggerFactory.getLogger(RestClientConfig.class);

    @Bean
    public KakaoComponent kakaoRestClient(){
        RestClient restClient = RestClient.builder()
                .defaultRequest((requestHeadersSpec -> {
                    logger.info(requestHeadersSpec.toString());
                }))
                .defaultStatusHandler(HttpStatusCode::isError,
                        (request, response) -> {

                            logger.info(request.getURI().toString());
                            logger.error("Client Error Status " + response.getStatusCode());
                            logger.error("Client Error Body "+ new String(response.getBody().readAllBytes()));
                })
                .build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

        return factory.createClient(KakaoComponent.class);
    }
}
