pipeline {
    agent any
    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhubCred')
    }
    tools {
        maven 'Default'
    }
    parameters {
        string defaultValue: 'spc-cd', description: 'Nombre de la aplicación.', name: 'APP_NAME', trim: true
        string defaultValue: '0.1.0', description: 'Tag de la imagen Docker.', name: 'IMAGE_TAG', trim: true
        string defaultValue: 'spc-cd', description: 'Nombre del contenedor de la aplicación construida.', name: 'CONTAINER_NAME', trim: true
    }
    triggers {
        githubPush()
    }
    stages {
        stage('Build') {
            steps {
                //sh 'mvn clean package'
                withSonarQubeEnv('SonarQube') {
                    sh 'mvn clean package sonar:sonar'
                }
                echo 'Maven Build App Completed.'
            }
        }
        stage('Test') {
            steps {
                    sh 'mvn test'
                    echo 'Unit Tests Executed'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                    echo 'Unit Tests Report Published.'
                }
            }
        }
        stage('DockerHub Login') {
            steps {
                sh "echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin"
                echo 'Login Completed.'
            }
        }
        stage('Build Docker Images') {
            parallel {
                stage('Current Tag') {
                    steps{
                        sh "docker build --tag $DOCKERHUB_CREDENTIALS_USR/${params.APP_NAME}:${params.IMAGE_TAG} ."
                        echo 'Build Image Completed.'
                    }
                }
                stage('Latest Tag') {
                    steps{
                        sh "docker build --tag $DOCKERHUB_CREDENTIALS_USR/${params.APP_NAME}:latest ."
                        echo 'Build Image:latest Completed.'
                    }
                }
            }
        }
        stage ('Push Image To DockerHub') {
            parallel {
                stage('Current Tag') {
                    steps{
                        sh "docker push $DOCKERHUB_CREDENTIALS_USR/${params.APP_NAME}:${params.IMAGE_TAG}"
                        echo "Image $DOCKERHUB_CREDENTIALS_USR/${params.APP_NAME}:${params.IMAGE_TAG} pushed."
                    }
                }
                stage('Latest Tag') {
                    steps{
                        sh "docker push $DOCKERHUB_CREDENTIALS_USR/${params.APP_NAME}:latest"
                        echo "Image $DOCKERHUB_CREDENTIALS_USR/${params.APP_NAME}:latest pushed."
                    }
                }
            }
        }
        stage ('Running App') {
            steps {
                sh "docker container rm --force ${params.CONTAINER_NAME}  2> /dev/null || echo 'El contenedor no existe'"
                sh "docker run --name ${params.CONTAINER_NAME} --detach --rm --publish 8181:8181 $DOCKERHUB_CREDENTIALS_USR/${params.APP_NAME}:${params.IMAGE_TAG}"
                echo "App Running In ${params.CONTAINER_NAME} Container."
            }
        }
    }
    post {
        always {
            sh 'docker logout'
            echo 'Docker Logged Out.'
        }
        success {
            slackSend channel: '#jenkins', color: 'good', message: "Build deployed successfully - ${env.JOB_NAME} ${env.BUILD_NUMBER} (<${env.BUILD_URL}|Open>)"
        }
        failure {
            //slackSend failOnError:true, message:"Build failed  - ${env.JOB_NAME} ${env.BUILD_NUMBER} (<${env.BUILD_URL}|Open>)"
            slackSend(
                channel: '#jenkins',
                color: 'danger',
                message: "Build failed  - ${env.JOB_NAME} ${env.BUILD_NUMBER} (<${env.BUILD_URL}|Open>)"
            )
        }
    }
}