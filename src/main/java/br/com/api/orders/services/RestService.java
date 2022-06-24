package br.com.api.orders.services;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.google.gson.Gson;
import com.newrelic.agent.deps.org.apache.http.client.ClientProtocolException;

import br.com.api.orders.dto.ResponseBody;

public class RestService {
    private static final String HEADER = "Authorization";
    private static final String PREFIX = "Bearer ";

    public static Authentication validate(HttpServletRequest requestOne) 
    throws ClientProtocolException, IOException {
        String token = requestOne.getHeader(HEADER);
        token = token.replace(PREFIX, "").trim();

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://a2a2cf5d13cf74131bc6dc3880894c1a-1494030207.us-east-1.elb.amazonaws.com/admin/token/valid");
        String json = "{\"token\":\"" + token + "\"}";
        StringEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        
        CloseableHttpResponse response = client.execute(httpPost);
        String bodyAsString = EntityUtils.toString(response.getEntity());
        client.close();
        ResponseBody responseBody = new Gson().fromJson(bodyAsString,
        ResponseBody.class);
        return new UsernamePasswordAuthenticationToken(responseBody.getPrincipal(), null, Collections.emptyList());
        }
    }
