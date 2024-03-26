# Personal Finance

This is a personal project I am creating for my own finances. It initially was written in Rust using a PostgreSQL database. However, I have since moved over to using Java/JPA and MariaDB.

The following endpoints have successfully been tested and implemented:

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