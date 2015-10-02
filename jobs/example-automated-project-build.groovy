/**
 * @author kevin
 * @since 10/1/15.
 */
job("example-automated-project-build") {
    scm {
        git {
            remote {
                url('https://github.com/apiumtech/example-automated-project.git')
                branch("master")
                credentials("2dc4f749-cf26-45b4-a3b6-694c795ebbb9")
            }
            clean()
        }
    }

    triggers {
        scm("H/3 * * * *")
    }

    steps {
        shell("./gradlew docker-build")
    }

    publishers {
        buildPipelineTrigger('example-automated-project-run-docker')
    }
}

job("example-automated-project-run-docker") {
    steps {
        shell("docker run docker.apiumtech.io/example-automated-project")
    }
}
