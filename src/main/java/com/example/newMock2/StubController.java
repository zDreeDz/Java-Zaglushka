package com.example.newMock2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
public class StubController {

    private static final DecimalFormat decimalFormat = new DecimalFormat("#.00");

    @PostMapping(value = "/stub", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> handleStub(@RequestBody Map<String, String> request) {
        String clientId = request.get("clientId");
        String rqUID = request.get("rqUID");
        String account = request.get("account");

        String currency;
        double maxLimit;

        if (clientId.startsWith("8")) {
            currency = "US";
            maxLimit = 2000.00;
        } else if (clientId.startsWith("9")) {
            currency = "EU";
            maxLimit = 1000.00;
        } else {
            currency = "RUB";
            maxLimit = 10000.00;
        }

        Random random = new Random();
        String balance = decimalFormat.format(random.nextDouble() * maxLimit);

        Map<String, String> response = new HashMap<>();
        response.put("rqUID", rqUID);
        response.put("clientId", clientId);
        response.put("account", account);
        response.put("currency", currency);
        response.put("balance", balance);
        response.put("maxLimit", decimalFormat.format(maxLimit));

        return response;
    }
}