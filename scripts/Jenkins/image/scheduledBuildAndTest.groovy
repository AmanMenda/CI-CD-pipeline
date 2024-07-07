def folderName = "myjobs"

folder(folderName) {
    description("A collection of all the jobs ")
}

job("$folderName/scheduledBuildAndTest") {
    parameters {
        stringParam('GIT_REPOSITORY_URL')
        choiceParam('GIT_REPO_BRANCH', ['master', 'main', 'dev', 'staging'])
    }
    scm {
        git {
            remote {
                name('origin')
                url('${GIT_REPOSITORY_URL}')
                credentials('github-ci-key')
                branch('${GIT_REPO_BRANCH}')
            }
        }
    }
    triggers {
        scm('* * * *')
    }
    steps {
        shell('curl https://sh.rustup.rs -sSf | sh -s -- -y && \
            export PATH="$HOME/.cargo/bin:$PATH" && \
            cargo build --release && \
            cargo test')
    }
}