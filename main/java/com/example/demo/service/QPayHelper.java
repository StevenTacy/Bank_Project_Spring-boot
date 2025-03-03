package com.example.demo.service;

import java.io.IOException;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import org.apache.commons.codec.digest.DigestUtils;

import com.example.demo.entity.OrderRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;




public class QPayHelper {
	
	private String getNonce(){
	    HttpUtil util = new HttpUtil();
	    String shopNo= SystemConfigUtil.systemConfMap.get("ShopNo");
	    String nonceUrl= SystemConfigUtil.systemConfMap.get("Qpay_API_Url_Nonce");

	    Map<String,String> map = new HashMap<String,String>();
	    map.put("ShopNo", shopNo);

	    String json;
	    String nonce="";
	    try {
	        json = new ObjectMapper().writeValueAsString(map);
	        nonce=util.post(nonceUrl, json);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return nonce;
	}
	
    private String getHashID(){
        BigInteger hashA1 = new BigInteger(
        SystemConfigUtil.systemConfMap.get("HASH_ID_A1"), 16);
        BigInteger hashA2 = new BigInteger(
        SystemConfigUtil.systemConfMap.get("HASH_ID_A2"), 16);
        BigInteger hashB1 = new BigInteger(
        SystemConfigUtil.systemConfMap.get("HASH_ID_B1"), 16);
        BigInteger hashB2 = new BigInteger(
        SystemConfigUtil.systemConfMap.get("HASH_ID_B2"), 16);
        BigInteger hashA = hashA1.xor(hashA2);
        BigInteger hashB = hashB1.xor(hashB2);
        String hashID =hashA.toString(16)+hashB.toString(16);
        return hashID.toUpperCase();
    }
    
    private String getIV(String nonce){
	    String sha256hex = DigestUtils.sha256Hex(nonce); 
	    String iv = sha256hex.substring(sha256hex.length()-16, sha256hex.length()).toUpperCase();
	    return iv;
	}
    
    private String getSign(Map<String, Object> map,String nonce,String hashid){
        //remove sub object
        map.values().removeIf(entries->entries.getClass().equals(java.util.LinkedHashMap.class)
        ||entries.toString().isBlank());
        //order
        String param=map.entrySet().stream()
          .map(p -> p.getKey() + "=" + p.getValue())
          .reduce((p1, p2) -> p1 + "&" + p2)
          .orElse("");

        //convert to string
        StringBuilder content = new StringBuilder();

        content.append(param).
                append(nonce).
                append(hashid);
        String sign= DigestUtils.sha256Hex(content.toString()).toUpperCase(); 
        return sign;
    }
    
    public String orderCreate(OrderRequest orderRequest){

        //1.nonce
        String nonce=getNonce();
        //2.hashID
        String hashID=getHashID();
        //3.iv
        String iv = getIV(nonce);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(new QpayPropNamingStrategy());
        Map<String, Object> map = 
        mapper.convertValue(orderRequest, new TypeReference<TreeMap<String, Object>>() {});
        //remove null
        map.values().removeIf(Objects::isNull);
        map.forEach((k, v) -> {
            if(v.getClass().equals(java.util.LinkedHashMap.class)){
                LinkedHashMap<Object,Object>m =(LinkedHashMap<Object, Object>) v;
                m.values().removeIf(Objects::isNull);
                map.replace(k, v, m);
            }
        });

        String jsonContent ="";
        try {
            jsonContent = new ObjectMapper().writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        //4.sign
        String sign = getSign(map,nonce,hashID);

        //5.message
        String message = "";
            try {
                message= EncrptedUtil.encrypt(jsonContent, hashID, iv);
            } catch (IOException | GeneralSecurityException e) {
                e.printStackTrace();
            }

        //send to api
        Map<String,String> request = new HashMap<String,String>();
        request.put("Version", "1.0.0");
        request.put("ShopNo", "NA0040_001");
        request.put("APIService", "OrderCreate");
        request.put("Sign", sign);        
        request.put("Nonce", nonce);
        request.put("Message", message);

        String reqJson="";
        try {
            reqJson = new ObjectMapper().writeValueAsString(request);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String res="";
        
        try {
        	 
            res=util.post("https://apisbx.sinopac.com/funBIZ/QPay.WebAPI/api/Order", reqJson);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return res;
    }
    
    
    
}
