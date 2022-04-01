package com.example.kur.controller;

import com.example.kur.service.TcmbKurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class KurController {

    @Autowired
    TcmbKurService tcmbKurService;

    @RequestMapping("/kurGetir")
    public ResponseEntity kurGetir(@RequestParam("kur") String kur) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(tcmbKurService.paraKodunaGoreKurBilgisi(kur));
    }
}
