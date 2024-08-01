pipeline{
    agent any

    stages{
        stage('Checkout'){
            steps{
                checkout scm
                echo 'Successfully checkout'
            }
        }
        stage('Code Analysis'){
            steps{
                sh 'ls -al'
                echo 'Successfully Analyse code'
            }
        }
    }
}
