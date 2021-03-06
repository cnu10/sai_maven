pipeline
{
    agent any
    stages
    {
        stage('ContinuousDownload_Masterbranch')
        {
            steps
            {
                git 'https://github.com/intelliqittrainings/maven.git'
            }
        }
        stage('ContinuousBuild_Masterbranch')
        {
            steps
            {
                sh 'mvn package'
            }
        }
        stage('ContinuousDeployment_Masterbranch')
        {
            steps
            {
               sh 'scp /home/ubuntu/.jenkins/workspace/MultiBranchPipelineJob_master/webapp/target/webapp.war ubuntu@172.31.9.254:/var/lib/tomcat9/webapps/qaappDec.war'
            }
        }
        
       
    }   
    
}
