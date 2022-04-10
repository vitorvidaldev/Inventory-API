# Inventory API

API used for inventory management, developed with:
- Kotlin
- Spring
- PostgresSQL
- Docker
- Docker-Compose

## Features

### User Features
- Create new user
- User login
- User authorization
- User authentication

### Product Features
- Add new product
- Retrieve product list
- Retrieve data from a single product
- Remove product from system
- Remove product units

### Sales Features
- Retrieve list of sold products
- Calculate total amount
- Allow time based filters

### Report Features
- Print monthly report in pdf

## How do I execute the application?

To set up the database, run:

```
docker-compose up -d --build
```

Download the project dependencies with the following command:

```
mvn clean install
```

Execute the following command to run the application:

```
mvn spring-boot:run
```

Execute the application tests with the following command:

```
mvn test
```

## Author

Vitor Vidal - More about me [here](https://github.com/vitorvidaldev).

## License

This project uses [the MIT license](LICENSE).