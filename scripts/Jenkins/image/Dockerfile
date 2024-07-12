# syntax=docker/dockerfile:1

# Can be used to build a jenkins image with the necessary
# plugins and use a pre-defined configuration file to set
# up the Jenkins instance. A job DSL have been provided 
# to make it easier for me to reproduce the same instance.

FROM jenkins/jenkins:lts

USER root

# Install docker in container to be able to
RUN apt-get -y update; apt-get install -y sudo; apt-get install -y git wget
RUN echo "Jenkins ALL=NOPASSWD: ALL" >> /etc/sudoers
RUN wget http://get.docker.com/builds/Linux/x86_64/docker-latest.tgz
RUN tar -xvzf docker-latest.tgz
RUN cp -r docker/* /usr/bin/

# GCC is installed here because it is needed by cargo to
# build the rust sample project
RUN apt-get update && apt-get install -y gcc

USER jenkins

ENV JAVA_OPTS="-Djenkins.install.runSetupWizard=false"
ENV CASC_JENKINS_CONFIG=/var/jenkins_home/casc.yaml

COPY Jenkins/image/plugins.txt /usr/share/jenkins/ref/plugins.txt
COPY Jenkins/image/casc.yaml /var/jenkins_home/casc.yaml
COPY Jenkins/image/scheduledBuildAndTest.groovy /var/jenkins_home/scheduledBuildAndTest.groovy
COPY Jenkins/image/deploy.groovy /var/jenkins_home/deploy.groovy
COPY Docker/ /custom_images

RUN jenkins-plugin-cli -f /usr/share/jenkins/ref/plugins.txt
