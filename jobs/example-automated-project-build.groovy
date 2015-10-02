import dsl.ApiumDsl
import javaposse.jobdsl.dsl.DslFactory

/**
 * @author kevin
 * @since 10/1/15.
 */

ApiumDsl.dockerJob(this as DslFactory, "example-automated-project-build", 'docker.apiumtech.io/example-automated-project') {
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

    steps {
        shell("docker login -e dev@apiumtech.com -u apium.developer -p 4p1umt3chr0cks docker.apiumtech.io")
        shell("docker build -t docker.apiumtech.io/example-automated-project .")
        shell("docker push docker.apiumtech.io/example-automated-project")
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
