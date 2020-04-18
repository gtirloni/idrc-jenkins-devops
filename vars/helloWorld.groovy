def call(Map args) {

  if (!args.name) {
    currentBuild.result = "FAILURE"
    throw new Exception("You need to define the argument name")
  }

  String name = args.name

  pipeline {
    stages {
      stage('Greeting') {
        steps {
          sh script: "echo 'Hello ${name}"
        }
      }
    }
  }
}
