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
                sh 'docker build -t myapp .'
            }
        }
        stage('Deploy') {
            steps {
                sshagent(credentials: ['ec2-ssh-key']) {
                    sh '''
                        ssh -o StrictHostKeyChecking=no ec2-user@i11c202.p.ssafy.io"
                            docker pull myapp:latest
                            docker stop myapp || true
                            docker rm myapp || true
                            docker run -d --name myapp -p 80:80 myapp:latest
                        "
                    '''
                }
            }
        }
    }
}
