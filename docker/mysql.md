# 拉取MySQL5.7的镜像
```shell script
docker pull mysql:5.7
```
# 创建并启动MySQL容器
```shell script
docker run -p 3306:3306 --name mysql-db -v /docker/volume/mysql/conf:/etc/mysql/conf.d -v /docker/volume/mysql/logs:/logs -v /docker/volume/mysql/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=123456 -d mysql:5.7 --lower_case_table_names=1
```

# 查看正在运行容器
```shell script
docker ps
```
#### 如图
![avatar](img/docker_ps_mysql.jpg)

# 停止MySQL
```shell script
docker stop 72c498664c3c
```

# 再次启动MySQL
```shell script
docker start 72c498664c3c
```