import static dsl.ApiumDsl.*

/**
 * @author kevin
 * @since 10/1/15.
 */

dockerJob(job("example-automated-project-build"), 'docker.apiumtech.io/example-automated-project') {
    label("docker")

    defaultCredentials()
    pollingScm()
    accessFor()

    authorization {
        blocksInheritance()
        permissionAll("jenkins-admin")
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
