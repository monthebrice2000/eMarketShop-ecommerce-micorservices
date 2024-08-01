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
                    withSonarQubeEnv() {
                        sh "sonar-scanner -Dsonar.host.url=http://20.55.59.215/:9000 -Dsonar.login=admin -Dsonar.password=admin"
                    }
                }
                    
                echo 'Successfully Analyse code'
            }
        }
    }
}
