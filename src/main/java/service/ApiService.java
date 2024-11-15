package service;

import client.ApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ApiService {
    private final ApiClient apiClient;
    private final ApiLogService apiLogService;

    public String getData(String code) {
        String response = apiClient.getData(code);
        apiLogService.logRequest("GET", "/?getdata=" + code, code);
        apiLogService.logResponse(response);
        return response;
    }


    public String postCode(String code) {
        String response = apiClient.postCode(code);
        apiLogService.logRequest("POST", "/?code=" + code, code);
        apiLogService.logResponse(response);
        return response;
    }

    //TODO: downloadFile method


}
