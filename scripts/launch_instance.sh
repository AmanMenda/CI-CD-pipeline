# If jenkins-container already exists, remove it and delete dangling images
docker ps -q --filter "name=jenkins-container" | grep -q . && docker stop jenkins-container
docker image prune -a

docker build -t jenkins-image:jcasc -f Jenkins/image/Dockerfile .

# Ensure that environment variables are correctly loaded
# by checking them inside the container using `printenv`.
# It's recommended to prefix the variables with `JENKINS_`
# to facilitate filtering with `grep`.

# TODO: Give the option to run in detached head
docker run --rm \
    --name jenkins-container \
    --env-file .env \
    -p 8081:8080 \
    -v /var/run/docker.sock:/var/run/docker.sock \
    -u root --privileged=true \
    jenkins-image:jcasc