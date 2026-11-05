pipeline {
    agent {
        docker {
            image 'your-android-sdk-docker-image:latest'
            // or agent any if SDK/JDK are pre-installed on the Jenkins node
        }
    }
    environment {
        ANDROID_HOME = '/opt/android-sdk'
        JAVA_HOME = '/usr/lib/jvm/java-17-openjdk-amd64'
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build & Test') {
            steps {
                sh 'chmod +x gradlew'
                sh './gradlew assembleDebug testDebugUnitTest'
            }
        }
    }
    post {
        always {
            junit 'app/build/test-results/**/*.xml'
        }
    }
}
