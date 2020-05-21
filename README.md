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
  {"userId":null,"name":"user1", "token":"ey..."} <- copy token
  ```

  - request with authorization

  ```shell-script
  $ curl -XPATCH -H "Authorization: Bearer ..."(... is encoded token copied the previous step) -H "Content-type: application/json" -d '{"name": "user2","password": "password2"}' 'http://localhost:8080/services/v1/user/1'
  ```

  - request auth user information (Example of using @AuthenticationPrincipal)
  
  ```shell script
  curl -XGET -H "Authorization: Bearer ey..." 'http://localhost:8080/auth'
  {"userId":2,"name":"user1","createdAt":"2020-05-17T13:04:30.88","updatedAt":"2020-05-17T13:04:30.88","lockVersion":0,"deleteFlag":false}
  ```
