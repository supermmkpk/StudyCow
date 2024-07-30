pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Build') {
            steps {
                sh 'docker build -t myapp:${GIT_COMMIT} .'
            }
        }
        
        stage('Deploy') {
            steps {
                sh 'docker stop myapp || true'
                sh 'docker rm myapp || true'
                sh 'docker run -d --name myapp -p 8082:8080 myapp:${GIT_COMMIT}'
            }
        }
    }
}
