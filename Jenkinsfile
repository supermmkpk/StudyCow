pipeline {
    agent any

    tools {
        jdk 'JDK 17'  // Jenkins에 설정된 JDK 11 버전 이름과 일치해야 합니다.
        nodejs 'NodeJS' '20.15.1'  // Jenkins에 설정된 Node.js 버전 이름과 일치해야 합니다.
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Frontend Build') {
            steps {
                dir('studycow') {
                    sh 'npm ci'
                    sh 'npm run build'
                }
            }
        }

        stage('Backend Build') {
            steps {
                dir('backend') {
                    // Gradle 프로젝트라고 가정
                    sh './gradlew clean build'

                    // Maven 프로젝트인 경우 아래 줄 사용
                    // sh 'mvn clean package'
                }
            }
        }

        stage('Archive Artifacts') {
            steps {
                archiveArtifacts artifacts: 'studycow/build/**/*', fingerprint: true
                archiveArtifacts artifacts: 'backend/build/libs/*.jar', fingerprint: true
            }
        }
    }

    post {
        always {
            echo 'Build finished'
            cleanWs()
        }
        success {
            echo 'Build succeeded'
        }
        failure {
            echo 'Build failed'
        }
    }
}
