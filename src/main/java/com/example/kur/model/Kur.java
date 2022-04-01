package com.example.kur.model;

import java.math.BigDecimal;

public class Kur {

    private int birim;
    private String tarih;
    private String paraKodu;
    private BigDecimal satisFiyati;
    private BigDecimal alisFiyati;
    private String cevrildigiParaKodu;

    public int getBirim() {
        return birim;
    }

    public void setBirim(int birim) {
        this.birim = birim;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getParaKodu() {
        return paraKodu;
    }

    public void setParaKodu(String paraKodu) {
        this.paraKodu = paraKodu;
    }

    public BigDecimal getSatisFiyati() {
        return satisFiyati;
    }

    public void setSatisFiyati(BigDecimal satisFiyati) {
        this.satisFiyati = satisFiyati;
    }

    public BigDecimal getAlisFiyati() {
        return alisFiyati;
    }

    public void setAlisFiyati(BigDecimal alisFiyati) {
        this.alisFiyati = alisFiyati;
    }

    public String getCevrildigiParaKodu() {
        return cevrildigiParaKodu;
    }

    public void setCevrildigiParaKodu(String cevrildigiParaKodu) {
        this.cevrildigiParaKodu = cevrildigiParaKodu;
    }
}
