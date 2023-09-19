package github.abhiace.ecomstore.orderservice.exception;

import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.util.Map;

@Component
public class RestTemplateErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode() == HttpStatus.NOT_FOUND;
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        Map<String, Object> responseBodyMap = new GsonJsonParser().parseMap(new String(response.getBody().readAllBytes()));
        throw new InvalidOrderException(responseBodyMap.get("errorMessage").toString(), response.getStatusCode().toString());
    }
}
