@Library('project_devops')_

node(label: 'master'){
    //Variables
    def gitURL = "https://github.com/Thanu1686/Jenkins2.git"
    def repoBranch = "master"
    def applicationName = "atmosphere"
    def sonarqubeServer = "sonar"
    def sonarqubeGoal = "clean verify sonar:sonar"
    def mvnHome = "MAVEN_HOME"
    def pom = "pom.xml"
    def goal = "clean install"
    def lastSuccessfulBuildID = 0
    def project_path = "spring-boot-samples/spring-boot-sample-atmosphere"
    def artifactoryServer = "Artifactory"
    def releaseRepo = "generic-local"
    def snapshotRepo = "generic-snapshot"
  
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
    dir(project_path){
        //Sonarqube Analysis
        stage('Sonarqube-scan'){
            sonarqubeScan "${mvnHome}","${sonarqubeGoal}","${pom}", "${sonarqubeServer}"
        }

        //Quality-gate
        stage('Quality-Gate'){
            qualityGate "${sonarqubeServer}"
        }

        //MVN Build
        stage('Maven Build and Push to Artifactory'){
            mavenBuild "${artifactoryServer}","${mvnHome}","${pom}", "${goal}", "${releaseRepo}", "${snapshotRepo}"
        }
   }    
}
