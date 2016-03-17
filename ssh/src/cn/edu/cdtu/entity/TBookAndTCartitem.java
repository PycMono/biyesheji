package cn.edu.cdtu.entity;

import java.math.BigDecimal;

public class TBookAndTCartitem {
private String bookName;
private BigDecimal bookCurrPrice;
private Integer cartitemCount;
private String cartitemId;
private String bookImageB;
public String getBookName() {
	return bookName;
}
public TBookAndTCartitem() {
	super();
	// TODO Auto-generated constructor stub
}
public TBookAndTCartitem(String bookName, BigDecimal bookCurrPrice,
		Integer cartitemCount, String cartitemId, String bookImageB) {
	super();
	this.bookName = bookName;
	this.bookCurrPrice = bookCurrPrice;
	this.cartitemCount = cartitemCount;
	this.cartitemId = cartitemId;
	this.bookImageB = bookImageB;
}
public void setBookName(String bookName) {
	this.bookName = bookName;
}
public BigDecimal getBookCurrPrice() {
	return bookCurrPrice;
}
public void setBookCurrPrice(BigDecimal bookCurrPrice) {
	this.bookCurrPrice = bookCurrPrice;
}
public Integer getCartitemCount() {
	return cartitemCount;
}
public void setCartitemCount(Integer cartitemCount) {
	this.cartitemCount = cartitemCount;
}
public String getCartitemId() {
	return cartitemId;
}
public void setCartitemId(String cartitemId) {
	this.cartitemId = cartitemId;
}
public String getBookImageB() {
	return bookImageB;
}
public void setBookImageB(String bookImageB) {
	this.bookImageB = bookImageB;
}
}
