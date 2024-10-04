package com.loonds.places.controller;

import com.loonds.places.dto.VendorDto;
import com.loonds.places.service.PlaceService;
import com.loonds.places.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.SignatureException;

@RestController
public class EchoController {
    @Autowired
    PlaceService placeService;
    @Autowired
    VendorService vendorService;

    @GetMapping("/vendor")
    public VendorDto create(@RequestBody VendorDto vendorDto) throws SignatureException {
        return vendorService.create(vendorDto);
    }
}
