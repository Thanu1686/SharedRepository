def call(def dockerImageName, def tagNumber){
  sh "docker pull ${dockerImageName}:${tagNumber}"
}
