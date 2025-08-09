node {
    def mvnPath
    
    stage('git clone') { 
        git branch: 'main', url: 'https://github.com/Haider7214/SpringBoot.git'
    }
    stage('Maven Build') {
        def mvnHome=tool name:'maven-3.9.10', type:'maven';
        mvnPath="${mvnHome}/bin/mvn";
        sh "${mvnPath} clean package";
        echo 'maven build success';
    }
}
