def folderName = "myjobs"

folder(folderName) {
    description("A collection of all the jobs")
}

job("$folderName/buildImage") {
    parameters {
        stringParam('GIT_REPOSITORY_URL')
    }
    triggers {
        scm('* * * * *')
    }
    scm {
        git {
            remote {
                name('origin')
                url('${GIT_REPOSITORY_URL}')
                credentials('github-ci-key')
            }
            branch('master')
        }
    }
    steps {
        shell("cp /custom_images/Dockerfile.standalone . && docker build -t my-rust-app -f ./Dockerfile.standalone .")
    }
}
