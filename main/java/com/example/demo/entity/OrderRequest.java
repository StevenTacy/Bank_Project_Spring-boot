package com.example.demo.entity;

import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;



@Component
public class OrderRequest {
	 public enum PayType { A, C };
	    public enum AutoBilling { Y, N };
	    
	   
	    public class ATMParam{
	        private Integer ExpireDate;
	    }
	   
	    public class CardParam{
	        private AutoBilling AutoBilling;
	        private Integer ExpBillingDays;
	        private Integer ExpMinutes;
	        private String PayTypeSub;
	    }
	    
	    private String ShopNo;
	    private String OrderNo;
	    private Integer Amount;
	    private String CurrencyID;
	    private String PrdtName;
	    private String Memo;
	    private String Param1;
	    private String Param2;
	    private String Param3;
	    private String ReturnURL;
	    private String BackendURL;
	    private PayType PayType;
	    private ATMParam ATMParam;
	    private CardParam CardParam;
		public String getShopNo() {
			return ShopNo;
		}
		public void setShopNo(String shopNo) {
			ShopNo = shopNo;
		}
		public String getOrderNo() {
			return OrderNo;
		}
		public void setOrderNo(String orderNo) {
			OrderNo = orderNo;
		}
		public Integer getAmount() {
			return Amount;
		}
		public void setAmount(Integer amount) {
			Amount = amount;
		}
		public String getCurrencyID() {
			return CurrencyID;
		}
		public void setCurrencyID(String currencyID) {
			CurrencyID = currencyID;
		}
		public String getPrdtName() {
			return PrdtName;
		}
		public void setPrdtName(String prdtName) {
			PrdtName = prdtName;
		}
		public String getMemo() {
			return Memo;
		}
		public void setMemo(String memo) {
			Memo = memo;
		}
		public String getParam1() {
			return Param1;
		}
		public void setParam1(String param1) {
			Param1 = param1;
		}
		public String getParam2() {
			return Param2;
		}
		public void setParam2(String param2) {
			Param2 = param2;
		}
		public String getParam3() {
			return Param3;
		}
		public void setParam3(String param3) {
			Param3 = param3;
		}
		public String getReturnURL() {
			return ReturnURL;
		}
		public void setReturnURL(String returnURL) {
			ReturnURL = returnURL;
		}
		public String getBackendURL() {
			return BackendURL;
		}
		public void setBackendURL(String backendURL) {
			BackendURL = backendURL;
		}
		public PayType getPayType() {
			return PayType;
		}
		public void setPayType(PayType payType) {
			PayType = payType;
		}
		public ATMParam getATMParam() {
			return ATMParam;
		}
		public void setATMParam(ATMParam aTMParam) {
			ATMParam = aTMParam;
		}
		public CardParam getCardParam() {
			return CardParam;
		}
		public void setCardParam(CardParam cardParam) {
			CardParam = cardParam;
		}
	    


	}

