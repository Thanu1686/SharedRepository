def call(def registry, def dockerCredential ,def imageName){
  docker.withRegistry('https://registry.hub.docker.com', "${dockerCredential}") {
    
    echo "${dockerCredential}"
 
    //build docker image
    image = docker.build("${imageName}:${BUILD_NUMBER}")
    
    //push image to hub
    image.push()
    
   }
}
