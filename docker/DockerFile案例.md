# 1.Base镜像(scratch)
```text
Docker Hub 中99%的镜像都是通过在base镜像中安装和配置需要的软件中构建出来的。
```


# 2.案例1
## 2.1 mycentos
```dockerfile
from centos
MAINTAINER chen.nan<namchern@aliyun.com>

ENV MYPATH /usr/local
WORKDIR $MYPATH

RUN yum -y install vim
RUN yum -y install net-tools

EXPOSE 80
CMD echo $MYPATH
CMD echo "success----------------------------ok"
CMD /bin/bash
```
构建
```shell script
# -f 指定dockerfile文件
# -t 生成镜像的名称版本号
# 最后面一个点表示生成在当前路径下
docker build -f /docker_case/mycentos_dockerfile -t mycentos:0.1 .
```

## 踩到的坑
```text
Step 5/10 : RUN yum -y install vim
 ---> [Warning] IPv4 forwarding is disabled. Networking will not work.
 ---> Running in 5bd631629d98
Loaded plugins: fastestmirror, ovl
Determining fastest mirrors


 One of the configured repositories failed (Unknown),
 and yum doesn't have enough cached data to continue. At this point the only
 safe thing yum can do is fail. There are a few ways to work "fix" this:

     1. Contact the upstream for the repository and get them to fix the problem.

     2. Reconfigure the baseurl/etc. for the repository, to point to a working
        upstream. This is most often useful if you are using a newer
        distribution release than is supported by the repository (and the
        packages for the previous distribution release still work).

     3. Run the command with the repository temporarily disabled
            yum --disablerepo=<repoid> ...

     4. Disable the repository permanently, so yum won't use it by default. Yum
        will then just ignore the repository until you permanently enable it
        again or use --enablerepo for temporary usage:

            yum-config-manager --disable <repoid>
        or
            subscription-manager repos --disable=<repoid>

     5. Configure the failing repository to be skipped, if it is unavailable.
        Note that yum will try to contact the repo. when it runs most commands,
        so will have to try and fail each time (and thus. yum will be be much
        slower). If it is a very temporary problem though, this is often a nice
        compromise:

            yum-config-manager --save --setopt=<repoid>.skip_if_unavailable=true

Cannot find a valid baseurl for repo: base/7/x86_64
Could not retrieve mirrorlist http://mirrorlist.centos.org/?release=7&arch=x86_64&repo=os&infra=container error was
14: curl#6 - "Could not resolve host: mirrorlist.centos.org; Unknown error"
The command '/bin/sh -c yum -y install vim' returned a non-zero code: 1
```
## 解决方法
```shell script
echo net.ipv4.ip_forward=1 >> /usr/lib/sysctl.d/00-system.conf
systemctl restart network
systemctl restart docker
```

# 3.案例2 myIp

curl命令可以用来执行下载，发送各种HTTP请求，指定HTTP头部等操作。
如果系统没有curl可以使用yum install -y curl命令进行安装，也可以下载安装。
curl是将下载文件输出到stdout

使用命令下面的命令后，[www.baidu.com](http://www.baidu.com)的html就会显示在屏幕上了。
```shell script
curl http://www.baidu.com
```
这是最简单的使用方法,用这个命令获得了[http://curl.haxx.se](http://curl.haxx.se)指向的页面，同样，如果这里的url指向的是一个文件或者一幅图都可以直接下载、
到本地。如果下载的是html文档，那么缺省的将只显示文件头部，即HTML文档的header。要全部显示，请加参数-i

### dockerFile文件内容
```dockerfile
FROM centos
RUN yum install -y curl
CMD ["curl","-s","https://www.ip.cn"]
```

### 生成docker images
```shell script
docker build -f /docker_case/case2_dockerfile -t myip .
```

### 查看运行结果
```shell script
docker run -it myip
```
控制台结果：
```text
{"ip": "140.207.236.69", "country": "上海市", "city": "联通"}
```

#### 问题：如果要显示http头部信息，则GG
```shell script
docker run -it myip -i
```
```text
docker: Error response from daemon: OCI runtime create failed: container_linux.go:345: starting container process caused "exec: \"-i\": executable file not found in $PATH": unknown.
```
#### 解决方案：使用 【ENTRYPOINT】 指令关键字取代 【CMD】，dockerfile内容如下
```shell script
FROM centos
RUN yum install -y curl
ENTRYPOINT ["curl","-s","https://www.ip.cn"
```
 
# 4.案例3：自定义tomcat9
### dockerfile内容
```dockerfile
FROM centos
MAINTAINER chen.nan<namchern@aliyun.com>
# 把宿主机当前上下文的c.txt拷贝到容器/usr/local/路径下
COPY c.txt /usr/local/cincontainer.txt
# 把java和tomcat添加到容器中
ADD jdk-8u191-linux-x64.tar.gz /usr/local/
ADD apache-tomcat-8.5.45.tar.gz /usr/local/
# 安装vim编辑器
RUN yum -y install vim
# 设置工作访问时候的workdir路径，登录落脚点
ENV MYPATH /usr/local
WORKDIR $MYPATH
# 配置java与tomcat环境变量
ENV JAVA_HOME /usr/local/jdk1.8.0_191
ENV CLASSPATH .:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
ENV CATALINA_HOME /usr/local/apache-tomcat-8.5.45
ENV CATALINA_BASE /usr/local/apache-tomcat-8.5.45
ENV PATH $PATH:$JAVA_HOME/bin:$CATALINA_HOME/lib:$CATALINA_HOME/bin
# 容器运行时监听的端口
EXPOSE 8080
# 启动时运行tomcat
# ENTRYPOINT ["/usr/local/apache-tomcat-8.5.45/bin/startup.sh"]
# CMD ["/usr/local/apache-tomcat-8.5.45/bin/catalina.sh","run"]
CMD /usr/local/apache-tomcat-8.5.45/bin/startup.sh && tail -f /usr/local/apache-tomcat-8.5.45/logs/catalina.out

```
 