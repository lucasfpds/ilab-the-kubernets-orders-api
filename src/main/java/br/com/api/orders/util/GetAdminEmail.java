package br.com.api.orders.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.api.orders.model.Order;

public class GetAdminEmail {
    private static final String PREFIX = "Bearer ";

    public static String adminExist(Order order, String token) throws Exception {
        token = token.replace(PREFIX, "").trim();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<?> entity = new HttpEntity<Object>(headers);

        RestTemplate rest = new RestTemplate();
        String url = "http://clusterip-admin:8080/admin/";
                
        ResponseEntity<String> response = rest.exchange(url + order.getIdAdmin(), HttpMethod.GET, entity, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode id = root.path("id");


        if (id != null) {
            String email = root.get("email").textValue();

            return email;
        }

        throw new Exception("{\"error\":\"O administrador não tem um ID válido!\"}");
    }
}
