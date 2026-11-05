
pipeline {
    agent any

    environment {
        ANDROID_HOME = '/opt/android-sdk'
        JAVA_HOME = '/usr/lib/jvm/java-17-openjdk-amd64'
        PATH = "${JAVA_HOME}/bin:${PATH}"
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out source code...'
                // checkout scm
            }
        }

        stage('Check Java') {
            steps {
                sh '''
                    echo "JAVA_HOME: $JAVA_HOME"
                    java -version
                    which java
                '''
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
