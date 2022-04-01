package com.example.kur;

import com.example.kur.service.TcmbKurService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class KurApplicationTests {

    @Autowired
    TcmbKurService tcmbKurService;

    @Test
    void dogruKurTesti() throws Exception {
        final String kur = "USD";
        assertEquals("{\"birim\":1,\"tarih\":\"04/01/2022\",\"paraKodu\":\"USD\",\"satisFiyati\":14.6873,\"alisFiyati\":14.6609,\"cevrildigiParaKodu\":\"TL\"}",tcmbKurService.paraKodunaGoreKurBilgisi(kur));
    }

    @Test
    void hataliKurTesti() throws Exception {
        final String kur = "USDDD";
        assertEquals("Eksik veya hatali para birimi girdiniz!",tcmbKurService.paraKodunaGoreKurBilgisi(kur));
    }
}
