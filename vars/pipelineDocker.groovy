def call(Map args) {
  if(!args.imageName) {
    currentBuild.result = "FAILURE"
    throw new Exception("You must specify the argument imageName")
  }

  String imageName = args.imageName

  pipeline {
    agent {
      label 'docker'
    }

    options {
      disableConcurrentBuilds()
      timestamps()
      ansiColor('xterm')
    }

    environment {
      IMAGE_NAME = "${imageName}"
      IMAGE_TAG  = env.GIT_COMMIT.substring(0, 8)
    }

    stages {
      stage('Build') {
        steps {
          script: "docker build -t ${env.IMAGE_NAME}:${env.IMAGE_TAG} ."
        }
      }

/*
      stage('Test') {
      }

      stage('Release') {
      }
*/

    }
  }
}
