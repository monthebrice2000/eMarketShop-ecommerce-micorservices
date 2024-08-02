pipeline{
    agent any
    environment {
        imageName 'emarketshop'
        PROJECT_VERSION '0.0.1'
    }
    stages{
        stage('Checkout'){
            steps{
                checkout scm
                echo 'Successfully checkout'
            }
        }

        stage('Pre-integration Tests'){
            parrallel {
                stage('Quality Tests') {
                    steps {
                        cd './api-gateway'
                        sh "docker build -t ${env.imageName}/api-gateway-test ."
                        sh "docker build -f Dockerfile.test --build-arg="PROJECT_VERSION=${env.PROJECT_VERSION}" -t ${env.imageName}/api-gateway-test:${env.PROJECT_VERSION} ."
                        sh "docker run --rm -v ${PWD}/reports:/app/target ${env.imageName}/api-gateway-test:${env.PROJECT_VERSION} /bin/sh -c 'cd /app && ./mvnw checkstyle:check pmd:check'"
                        // junit "**/target/pmd.xml, **/target/checkstyle-*.xml"
                        publishCheckstyle failedTotalHigh: 1, healthy: 100, thresholdLimit: 'high', tool: checkstyle(pattern: '**/reports/checkstyle-result.xml')
                        
                        publishPmd failedTotalHigh: 1, healthy: 100, thresholdLimit: 'high', tool: pmd(pattern: '**/reports/pmd.xml')
                    }

                }
                stage('Unit Tests') {
                    steps{
                        cd './api-gateway'
                        sh "docker build -t ${env.imageName}/api-gateway-test ."
                        sh "docker build -f Dockerfile.test --build-arg="PROJECT_VERSION=${env.PROJECT_VERSION}" -t ${env.imageName}/api-gateway-test:${env.PROJECT_VERSION} ."
                        sh "docker run --rm -v ${PWD}/reports/tests:/app/target/surefire-reports ${env.imageName}/api-gateway-test:${env.PROJECT_VERSION} cd /app && ./mvnw -Dcheckstyle.skip -Dpmd.skip test"
                        junit "**/target/surefire-reports/*.xml"
                    }
                }
                stage('Security Tests'){
                    steps{
                        cd './api-gateway'
                        sh "docker build -t ${env.imageName}/api-gateway-test ."
                        sh "docker build -f Dockerfile.test --build-arg="PROJECT_VERSION=${env.PROJECT_VERSION}" -t ${env.imageName}/api-gateway-test:${env.PROJECT_VERSION} ."
                        sh "docker run --rm -v ${PWD}/reports:/app/target ${env.imageName}/api-gateway-test:${env.PROJECT_VERSION} /bin/sh -c 'trivy fs /app > /app/target/trivy-results.txt'"
                        junit "**/reports/trivy-results.txt"
                    }
                }
                stage('Code coverage') {
                    steps{
                        cd './api-gateway'
                        sh "docker build -t ${env.imageName}/api-gateway-test ."
                        sh "docker build -f Dockerfile.test --build-arg="PROJECT_VERSION=${env.PROJECT_VERSION}" -t ${env.imageName}/api-gateway-test:${env.PROJECT_VERSION} ."
                        sh "docker run --rm -v ${PWD}/reports/jacoco:/app/target/site/jacoco ${env.imageName}/api-gateway-test:${env.PROJECT_VERSION} /bin/sh -c 'cd /app && ( ./mvnw -Dcheckstyle.skip -Dpmd.skip test || ./mvnw jacoco:report)'"
                        // Archive the JaCoCo report
                        publishHTML(target: [
                            reportDir: '**/reports/jacoco',
                            reportFiles: 'index.html',
                            keepAll: true,
                            alwaysLinkToLastBuild: true,
                            allowMissing: false,
                            reportName: 'Coverage Reports'
                        ])
                        archiveArtifacts artifacts: '**/reports/jacoco/*.xml', allowEmptyArchive: true
                    }
                }
            }
        }

        stage('Sonarqube Analysis'){
            steps{
                script{
                    def mvn= tool 'M2_HOME'
                    withSonarQubeEnv() {
                        sh "cd ./api-gateway && ${mvn}/bin/mvn clean verify sonar:sonar -Dcheckstyle.skip -Dpmd.skip -DskipTests -Dsonar.projectKey=api-gateway -Dsonar.projectName=api-gateway -Dsonar.host.url=http://4.210.225.38:9000"
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