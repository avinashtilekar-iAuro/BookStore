
pipeline {
    agent any

    environment {
        ANDROID_HOME = '/opt/android-sdk'
        JAVA_HOME = '/usr/lib/jvm/java-17-openjdk-amd64'
    }
    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out source code...'
                // checkout scm
            }
        }

        stage('Build') {
            steps {
                echo 'Building application...'
                sh './gradlew clean assembleDebug'
            }
        }

        stage('JUnit Unit Tests') {
            steps {
                echo 'Running JUnit test cases...'

                sh './gradlew testDebugUnitTest'
            }

            post {
                always {
                    echo 'Publishing JUnit test results...'

                    junit(
                            testResults: '**/build/test-results/testDebugUnitTest/*.xml',
                            allowEmptyResults: false
                    )
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline executed successfully!'
        }

        failure {
            echo 'Pipeline execution failed!'
        }

        always {
            echo 'Jenkins pipeline execution completed.'
        }
    }
}
