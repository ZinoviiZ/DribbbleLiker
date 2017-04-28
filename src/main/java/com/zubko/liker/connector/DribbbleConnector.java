package com.zubko.liker.connector;

import com.zubko.liker.entity.DribbbleShot;
import com.zubko.liker.model.enums.DribbbleMethods;
import com.zubko.liker.model.response.DribbbleAccessTokenResponse;
import com.zubko.liker.model.response.DribbbleLikeShotResponse;
import com.zubko.liker.model.response.DribbbleUserResponse;
import com.zubko.liker.model.response.DribbbleUserShotsResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.zubko.liker.model.common.DribbbleKeys.CLIENT_ID;
import static com.zubko.liker.model.common.DribbbleKeys.CLIENT_SECRET;
import static com.zubko.liker.model.enums.DribbbleMethods.*;

/**
 * Created by zinoviyzubko on 27.04.17.
 */
@Component
public class DribbbleConnector {

    private RestTemplate restTemplate;

    @PostConstruct
    private void init() {
//
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(new AuthScope(null, -1), new UsernamePasswordCredentials("username", "password"));
        HttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(credentialsProvider).build();
        restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));

//        restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new LinkedList<HttpMessageConverter<?>>();
        messageConverters.add(new FormHttpMessageConverter());
        messageConverters.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(messageConverters);
    }

    public DribbbleAccessTokenResponse invokeGetAccessToken(String code) {
        String url = DribbbleMethods.GET_ACCESS_TOCKEN.getUrl();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, ContentType.MULTIPART_FORM_DATA.getMimeType());
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", CLIENT_ID);
        map.add("client_secret", CLIENT_SECRET);
        map.add("code", code);
        HttpEntity<Map<String, String>> request = new HttpEntity(map, headers);
        DribbbleAccessTokenResponse response = restTemplate.postForObject(url, request, DribbbleAccessTokenResponse.class);
        return response;
    }

    public DribbbleLikeShotResponse invokeLikeShot(String accessToken, String shotId) {
        String url = LIKE_SHOT.getUrl();
        url = url.replace("${ID}", shotId);
        HttpHeaders headers = createHeader(accessToken);
        HttpEntity requestEntity = new HttpEntity(headers);
        ResponseEntity<DribbbleLikeShotResponse> responseEntity = restTemplate.exchange(url, LIKE_SHOT.getHttpMethod(), requestEntity, DribbbleLikeShotResponse.class);
        return responseEntity.getBody();
    }

    public DribbbleUserShotsResponse invokeGetUserShots(String accessToken, String userId) {
        String url = GET_USER_SHOTS.getUrl();
        url = url.replace("${ID}", userId);
        HttpHeaders headers = createHeader(accessToken);
        HttpEntity requestEntity = new HttpEntity(headers);
        ResponseEntity<DribbbleShot[]> responseEntity = restTemplate.exchange(url, GET_USER_SHOTS.getHttpMethod(), requestEntity, DribbbleShot[].class);
        DribbbleUserShotsResponse response = new DribbbleUserShotsResponse(Arrays.asList(responseEntity.getBody()));
        return response;
    }

    public DribbbleUserResponse invokeGetCurrentUser(String accessToken) {

        String url = GET_CURRENT_USER.getUrl();
        HttpHeaders headers = createHeader(accessToken);
        HttpEntity requestEntity = new HttpEntity(headers);
        ResponseEntity<DribbbleUserResponse> responseEntity = restTemplate.exchange(url, GET_CURRENT_USER.getHttpMethod(), requestEntity, DribbbleUserResponse.class);
        return responseEntity.getBody();
    }


    private HttpHeaders createHeader(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
        return headers;
    }
}
