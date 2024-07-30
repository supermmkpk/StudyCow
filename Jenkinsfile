pipeline {
    agent any

    tools {
        gradle 'Gradle'
        nodejs 'Node'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Backend') {
            steps {
                dir('backend') {
                    sh 'gradle clean build'
                }
            }
        }

        stage('Build Frontend') {
            steps {
                dir('studycow') {
                    sh 'npm install'
                    sh 'npm run build'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build('your-app-image:latest', '-f Dockerfile .')
                }
            }
        }

        stage('Deploy') {
            steps {
                sshagent(['your-ssh-credentials']) {
                    sh '''
                        ssh user@your-server "docker stop my-running-app || true"
                        ssh user@your-server "docker rm my-running-app || true"
                        ssh user@your-server "docker rmi your-app-image:latest || true"
                        docker save -o your-app-image.tar your-app-image:latest
                        scp your-app-image.tar user@your-server:/tmp/
                        ssh user@your-server "docker load -i /tmp/your-app-image.tar"
                        ssh user@your-server "docker run -d --name my-running-app -p 8080:8080 your-app-image:latest"
                    '''
                }
            }
        }
    }

    post {
        success {
            echo 'CI/CD pipeline executed successfully!'
        }
        failure {
            echo 'CI/CD pipeline failed. Please check the logs for details.'
        }
    }
}
