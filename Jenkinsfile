pipeline {
    agent any
    
    environment {
        DOCKER_NETWORK = "studycow_network"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Backend - Build') {
            steps {
                dir('backend') {
                    sh 'chmod +x ./gradlew'
                    sh './gradlew clean bootJar'
                    sh 'docker build -t backend:${BUILD_NUMBER} .'
                }
            }
        }
        
stage('Frontend - Build and Deploy') {
    steps {
        dir('frontend') {
            sh 'npm install'
            sh 'npm run build'
            sh 'docker build -t frontend:${BUILD_NUMBER} .'
            sh 'docker stop frontend || true'
            sh 'docker rm frontend || true'
            sh 'docker run -d --name frontend --network studycow_network frontend:${BUILD_NUMBER}'
        }
    }
}
        
        stage('Backend - Deploy') {
            steps {
                script {
                    sh "docker network create ${DOCKER_NETWORK} || true"
                    
                    sh 'docker stop backend || true'
                    sh 'docker rm backend || true'
                    sh "docker run -d --name backend --network ${DOCKER_NETWORK} -p 8443:8443 backend:${BUILD_NUMBER}"
                    

                }
            }
        }
        
        stage('Health Check') {
            steps {
                script {
                    sh 'sleep 3'
                    sh 'curl https://13.125.238.202:8443 || echo "Backend health check failed"'
                    sh 'curl http://localhost:8080 || echo "local test"'
                }
            }
        }
    }
    
    post {
        always {
            sh 'docker logs backend || echo "No backend logs available"'
            sh 'docker logs frontend || echo "No frontend logs available"'
        }
        failure {
            sh 'docker stop backend frontend || true'
            sh 'docker rm backend frontend || true'
        }
        cleanup {
            cleanWs()
        }
    }
}
