 node('linux') {
            def zip_name = ''
            currentBuild.result = 'SUCCESS'
            stage("Git Checkout") 
             {
                           sh "echo hello"
             }

}
