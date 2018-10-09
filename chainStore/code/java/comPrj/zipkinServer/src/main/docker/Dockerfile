# Version 0.0.1
FROM java:8

MAINTAINER zenmind "41877287@qq.com"

# 环境变量
ENV WORK_PATH /home/project/hqDocker
ENV INIT_PATH /@project.artifactId@/init
ENV APP_NAME @project.build.finalName@.@project.packaging@
ENV APP_VERSION @project.version@

ENV env.prd.active true
ENV env.eureka.active true
ENV env.profiles.active prd
ENV env.eureka.address http://192.168.40.220:9111/eureka/
ENV env.rabbitMqHost 192.168.40.220
ENV env.mysqlHost 192.168.40.220
ENV env.elasticsearchHost 192.168.40.220

EXPOSE 9411

#USER
#USER user:group

#RUN
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone
RUN mkdir -p $WORK_PATH/logs
RUN mkdir -p $INIT_PATH

#VOLUME
VOLUME ["/@project.artifactId@/init", "/home/project/hqDocker/logs"]

#COPY
#COPY $APP_NAME $WORK_PATH/
#COPY setup.sh $INIT_PATH/setup.sh
#COPY ./libs/* $WORK_PATH/libs/

WORKDIR $WORK_PATH
# CMD
#CMD ["java", "-jar", "@project.build.finalName@.@project.packaging@"]
CMD ["sh", "/@project.artifactId@/init/setup.sh"]