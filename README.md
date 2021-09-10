This Springboot application displays the total number of Coronavirus
cases across the world from John Hopkins Covid-19 tracker.

use "mvn clean install" to generate the .jar file which can be 
exposed via a Docker Container. 

The Docker image has also been pushed to Docker Hub registry
shiladityaiitm/corona-tracker, so that it can used for Kubernetes
Deployment as well.

The URL can be accessed via http://<hostip>:[PORT]

PORT is 30001 for K8
PORT is 8558 for Docker 
