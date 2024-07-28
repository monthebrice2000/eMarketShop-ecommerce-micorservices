pipeline {
   agent "emarketshop-agent"

   environment {
      MVN_HOME = tool 'M2_HOME'
      DOCKER_REGISTRY = '819XXXXXX43.dkr.ecr.us-east-2.amazonaws.com'
      DOCKER_IMAGE_PREFIX = "${DOCKER_REGISTRY}/emarketshop"
      DOCKER_IMAGE_VERSION = '0.0.1'
      KUBECONFIG_ID = 'kubeconfig'
   }

   stages {
      stage('Build pakages') {
         steps {
            // Run the maven build
            sh 'mvn -Dmaven.test.failure.ignore clean package'
         }
      }
      stage('Archive Tests Results and Artifacts') {
         steps {
            junit '**/target/surefire-reports/TEST-*.xml'
            archiveArtifacts '**/target/*.jar'
         }
      }
      stage('Build images') {
         steps {
            sh './scripts/build-image.sh'
         }
      }
      stage('Push images to ECR') {
         steps {
            // script {
            //    docker.withRegistry("https://${DOCKER_REGISTRY}", 'ecr:us-east-2:ecr-user') {
            //             sh "docker push ${DOCKER_IMAGE_PREFIX}/configserver:${DOCKER_IMAGE_VERSION}"
            //    }
            // }

            sh './push-image-to-registry.sh'
         }
      }
      stage('Kubernetes deploy') {
         steps {
            sh 'kubectl view config'
         }
      }
   }
   post {
      always {
            cleanWs()
      }
   }
}
