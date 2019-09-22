# MySQL
```shell script
docker run -p 3306:3306 --name mysql-db -v ~/mysql/conf:/etc/mysql/conf.d -v ~/mysql/logs:/logs -v ~/mysql/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=123456 -d mysql:5.7 --lower_case_table_names=1
```

# Redis
```shell script
docker run --name third-redis -v /root/redis/data:/data -p 6379:6379  -d redis redis-server --appendonly yes
```

# RabbitMQ
```shell script
docker run -d -p 5672:5672 -p 15672:15672 --name rabbitmq rabbitmq:management
```