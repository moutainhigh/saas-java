# Version 0.0.1
FROM java:8

MAINTAINER zenmind "41877287@qq.com"

# 环境变量
ENV INIT_PATH /commonPrj/init
ENV DATA_PATH /commonPrj/data
ENV FONT_PATH /usr/lib/jvm/java-8-openjdk-amd64/jre/lib/fonts/fallback
ENV APP_NAME @project.build.finalName@.@project.packaging@
ENV APP_VERSION @project.version@

#RUN
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone
RUN mkdir -p $INIT_PATH
RUN mkdir -p $DATA_PATH
RUN mkdir -p $FONT_PATH

#VOLUME
#VOLUME ["/commonPrj/init", "/commonPrj/data"]

#COPY
COPY $APP_NAME /commonPrj/init/$APP_NAME
COPY setup.sh /commonPrj/init/setup.sh
COPY ./libs/* /commonPrj/init/libs/

# CMD
CMD ["sh", "/commonPrj/init/setup.sh"]