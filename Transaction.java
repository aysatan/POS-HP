
public class Transaction {

	private String transactionID, itemID, operatorID, customerID, memberID, date;
	private Integer quantityPurchased, subTotalPrice, totalPrice, paidAmount, changeAmount;
	
	public String getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}
	public String getItemID() {
		return itemID;
	}
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}
	public String getOperatorID() {
		return operatorID;
	}
	public void setOperatorID(String operatorID) {
		this.operatorID = operatorID;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getQuantityPurchased() {
		return quantityPurchased;
	}
	public void setQuantityPurchased(Integer quantityPurchased) {
		this.quantityPurchased = quantityPurchased;
	}
	public Integer getSubTotalPrice() {
		return subTotalPrice;
	}
	public void setSubTotalPrice(Integer subTotalPrice) {
		this.subTotalPrice = subTotalPrice;
	}
	public Integer getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Integer getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(Integer paidAmount) {
		this.paidAmount = paidAmount;
	}
	public Integer getChangeAmount() {
		return changeAmount;
	}
	public void setChangeAmount(Integer changeAmount) {
		this.changeAmount = changeAmount;
	}
	
	public Transaction(String transactionID, String itemID, String operatorID, String customerID, String memberID,
			String date, Integer quantityPurchased, Integer subTotalPrice, Integer totalPrice, Integer paidAmount,
			Integer changeAmount) {
		super();
		this.transactionID = transactionID;
		this.itemID = itemID;
		this.operatorID = operatorID;
		this.customerID = customerID;
		this.memberID = memberID;
		this.date = date;
		this.quantityPurchased = quantityPurchased;
		this.subTotalPrice = subTotalPrice;
		this.totalPrice = totalPrice;
		this.paidAmount = paidAmount;
		this.changeAmount = changeAmount;
	}

}
