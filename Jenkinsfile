pipeline {
    agent any
    stages {
        stage('s1') {
            steps {
                sh 'echo "Stage 1"'
                // Construir la aplicación
            }
        }
        stage('s2') {
            parallel {
                stage('s2.a') {
                    steps {
                        sh 'sleep 5s'
                        sh 'echo "Stage A"'
                        // Pruebas unitarias
                    }
                }
                stage('s2.b') {
                    steps {
                        sh 'echo "Stage B"'
                        // Pruebas de integración
                    }
                }
            }
        }
        stage('s3') {
            steps {
                sh 'echo "Stage 3"'
                // Despliegue
            }
        }
    }
}