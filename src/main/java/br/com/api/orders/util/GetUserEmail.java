package br.com.api.orders.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.api.orders.model.Order;

public class GetUserEmail {
    public static Users userExist(Order order) throws Exception {
        Users user = new Users();

        RestTemplate rest = new RestTemplate();
        String url = "http://localhost:8082/read/";

        ResponseEntity<String> response = rest.getForEntity(url + order.getIdUser(), String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode id = root.path("id");
        
        if(id != null) {
            String name = root.get("name").textValue();
            String email = root.get("email").textValue();

            user.setName(name);
            user.setEmail(email);

            return user;
        }

        throw new Exception("{\"error\":\"O usuário utilizado não existe!\"}");
    }
}

