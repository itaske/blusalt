# blusalt

Build the docker compose up and run it.
Reach the Customer Service on port 8080, Billing Service on port 8081.
This is the endpoint used to may bill payment http://localhost:8080/api/v1/customers/bill for Customers
Body {
    "customerId": "1",
    "amount":12343
}
this is the endpoint for getting status of Transaction http://localhost:8080/api/v1/customers/transaction/cb2b1ccf-1eaa-4356-9262-463737a45ee1
Can be used to check when the Status becomes successful
