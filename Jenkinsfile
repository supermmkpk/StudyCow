pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Frontend Build') {
            steps {
                dir('studycow') {
                    nodejs(nodeJSInstallationName: 'NodeJS 18.x') {  // Jenkins에 설정된 Node.js 버전
                        sh 'npm ci'  // npm install 대신 ci를 사용하여 정확한 버전 설치
                        sh 'npm run build'
                    }
                }
            }
        }

        stage('Backend Build') {
            steps {
                dir('backend') {
                    // Java 프로젝트라고 가정
                    // Jenkins에 설정된 JDK 버전 사용
                    jdk('JDK 11') {  
                        // Gradle 프로젝트라고 가정
                        sh './gradlew clean build'

                        // Maven 프로젝트인 경우 아래 줄 사용
                        // sh 'mvn clean package'
                    }
                }
            }
        }

        stage('Archive Artifacts') {
            steps {
                // 프론트엔드 빌드 결과물 아카이브
                archiveArtifacts artifacts: 'frontend/build/**/*', fingerprint: true

                // 백엔드 빌드 결과물 아카이브 (JAR 파일이라고 가정)
                archiveArtifacts artifacts: 'backend/build/libs/*.jar', fingerprint: true
            }
        }

        // 여기에 추가적인 단계를 넣을 수 있습니다 (예: 테스트, 배포 등)
    }

    post {
        always {
            // 빌드 결과에 상관없이 항상 실행
            echo 'Build finished'
            // 작업 공간 정리 (선택사항)
            cleanWs()
        }
        success {
            echo 'Build succeeded'
            // 성공 시 알림 보내기 (예: 슬랙, 이메일 등)
        }
        failure {
            echo 'Build failed'
            // 실패 시 알림 보내기
        }
    }
}
