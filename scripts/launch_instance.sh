docker build -t jenkins-image:jcasc -f Jenkins/image/Dockerfile Jenkins/image/

# Ensure that environment variables are correctly loaded
# by checking them inside the container using `printenv`.
# It's recommended to prefix the variables with `JENKINS_`
# to facilitate filtering with `grep`.

docker run --rm \
    --name jenkins-container \
    --env-file .env \
    -p 8081:8080 -d \
    jenkins-image:jcasc
