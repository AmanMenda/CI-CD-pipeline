# syntax=docker/dockerfile:1

# Can be used to build a jenkins image with the necessary
# plugins and use a pre-defined configuration file to set
# up the Jenkins instance. A job DSL have been provided 
# to make it easier for me to reproduce the same instance.

FROM jenkins/jenkins:lts

USER root

# GCC is installed here because it is needed by cargo to
# build the rust sample project
RUN apt-get update && apt-get install -y gcc

USER jenkins

ENV JAVA_OPTS="-Djenkins.install.runSetupWizard=false"
ENV CASC_JENKINS_CONFIG=/var/jenkins_home/casc.yaml

COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
COPY casc.yaml /var/jenkins_home/casc.yaml
COPY scheduledBuildAndTest.groovy /var/jenkins_home/scheduledBuildAndTest.groovy

RUN jenkins-plugin-cli -f /usr/share/jenkins/ref/plugins.txt
