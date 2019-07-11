def call(def sonarqubeServer){
  withSonarQubeEnv("${sonarqubeServer}") {
    timeout(time: 2, unit: 'MINUTES') { 
    sleep(10)
      def qg = waitForQualityGate() 
      if (qg.status != 'OK') {
        error "Pipeline aborted due to quality gate failure: ${qg.status}"
        currentBuild.status='FAILURE'
      }
    }
  }
}
