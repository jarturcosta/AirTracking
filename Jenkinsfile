node {

    stage('Clone Repo') {
        git credentialsId: 'airtracking-git', url: 'git@gitlab.com:airtracking/airtracking.gitlab.io.git', branch: 'master'
    }

    stage('Build') {
        sh 'mvn -f airtracking clean package'
        sh 'mvn -f airtrackingwebapp clean package'
    }

    stage('Deploy Artifact') {
        sh 'mvn -s settings.xml -f airtracking deploy'
        sh 'mvn -s settings.xml -f airtrackingwebapp deploy'
    }

    stage('Deploy Runtime') {
        def APIVersion = sh script: 'mvn org.apache.maven.plugins:maven-help-plugin:3.1.0:evaluate -Dexpression=project.version -q -DforceStdout -f airtracking', returnStdout: true
        def WebVersion = sh script: 'mvn org.apache.maven.plugins:maven-help-plugin:3.1.0:evaluate -Dexpression=project.version -q -DforceStdout -f airtrackingwebapp', returnStdout: true
        
        def WebDockerfile = sh script: 'cat airtrackingwebapp/Dockerfile', returnStdout: true
        def ApiDockerfile = sh script: 'cat airtracking/Dockerfile', returnStdout: true
        def Compose = sh script: 'cat docker-compose.yml', returnStdout: true

        sshagent (credentials: ['airtracking-runtime']) {

            sh "scp airtracking/Dockerfile esp52@192.168.160.103:/home/esp52/airtracking-bin/Dockerfile"
            sh "scp airtrackingwebapp/Dockerfile esp52@192.168.160.103:/home/esp52/airtrackingwebapp-bin/Dockerfile"
            sh "scp docker-compose.yml esp52@192.168.160.103:/home/esp52/docker-compose.yml"
            
            sh "ssh -o StrictHostKeyChecking=no -l esp52 192.168.160.103 sh pull.sh ${APIVersion} ${WebVersion}"
        }
    }
}