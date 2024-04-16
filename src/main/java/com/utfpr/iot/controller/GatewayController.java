package com.utfpr.iot.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utfpr.iot.dto.GatewayDTO;
import com.utfpr.iot.model.Gateway;

@RestController
@RequestMapping("/gateway")
public class GatewayController {

    @GetMapping("/person/{id}")
    public List<Gateway> getGatewaysByPerson(GatewayDTO dto, long id) {
        return null;

    }

}
