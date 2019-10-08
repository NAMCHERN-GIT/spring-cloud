# MySQL
```shell script
docker run -p 3306:3306 --name mysql-db -v /docker/volume/mysql/conf:/etc/mysql/conf.d -v /docker/volume/mysql/logs:/logs -v /docker/volume/mysql/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=123456 -d mysql:5.7 --lower_case_table_names=1 --sql_mode=STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION
```

# Redis
```shell script
docker run --name third-redis -v /docker/volume/redis/data:/data -p 6379:6379  -d redis redis-server --appendonly yes
```

# RabbitMQ
```shell script
docker run -d -p 5672:5672 -p 15672:15672 --name rabbitmq rabbitmq:management
```