
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
                    if [ ! -d "$JAVA_HOME" ]; then
                        echo "Configured JAVA_HOME ($JAVA_HOME) not found, attempting auto-detection..."
                        if [ -d "/usr/lib/jvm/java-17-openjdk-arm64" ]; then
                            export JAVA_HOME="/usr/lib/jvm/java-17-openjdk-arm64"
                        elif [ -d "/usr/lib/jvm/java-17-openjdk" ]; then
                            export JAVA_HOME="/usr/lib/jvm/java-17-openjdk"
                        elif command -v /usr/libexec/java_home >/dev/null 2>&1; then
                            export JAVA_HOME="$(/usr/libexec/java_home -v 17 2>/dev/null || /usr/libexec/java_home)"
                        elif command -v java >/dev/null 2>&1; then
                            export JAVA_HOME="$(dirname $(dirname $(readlink -f $(which java))))"
                        fi
                        export PATH="$JAVA_HOME/bin:$PATH"
                    fi

                    echo "JAVA_HOME is set to: $JAVA_HOME"
                    echo "Checking Java version..."
                    java -version
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
