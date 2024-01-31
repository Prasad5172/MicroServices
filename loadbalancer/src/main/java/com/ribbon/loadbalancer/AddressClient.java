package com.ribbon.loadbalancer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "schools", path = "/schools")
public interface AddressClient {
    @GetMapping("/api/v1/schools/test")
    public ResponseEntity<String> getAddressByEmployeeId();
}
