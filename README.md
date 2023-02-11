# SSE_WebApp
This application was created for an assignment at FFHS (University of Applied Sciences).
The goal of this project was create a modular and maintainable web application in Java.

The application backend was built with Spring Boot. The backend API can be consumed by any frontend client.
The frontend was built with ReactJS and utilizes the Fullcalendar framework for rendering the consumed data.

## Scope
This application is a demonstration of fullstack development. The project covers the creation of an API, the consumption of the API and rendering
of the data by the frontend and the packaging of the backend and frontend in a single JAR file.
Topics like user creation, roles, security, cross origin, etc. are not covered and should be properly implemented in a production setting.

## API Specification
openapi: "3.0.3"

info:

title: "sse_backend API"

description: "sse_backend API"

version: "1.0.0"

servers:

- url: "https://sse_backend"

paths:

/api/user:

    get:

      summary: "GET api/user"

      operationId: "getUsers"

      responses:

        "200":

          description: "OK"

          content:

            '*/*':

              schema:

                type: "array"

    post:

      summary: "POST api/user"

      operationId: "saveUser"

      requestBody:

        content:

          application/json:

            schema:

              type: "object"

        required: true

      responses:

        "200":

          description: "OK"

          content:

            '*/*':

              schema:

                type: "string"

/api/user/mail/{mail}:

    get:

      summary: "GET api/user/mail/{mail}"

      operationId: "getUserByEmail"

      parameters:

        - name: "mail"

          in: "path"

          required: true

          schema:

            type: "string"

      responses:

        "200":

          description: "OK"

          content:

            '*/*':

              schema:

                type: "object"

/api/user/{id}:

    get:

      summary: "GET api/user/{id}"

      operationId: "getUserById"

      parameters:

        - name: "id"

          in: "path"

          required: true

          schema:

            type: "integer"

            format: "int64"

      responses:

        "200":

          description: "OK"

          content:

            '*/*':

              schema:

                type: "object"

    put:

      summary: "PUT api/user/{id}"

      operationId: "updateUser"

      parameters:

        - name: "id"

          in: "path"

          required: true

          schema:

            type: "integer"

            format: "int64"

      requestBody:

        content:

          application/json:

            schema:

              type: "object"

        required: true

      responses:

        "200":

          description: "OK"

          content:

            '*/*':

              schema:

                type: "string"

    delete:

      summary: "DELETE api/user/{id}"

      operationId: "deleteUser"

      parameters:

        - name: "id"

          in: "path"

          required: true

          schema:

            type: "integer"

            format: "int64"

      responses:

        "200":

          description: "OK"

          content:

            '*/*':

              schema:

                type: "string"

/api/event:

    get:

      summary: "GET api/event"

      operationId: "getEvents"

      responses:

        "200":

          description: "OK"

          content:

            '*/*':

              schema:

                type: "array"

    put:

      summary: "PUT api/event"

      operationId: "updateEvent"

      requestBody:

        content:

          application/json:

            schema:

              type: "object"

        required: true

      responses:

        "200":

          description: "OK"

          content:

            '*/*':

              schema:

                type: "string"

    post:

      summary: "POST api/event"

      operationId: "saveEvent"

      requestBody:

        content:

          application/json:

            schema:

              type: "object"

        required: true

      responses:

        "200":

          description: "OK"

          content:

            '*/*':

              schema:

                type: "object"

    delete:

      summary: "DELETE api/event"

      operationId: "deleteEvent"

      parameters:

        - name: "Id"

          in: "query"

          required: true

          schema:

            type: "integer"

            format: "int64"

      responses:

        "200":

          description: "OK"

          content:

            '*/*':

              schema:

                type: "string"

/api/event/{eventId}/user/{userId}:

    put:

      summary: "PUT api/event/{eventId}/user/{userId}"

      operationId: "assignUserToEvent"

      parameters:

        - name: "eventId"

          in: "path"

          required: true

          schema:

            type: "integer"

            format: "int64"

        - name: "userId"

          in: "path"

          required: true

          schema:

            type: "integer"

            format: "int64"

      responses:

        "200":

          description: "OK"

          content:

            '*/*':

              schema:

                type: "string"

/api/event/{userId}:

    get:

      summary: "GET api/event/{userId}"

      operationId: "getUserEvents"

      parameters:

        - name: "userId"

          in: "path"

          required: true

          schema:

            type: "integer"

            format: "int64"

      responses:

        "200":

          description: "OK"

          content:

            '*/*':

              schema:

                type: "array"

    post:

      summary: "POST api/event/{userId}"

      operationId: "saveUserEvent"

      parameters:

        - name: "userId"

          in: "path"

          required: true

          schema:

            type: "integer"

            format: "int64"

      requestBody:

        content:

          application/json:

            schema:

              type: "object"

        required: true

      responses:

        "200":

          description: "OK"

          content:

            '*/*':

              schema:

                type: "object"

    delete:

      summary: "DELETE api/event/{userId}"

      operationId: "deleteUserEvent"

      parameters:

        - name: "userId"

          in: "path"

          required: true

          schema:

            type: "integer"

            format: "int64"

      requestBody:

        content:

          application/json:

            schema:

              type: "object"

        required: true

      responses:

        "200":

          description: "OK"

          content:

            '*/*':

              schema:

                type: "string"

/login:

    post:

      summary: "POST login"

      operationId: "findUserByUsernameAndPassword"

      requestBody:

        content:

          application/json:

            schema:

              type: "object"

        required: true

      responses:

        "200":

          description: "OK"

          content:

            '*/*':

              schema:

                type: "object"

## Authors and acknowledgment
Ennis Aliu

Steven Meyer

## License
FFHS - Fernfachhochschule Schweiz AG