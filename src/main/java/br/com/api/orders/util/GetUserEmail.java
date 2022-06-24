package br.com.api.orders.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.api.orders.model.Order;

public class GetUserEmail {
    private static final String PREFIX = "Bearer ";

    public static Users userExist(Order order, String token) throws Exception {
        Users user = new Users();

        token = token.replace(PREFIX, "").trim();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "BEARER " + token);

        HttpEntity<?> entity = new HttpEntity<Object>(headers);

        RestTemplate rest = new RestTemplate();
        String url = "http://a2a2cf5d13cf74131bc6dc3880894c1a-1494030207.us-east-1.elb.amazonaws.com/user/read/";

        ResponseEntity<String> response = rest.exchange(url + order.getIdUser(), HttpMethod.GET, entity, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode id = root.path("id");

        if (id != null) {
            String name = root.get("name").textValue();
            String email = root.get("email").textValue();

            user.setName(name);
            user.setEmail(email);

            return user;
        }

        throw new Exception("{\"error\":\"O usuário utilizado não existe!\"}");
    }
}