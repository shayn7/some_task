package client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "apiClient", url = "http://servername")
public interface ApiClient {
    @GetMapping("/?getdata={code}")
    String getData(@RequestParam("code") String code);

    @PostMapping("/?code={code}")
    String postCode(@RequestParam("code") String code);

    @GetMapping
    byte[] downloadFile(@RequestParam("url") String url);
}
