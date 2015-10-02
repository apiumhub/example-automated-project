import dsl.ApiumDsl
import javaposse.jobdsl.dsl.DslFactory

/**
 * @author kevin
 * @since 10/1/15.
 */

ApiumDsl.dockerJob(job("example-automated-project-build"), 'docker.apiumtech.io/example-automated-project') {
    label("docker")

    authorization {
        blocksInheritance()
        permissionAll("jenkins-admin")
    }

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

    publishers {
        downstream('example-automated-project-run-docker')
    }
}

job("example-automated-project-run-docker") {
    label("docker")

    authorization {
        blocksInheritance()
        permissionAll("jenkins-admin")
    }

    steps {
        shell("docker run docker.apiumtech.io/example-automated-project")
    }
}
