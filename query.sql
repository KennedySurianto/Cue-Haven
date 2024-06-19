CREATE TABLE Transactions (
	transaction_id SERIAL PRIMARY KEY,
	transaction_date DATE NOT NULL,
	customer_name VARCHAR(50) NOT NULL,
	table_number VARCHAR(3) NOT NULL,
	start_time TIME NOT NULL,
	end_time TIME NOT NULL
);