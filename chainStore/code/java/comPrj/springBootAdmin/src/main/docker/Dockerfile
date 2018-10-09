# Version 0.0.1
FROM java:8

MAINTAINER zenmind "41877287@qq.com"

# 环境变量
ENV WORK_PATH /home/project/hqDocker
ENV INIT_PATH /@project.artifactId@/init
ENV APP_NAME @project.build.finalName@.@project.packaging@
ENV APP_VERSION @project.version@

EXPOSE 8080

#RUN
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone
RUN mkdir -p $WORK_PATH/logs
RUN mkdir -p $INIT_PATH

#VOLUME
VOLUME ["/@project.artifactId@/init", "/home/project/hqDocker/logs"]

#COPY
COPY $APP_NAME $WORK_PATH/
COPY setup.sh $INIT_PATH/setup.sh

WORKDIR $WORK_PATH
# CMD
CMD ["sh", "/@project.artifactId@/init/setup.sh"]