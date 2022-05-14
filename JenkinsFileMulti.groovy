pipeline
{
    agent any
    stages
    {
        stage('ContinuousDownload_Master')
        {
            steps
            {
                git 'https://github.com/intelliqittrainings/maven.git'
            }
        }
        stage('ContinuousBuild_Master')
        {
            steps
            {
                sh 'mvn package'
            }
        }
        stage('ContinuousDeployment_Master')
        {
            steps
            {
               sh 'scp /home/ubuntu/.jenkins/workspace/ultiBranchPipelineTestJob_master/webapp/target/webapp.war ubuntu@172.31.9.254:/var/lib/tomcat9/webapps/qaappDec.war'
            }
        }
        
       
    }   
    
}
