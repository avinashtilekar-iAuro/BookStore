
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
                script {
                    def javaPath = sh(
                            script: 'dirname $(dirname $(readlink -f $(which java)))',
                            returnStdout: true
                    ).trim()

                    env.JAVA_HOME = javaPath
                    env.PATH = "${javaPath}/bin:${env.PATH}"
                }

                sh '''
                    echo "JAVA_HOME=$JAVA_HOME"
                    java -version
                    test -x "$JAVA_HOME/bin/java"
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
