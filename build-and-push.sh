#!/bin/bash

# Définir les variables
IMAGE_NAME="cloud-api-javas3"
IMAGE_TAG="latest"
DOCKERHUB_USER="abachyouness"

# Construire l'image Docker
docker build -t ${IMAGE_NAME}:${IMAGE_TAG} .

# Taguer l'image pour Docker Hub
docker tag ${IMAGE_NAME}:${IMAGE_TAG} ${DOCKERHUB_USER}/${IMAGE_NAME}:${IMAGE_TAG}

# Se connecter à Docker Hub (si nécessaire)
docker login

# Pousser l'image vers Docker Hub
docker push ${DOCKERHUB_USER}/${IMAGE_NAME}:${IMAGE_TAG}

# docker build -t cloud-api-javas3:latest .
# docker tag cloud-api-javas3:latest abachyouness/api-sp3dk17:latest
# docker push abachyouness/api-sp3dk17:latest
