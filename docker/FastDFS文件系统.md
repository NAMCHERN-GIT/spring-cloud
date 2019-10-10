# 通过Docker构建FastDFS文件系统

## 1.获取镜像
从docker镜像仓库中心拉取 FastDFS
```shell script
docker pull season/fastdfs
```

## 2.运行tracker
执行如下命令开启tracker 服务
```shell script
docker run -dti --name tracker -v /docker/volume/fastdfs/tracker:/var/fdfs season/fastdfs tracker
```
* 将fastDFS tracker运行目录映射到本机的 /docker/volume/fastdfs/tracker目录中  

## 3.运行storage
执行如下命令开启storage服务
```shell script
docker run -dti --name storage -e TRACKER_SERVER=192.168.232.151:22122 -v /docker/volume/fastdfs/storage:/var/fdfs season/fastdfs storage
```
* TRACKER_SERVER=本机的ip地址:22122 本机ip地址不要使用127.0.0.1
* 将fastDFS storage运行目录映射到本机的/docker/volume/fastdfs/storage目录中

