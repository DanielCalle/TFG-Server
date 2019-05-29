FROM ubuntu:16.04

# update
RUN apt-get update 
# config ssh
RUN apt-get install -y openssh-server
RUN mkdir /var/run/sshd
RUN echo 'root:tfg-ucm' | chpasswd
RUN sed -i 's/PermitRootLogin prohibit-password/PermitRootLogin yes/' /etc/ssh/sshd_config

# SSH login fix. Otherwise user is kicked off after login
RUN sed 's@session\s*required\s*pam_loginuid.so@session optional pam_loginuid.so@g' -i /etc/pam.d/sshd

# install maven 
RUN apt-get install -y maven
# install postgresql
RUN apt-get install -y postgresql
# install sudo
RUN apt-get install -y sudo
# install java jdk
RUN apt-get install -y openjdk-8-jdk

WORKDIR /usr/src/app
COPY . ./

ENV NOTVISIBLE "in users profile"
RUN echo "export VISIBLE=now" >> /etc/profile

EXPOSE 22 8000 8080
CMD ["/usr/sbin/sshd", "-D"]