package com.example.kur.service;

import com.example.kur.model.Kur;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

@Service
public class TcmbKurService {

    public String paraKodunaGoreKurBilgisi(String kur) throws Exception {
        return parse(kur);
    }

    private HashMap<String, Integer> tcmbSiraliKurlar() {
        HashMap<String, Integer> kurMap = new HashMap<>();
        kurMap.put("USD", 0);
        kurMap.put("AUD", 1);
        kurMap.put("DKK", 2);
        kurMap.put("EUR", 3);
        kurMap.put("GBP", 4);
        kurMap.put("CHF", 5);
        kurMap.put("SEK", 6);
        kurMap.put("CAD", 7);
        kurMap.put("KWD", 8);
        kurMap.put("NOK", 9);
        kurMap.put("SAR", 10);
        kurMap.put("JPY", 11);
        kurMap.put("BGN", 12);
        kurMap.put("RON", 13);
        kurMap.put("RUB", 14);
        kurMap.put("IRR", 15);
        kurMap.put("CNY", 16);
        kurMap.put("PKR", 17);
        kurMap.put("QAR", 18);

        return kurMap;
    }

    private String parse(String paraKodu) throws Exception {

        if (paraKodu == null || paraKodu.isEmpty()) {
            return "Hatali para birimi girdiniz!";
        }

        final String TCMB_DOVIZ_URL = "https://www.tcmb.gov.tr/kurlar/today.xml";

        HashMap<String, Integer> map = tcmbSiraliKurlar();
        Collection<String> kurListesi = map.keySet();
        int kurKoduIndex = 20;

        for (String kod : kurListesi) {
            if (kod.equalsIgnoreCase(paraKodu) || kod.contains(paraKodu)) {
                kurKoduIndex = map.get(kod);
            }
        }

        if(kurKoduIndex == 20){
            return "Eksik veya hatali para birimi girdiniz!";
        }

        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();

        Document document = builder.parse(new URL(TCMB_DOVIZ_URL).openStream());

        NodeList nodeList = document.getDocumentElement().getElementsByTagName("Currency");

        Node node = nodeList.item(kurKoduIndex);

        Kur kur = null;

        if (node != null && node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            kur = new Kur();
            kur.setTarih(document.getDocumentElement().getAttribute("Date"));
            kur.setBirim(Integer.parseInt(element.getElementsByTagName("Unit").item(0).getTextContent()));
            kur.setParaKodu(paraKodu);
            kur.setAlisFiyati(new BigDecimal(element.getElementsByTagName("ForexBuying").item(0).getTextContent()));
            kur.setSatisFiyati(new BigDecimal(element.getElementsByTagName("ForexSelling").item(0).getTextContent()));
            kur.setCevrildigiParaKodu("TRY");
        }

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(kur);
        return jsonString;
    }

}
