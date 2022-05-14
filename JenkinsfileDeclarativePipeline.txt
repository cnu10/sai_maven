pipeline {
  agent any

  stages {
    stage('Continuous Download') {
      steps {
        git 'https://github.com/cnu10/sai_maven.git'
      }
    }
    stage('Continuous Build') {
      steps {
        sh 'mvn package'
      }
    }
    stage('Continuous Deployment') {
      steps {
        sh 'scp /home/ubuntu/.jenkins/workspace/DeclarativePipeline/webapp/target/webapp.war ubuntu@172.31.9.254:/var/lib/tomcat9/webapps/qaappDec.war'
      }
    }
    stage('Continuous Testing') {
      steps {
        git 'https://github.com/cnu10/sai_Functionaltesting.git'
        sh 'java -jar /home/ubuntu/.jenkins/workspace/DeclarativePipeline/testing.jar'
      }
    }
    stage('Continuous Delivery') {
      steps {
        input message: 'waiting for approval from SME srinivas', submitter: 'srinivas'
        sh 'scp /home/ubuntu/.jenkins/workspace/DeclarativePipeline/webapp/target/webapp.war ubuntu@172.31.15.123:/var/lib/tomcat9/webapps/prodappDec.war'
      }
    }

  }
  //post{
  //   success{
  //      input message: 'waiting for approval from SME srinivas', submitter: 'srinivas'
  //      sh 'scp /home/ubuntu/.jenkins/workspace/DeclarativePipeline/webapp/target/webapp.war ubuntu@172.31.15.123:/var/lib/tomcat9/webapps/prodappDec.war'
  //  }

  /* failure{
       //     mail bcc: '', body: 'Build failed', cc: '', from: '', replyTo: '', subject: 'DeclarativePipeline job initiated', to: 'srinivas.aleti582@email.com'
       //     }
       // always{
       //     mail bcc: '', body: 'DeclarativePipeline job initiated and ran.', cc: '', from: '', replyTo: '', subject: 'DeclarativePipeline job initiated.', to: 'srinivas.aleti582@email.com'
        //    }
    }*/
}