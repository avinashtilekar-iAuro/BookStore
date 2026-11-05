
pipeline {
    agent any

    environment {
        ANDROID_HOME = '/opt/android-sdk'
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out source code...'
            }
        }

        stage('Configure Java') {
            steps {
                sh '''
            export JAVA_HOME=/iauro/lib/jvm/java-17-openjdk-amd64
            export PATH=$JAVA_HOME/bin:$PATH

            echo "Checking Java..."
            java -version

            test -f "$JAVA_HOME/bin/java"
        '''
            }
        }

        stage('Build') {
            steps {
                sh './gradlew clean assembleDebug'
            }
        }

        stage('JUnit Unit Tests') {
            steps {
                sh './gradlew testDebugUnitTest'
            }

            post {
                always {
                    junit(
                            testResults: '**/build/test-results/testDebugUnitTest/*.xml',
                            allowEmptyResults: true
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
