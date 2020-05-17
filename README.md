# SpringBoot-RestAPI-with-JWT

The Example of Rest API with SpringBoot + SpringSecurity + JWT(Json Web Token)

## Usage

  - start up web app

  ```shell-script
  $ docker-compose up -d
  $ ./mvnw sprint-boot:run
  ```

  - authorize

  ```shell-script
  $ curl -v -X POST -d '{"name": "user1", "password": "password"}' "http://localhost:8080/login"
  .
  .
  .
  < Authorization: Bearer ...      <- copy
  .
  .
  .
  ```

  - request with authorization

  ```shell-script
  $ curl -XPATCH -H "Authorization: Bearer ..."(... is encoded token copied the previous step) -H "Content-type: application/json" -d '{"name": "user2","password": "password2"}' 'http://localhost:8080/services/v1/user/1'
  ```
