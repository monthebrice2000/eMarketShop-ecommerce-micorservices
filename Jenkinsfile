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
                    def scannerHome= tool 'sonar-scanner'
                    withSonarQubeEnv() {
                        sh "${scannerHome}/bin/sonar-scanner" // -Dsonar.host.url=http://20.55.59.215:9000 -Dsonar.login=adminn -Dsonar.password=adminn"
                    }
                }
                    
                echo 'Successfully Analyse code'
            }
        }
    }
}