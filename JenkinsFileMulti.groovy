pipeline
{
    agent any
    stages
    {
        stage('ContinuousDownload_CreditCard_Branch')
        {
            steps
            {
                git 'https://github.com/intelliqittrainings/maven.git'
            }
        }
        stage('ContinuousBuild_CreditCard_Branch')
        {
            steps
            {
                sh 'mvn package'
            }
        }
        
       
    }   
    
}
