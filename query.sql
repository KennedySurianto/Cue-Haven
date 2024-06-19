CREATE TABLE Transactions (
	transaction_id SERIAL PRIMARY KEY,
	transaction_date DATE NOT NULL,
	customer_name VARCHAR(50) NOT NULL,
	table_number VARCHAR(3) NOT NULL,
	start_time TIME NOT NULL,
	end_time TIME NOT NULL
);

SELECT 
	transaction_id, 
	transaction_date, 
	customer_name, 
	table_number, 
	start_time, 
	end_time, 
	end_time - start_time AS play_time, 
	ROUND((EXTRACT(EPOCH FROM (end_time - start_time)) / 3600.0) * 50000, 0) AS charge
FROM Transactions
ORDER BY transaction_id DESC;