
pipeline {
    agent any

    environment {
        ANDROID_HOME = "${env.ANDROID_HOME ?: '/opt/android-sdk'}"
        JAVA_HOME = "${env.JAVA_HOME ?: '/usr/lib/jvm/java-17-openjdk-amd64'}"
        PATH = "${env.JAVA_HOME}/bin:${env.PATH}"
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out source code...'
            }
        }

        stage('Configure Environment') {
            steps {
                sh '''
                    # Auto-detect JAVA_HOME if configured directory does not exist
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

                    # Auto-detect ANDROID_HOME if configured directory does not exist
                    if [ ! -d "$ANDROID_HOME" ]; then
                        echo "Configured ANDROID_HOME ($ANDROID_HOME) not found, attempting auto-detection..."
                        if [ -d "$HOME/Library/Android/sdk" ]; then
                            export ANDROID_HOME="$HOME/Library/Android/sdk"
                        elif [ -d "/usr/local/share/android-sdk" ]; then
                            export ANDROID_HOME="/usr/local/share/android-sdk"
                        elif [ -d "/usr/lib/android-sdk" ]; then
                            export ANDROID_HOME="/usr/lib/android-sdk"
                        elif [ -d "/var/lib/jenkins/android-sdk" ]; then
                            export ANDROID_HOME="/var/lib/jenkins/android-sdk"
                        fi
                    fi

                    echo "JAVA_HOME is set to: $JAVA_HOME"
                    echo "Checking Java version..."
                    java -version

                    echo "ANDROID_HOME is set to: $ANDROID_HOME"
                    if [ -d "$ANDROID_HOME" ]; then
                        echo "Android SDK directory verified at $ANDROID_HOME"
                    else
                        echo "WARNING: Android SDK directory not found at $ANDROID_HOME"
                    fi
                '''
            }
        }

        stage('Build') {
            steps {
                sh '''
                    if [ ! -d "$ANDROID_HOME" ]; then
                        if [ -d "$HOME/Library/Android/sdk" ]; then
                            export ANDROID_HOME="$HOME/Library/Android/sdk"
                        elif [ -d "/usr/local/share/android-sdk" ]; then
                            export ANDROID_HOME="/usr/local/share/android-sdk"
                        elif [ -d "/usr/lib/android-sdk" ]; then
                            export ANDROID_HOME="/usr/lib/android-sdk"
                        fi
                    fi
                    export ANDROID_SDK_ROOT="$ANDROID_HOME"
                    ./gradlew clean assembleDebug
                '''
            }
        }

        stage('JUnit Unit Tests') {
            steps {
                sh '''
                    if [ ! -d "$ANDROID_HOME" ]; then
                        if [ -d "$HOME/Library/Android/sdk" ]; then
                            export ANDROID_HOME="$HOME/Library/Android/sdk"
                        elif [ -d "/usr/local/share/android-sdk" ]; then
                            export ANDROID_HOME="/usr/local/share/android-sdk"
                        elif [ -d "/usr/lib/android-sdk" ]; then
                            export ANDROID_HOME="/usr/lib/android-sdk"
                        fi
                    fi
                    export ANDROID_SDK_ROOT="$ANDROID_HOME"
                    ./gradlew testDebugUnitTest
                '''
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
