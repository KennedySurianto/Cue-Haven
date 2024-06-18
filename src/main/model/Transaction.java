package main.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Transaction {
	private Integer transactionId;
	private LocalDate transactionDate;
	private String customerName;
	private String tableNumber;
	private LocalTime startTime;
	private LocalTime endTime;
	
	public Transaction() {}
	
	public Transaction(Integer transactionId, LocalDate transactionDate, String customerName, String tableNumber,
			LocalTime startTime, LocalTime endTime) {
		super();
		this.transactionId = transactionId;
		this.transactionDate = transactionDate;
		this.customerName = customerName;
		this.tableNumber = tableNumber;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public void startTransaction(String customerName, String tableNumber) {
		this.transactionDate = LocalDate.now();
		this.customerName = customerName;
		this.tableNumber = tableNumber;
		this.startTime = LocalTime.now();
		this.endTime = null;
	}
	
	public void endTransaction(LocalTime endTime) {
		this.endTime = endTime;
	}
	
	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(String tableNumber) {
		this.tableNumber = tableNumber;
	}
	
}
