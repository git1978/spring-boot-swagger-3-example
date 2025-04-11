# Swagger 3 and Spring Boot example (with OpenAPI 3)

Document REST API with Swagger 3 in Spring Boot example (follow OpenAPI 3 specification). You will also know several ways to configure Swagger API description and response.

## Add lombok

```
download  le jar lomobk
add in eclipse.ini
-javaagent:/Users/younessabach/Documents/dev/java/mylibs/lombok.jar
```

## Run Spring Boot application

```
mvn spring-boot:run
```

## Create image docker

```
docker build -t image-sp317 .

docker run -p 8080:8080 image-sp317

docker images


brew install docker-buildx
```

## kill port

```
kill -9 <PORT>

```

# create ressources azure

chmod +x create-ressources.sh
./create-ressources.sh

# Version of azure-cli, helm, kubectl

echo -e "Azure CLI: $(az version --output tsv | head -n 1)" &&
echo -e "Helm: $(helm version --short)" &&
echo -e "kubectl: $(kubectl version --client --output=json | jq -r '.clientVersion.gitVersion')" | column -t

ARCH=amd64 # change to 'arm64' if you have M1 chip
VERSION=v0.8.2
curl -LO https://github.com/docker/buildx/releases/download/${VERSION}/buildx-${VERSION}.darwin-${ARCH}
mkdir -p ~/.docker/cli-plugins
mv buildx-${VERSION}.darwin-${ARCH} ~/.docker/cli-plugins/docker-buildx
chmod +x ~/.docker/cli-plugins/docker-buildx
docker buildx version # verify installation
