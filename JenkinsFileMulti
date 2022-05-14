pipeline
{
    agent any
    stages
    {
        stage('ContinuousDownload_CreditCard')
        {
            steps
            {
                git 'https://github.com/intelliqittrainings/maven.git'
            }
        }
        stage('ContinuousBuild_CreditCard')
        {
            steps
            {
                sh 'mvn package'
            }
        }
        
       
    }   
    
}
