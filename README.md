# blusalt

Build the docker compose up and run it.
Reach the Customer Service on port 8080, Billing Service on port 8081.
This is the endpoint used to may bill payment http://localhost:8080/api/v1/customers/bill for Customers
Body {
    "customerId": "1",
    "amount":12343
}
The endpoint for getting status of Transaction is http://localhost:8080/api/v1/customers/transaction/{transaction-id}
Can be used to check when the Status becomes successful
