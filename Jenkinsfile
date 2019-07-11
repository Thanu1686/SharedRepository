libraries {
  lib('project_devops@master')
}

node(label: 'master'){
    //Variables
    def gitURL = "https://github.com/AbhishekSuvarna/game-of-life.git "
    def repoBranch = "master"
    def applicationName = "game-of-life"
    def sonarqubeServer = "sonar"
    def sonarqubeGoal = "clean verify sonar:sonar"
    def mvnHome = "MAVEN_HOME"
    def pom = "pom.xml"
    def goal = "clean install"
    def lastSuccessfulBuildID = 0
    
    //Check for Previous-Successful-Build
    stage('Get Last Successful Build Number'){
        def build = currentBuild.previousBuild
        while (build != null) {
            if (build.result == "SUCCESS")
            {
                lastSuccessfulBuildID = build.id as Integer
                break
            }
            build = build.previousBuild
        }
        echo "${lastSuccessfulBuildID}"
    }
    //Git Stage
    stage('Git-Checkout'){
        gitClone "${gitURL}","${repoBranch}"    
    }
    
    //Sonarqube Analysis
    stage('Sonarqube-scan'){
        sonarqubeScan "${mvnHome}","${sonarqubeGoal}","${pom}", "${sonarqubeServer}"
    }
       
}
