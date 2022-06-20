package br.com.api.orders.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.api.orders.model.Order;

public class GetAdminEmail {
    public static String adminExist(Order order) throws Exception {
        RestTemplate rest = new RestTemplate();
        String url = "http://localhost:8080/admin/";
        
        ResponseEntity<String> response = rest.getForEntity(url + order.getIdAdmin(), String.class);

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
