pipeline {
  agent any

  stages {
    stage('Continuous Download') {
      steps {
        script{
            try{
                  git 'https://github.com/cnu10/sai_maven.git'
                }
                catch (Exception e1)
                {
                  mail bcc: '', body: 'Jenkings is unable to download the code from remote github', cc: '', from: '', replyTo: '', subject: 'download failed', to: 'git_admin@gmail.com'
                  exit(1)
                }
        }
      }
        
      }
    
    stage('Continuous Build') {
      steps {
        script{
                try{
                      sh 'mvn package'
                }
                catch (Exception e2){
                  mail bcc: '', body: 'Jenkings is unable to build and create artifact with maven', cc: '', from: '', replyTo: '', subject: 'build failed', to: 'developers@gmail.com'
                  exit(1)
                }
          }
        
      }
    }
    stage('Continuous Deployment') {
      steps {
        script{
                try{
                      sh 'scp /home/ubuntu/.jenkins/workspace/DeclarativePipeline/webapp/target/webapp.war ubuntu@172.31.9.254:/var/lib/tomcat9/webapps/qaappDec.war'
                }
                catch (Exception e3){
                  mail bcc: '', body: 'Jenkings is unable to deploy the code into tomcat QA server', cc: '', from: '', replyTo: '', subject: 'QA deployment failed', to: 'middlewareadmin@gmail.com'
                  exit(1)
                }
          }
        
      }
    }
    stage('Continuous Testing') {
      steps {
        script{
          try{
            git 'https://github.com/cnu10/sai_Functionaltesting.git'
            sh 'java -jar /home/ubuntu/.jenkins/workspace/DeclarativePipeline/testing.jar'
          }
          catch(exception e4){
            mail bcc: '', body: 'QA test failed', cc: '', from: '', replyTo: '', subject: 'QA testing failed', to: 'developers@gmail.com'
            exit(1)
          }

        }
        
      }
    }
    // stage('Continuous Delivery') {
    //   steps {
    //     script{
    //       try{
    //         input message: 'waiting for approval from SME srinivas', submitter: 'srinivas'
    //         sh scp /home/ubuntu/.jenkins/workspace/DeclarativePipeline/webapp/target/webapp.war ubuntu@172.31.15.123:/var/lib/tomcat9/webapps/prodappDec.war'
    //       }
    //       catch(exception e5){
    //         mail bcc: '', body: 'deployment failed in prod', cc: '', from: '', replyTo: '', subject: 'unable to deploy into prod tomcat server ', to: 'mwadmin@gmail.com'
    //         exit(1)          

    //     }
        
    //   }
    // }
    // }
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