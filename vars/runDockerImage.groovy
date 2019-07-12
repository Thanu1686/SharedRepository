def call(def vmPort, def containerPort, def applicationName ,def imageName, def tag, def lastSuccessfullBuild){
  //sh " docker rm -f ${applicationName}-${lastSuccessfullBuild}"
  sh " docker run -d -p ${vmPort}:${containerPort} --name=${applicationName}-${tag} ${imageName}:${tag}"
}
