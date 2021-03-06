package cn.edu.cdtu.entity;
// default package
// Generated 2015-6-28 18:16:30 by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;

/**
 * TOrderitem generated by hbm2java
 */
public class TOrderitem implements java.io.Serializable {

	private String orderitemId;
	private TBook TBook;
	private TOrder TOrder;
	private String orderitemName;
	private BigDecimal orderitemPrice;
	private BigDecimal orderitemSubtotal;
	private String orderitemImage;
	private Integer orderitemCount;

	public TOrderitem() {
	}

	public TOrderitem(String orderitemId) {
		this.orderitemId = orderitemId;
	}

	public TOrderitem(String orderitemId, TBook TBook, TOrder TOrder,
			String orderitemName, BigDecimal orderitemPrice,
			BigDecimal orderitemSubtotal, String orderitemImage,
			Integer orderitemCount) {
		this.orderitemId = orderitemId;
		this.TBook = TBook;
		this.TOrder = TOrder;
		this.orderitemName = orderitemName;
		this.orderitemPrice = orderitemPrice;
		this.orderitemSubtotal = orderitemSubtotal;
		this.orderitemImage = orderitemImage;
		this.orderitemCount = orderitemCount;
	}

	public String getOrderitemId() {
		return this.orderitemId;
	}

	public void setOrderitemId(String orderitemId) {
		this.orderitemId = orderitemId;
	}

	public TBook getTBook() {
		return this.TBook;
	}

	public void setTBook(TBook TBook) {
		this.TBook = TBook;
	}

	public TOrder getTOrder() {
		return this.TOrder;
	}

	public void setTOrder(TOrder TOrder) {
		this.TOrder = TOrder;
	}

	public String getOrderitemName() {
		return this.orderitemName;
	}

	public void setOrderitemName(String orderitemName) {
		this.orderitemName = orderitemName;
	}

	public BigDecimal getOrderitemPrice() {
		return this.orderitemPrice;
	}

	public void setOrderitemPrice(BigDecimal orderitemPrice) {
		this.orderitemPrice = orderitemPrice;
	}

	public BigDecimal getOrderitemSubtotal() {
		return this.orderitemSubtotal;
	}

	public void setOrderitemSubtotal(BigDecimal orderitemSubtotal) {
		this.orderitemSubtotal = orderitemSubtotal;
	}

	public String getOrderitemImage() {
		return this.orderitemImage;
	}

	public void setOrderitemImage(String orderitemImage) {
		this.orderitemImage = orderitemImage;
	}

	public Integer getOrderitemCount() {
		return this.orderitemCount;
	}

	public void setOrderitemCount(Integer orderitemCount) {
		this.orderitemCount = orderitemCount;
	}

}
