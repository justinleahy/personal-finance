# Personal Finance Documentation

This is a custom personal finance application. It is being developing in my free time to assist me in knowing where my money is being spent.
The backend if being written in Java with the Spring framework. The frontend has yet to be decided.

Core Tenants:

- Privacy
  - As such no bank APIs will ever be implemented. At this point only a stock price API is being considered for when I implement investment accounts.
  - I personally enjoy manually inputting each transaction.


Nothing in this is concrete, database schema will be changed actively and you will lose all data in it. When it's in a good state, database migration will be paramount.

## Users

Users currently do not have passwords (this will be added in the future, after security is implemented).

### Endpoints

`GET /api/user/{id}`

Returns the user object of the specified id value

Status Codes: `OK: 200`, `Not Found: 404`

`POST /api/user`

Creates a new user and returns the user object

Status Codes: `Created: 201`, `Conflict: 409`

`PATCH /api/user/{id}`

Updates all values of the user at the specified id value

Status Codes: `OK: 200`, `Not Found: 404`

`DELETE /api/user/{id}`

Deletes the user at the specified user id

Status Codes: `No Content: 204`

## Accounts

Accounts will function as they do in the real world.

#### Account Types
- 1 : Checking Account
- 2 : Savings Account
- 3 : Credit Card Account

## Transactions

Largest possible value for a transaction is 999999999999999.9999. BigInteger with precision of 19 and scale of 4.

#### Transaction Types
- 1 : Expense
- 2 : Deposit

These are the default ones, custom ones can be added in the future. Transaction Type names are unique, two ids cannot
share the same name.

#### Transaction Categories

These are the default ones, custom ones can be added in the future.

## Vendors

Multiple vendors cannot have the same name. The initial one must be renamed before another one can have the same name. I can adjust this in the future if a good use case is given.
No default Vendors exist, they must be created before transactions can be added.