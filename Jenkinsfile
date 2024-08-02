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
                        sh "cd ./api-gateway && ${mvn}/bin/mvn clean verify sonar:sonar -Dcheckstyle.skip -Dpmd.skip -DskipTests -Dsonar.projectKey=api-gateway -Dsonar.projectName=api-gateway -Dsonar.host.url=http://4.210.225.38:9000"
                        // sh "${scannerHome}/bin/sonar-scanner" // -Dsonar.host.url=http://20.55.59.215:9000 -Dsonar.login=adminn -Dsonar.password=adminn"
                    }
                }
                echo 'Successfully Analyse code'
            }
        }

        stage("Quality Gate"){
            steps{
                script{
                    timeout(time: 5, unit: 'MINUTES') {
                        def qg = waitForQualityGate()
                        if (qg.status != 'OK') {
                            error "Pipeline aborted due to quality gate failure: ${qg.status}"
                        }
                    }
                    echo 'Quality Gate is OK'
                }
            }
        }
    }
}