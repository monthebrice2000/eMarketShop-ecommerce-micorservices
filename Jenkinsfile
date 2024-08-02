pipeline{
    agent any

    stages{
        stage('Checkout'){
            steps{
                checkout scm
                echo 'Successfully checkout'
            }
        }
        stage('Sonarqube Analysis'){
            steps{
                script{
                    def mvn= tool 'M2_HOME'
                    withSonarQubeEnv() {
                        sh "${mvn}/bin/mvn clean verify sonar:sonar -Dsonar.projectKey=api-gateway -Dsonar.projectName='api-gateway"
                        // sh "${scannerHome}/bin/sonar-scanner" // -Dsonar.host.url=http://20.55.59.215:9000 -Dsonar.login=adminn -Dsonar.password=adminn"
                    }
                }
                echo 'Successfully Analyse code'
            }
        }
    }
}