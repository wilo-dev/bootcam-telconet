package ec.telconet.mscompproofwilliamenriquez.leccion.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ec.telconet.mscompproofwilliamenriquez.leccion.entity.model.ProductoEntity;
import ec.telconet.mscompproofwilliamenriquez.leccion.entity.response.ProductoResponse;
import ec.telconet.mscompproofwilliamenriquez.leccion.repository.ProductoRepository;
import ec.telconet.mscompproofwilliamenriquez.util.entity.OutputEntity;
import ec.telconet.mscompproofwilliamenriquez.util.enums.MessageEnum;
import ec.telconet.mscompproofwilliamenriquez.util.exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApiService {

    @Value("${product-api.url}")
    private String url;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private RestTemplate restTemplate;


    public String fetchDataFromApi() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        } else {
            // Manejo de errores aquí
            throw new RuntimeException("Error al consumir la API. Código de estado: " + responseEntity.getStatusCodeValue());
        }
    }

    public void fetchDataAndSaveToDatabase(int skip, int limit) {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url + "?skip=" + skip + "&limit=" + limit, String.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            String responseBody = responseEntity.getBody();
            saveProductsToDatabase(responseBody);
        } else {
            // Manejo de errores aquí
            throw new RuntimeException("Error al consumir la API. Código de estado: " + responseEntity.getStatusCodeValue());
        }
    }

    private void saveProductsToDatabase(String responseBody) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(responseBody);

            // Verificar si la respuesta tiene la clave "products" y es un array
            if (jsonNode.has("products") && jsonNode.get("products").isArray()) {
                JsonNode productsNode = jsonNode.get("products");

                for (JsonNode productNode : productsNode) {
                    ProductoEntity producto = objectMapper.treeToValue(productNode, ProductoEntity.class);
                    // Puedes procesar más los datos si es necesario
                    productoRepository.save(producto);
                }
            } else {
                // Manejar el caso en que la respuesta no tiene la clave "products" o no es un array
                throw new RuntimeException("La respuesta de la API no tiene la clave 'products' o no es un array JSON.");
            }
        } catch (IOException e) {
            // Manejo de errores aquí
            throw new RuntimeException("Error al procesar la respuesta de la API.", e);
        }
    }


}
