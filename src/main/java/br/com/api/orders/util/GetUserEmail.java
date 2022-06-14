package br.com.api.orders.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.api.orders.model.Order;

public class GetUserEmail {
    public static String userExist(Order order) throws Exception {
        RestTemplate rest = new RestTemplate();
        String url = "http://localhost:8082/read/";

        ResponseEntity<String> response = rest.getForEntity(url + order.getIdUser(), String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode id = root.path("id");
        
        if(id != null) {
            return root.get("email").textValue();
        }

        throw new Exception("{\"error\":\"O usuário utilizado não existe!\"}");
    }
}
