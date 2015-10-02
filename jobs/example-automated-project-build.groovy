import static dsl.ApiumDsl.*

/**
 * @author kevin
 * @since 10/1/15.
 */

dockerJob(job("example-automated-project-build"), 'docker.apiumtech.io/example-automated-project') {
    label("docker")

    pollingScm(this, 'https://github.com/apiumtech/example-automated-project.git')
    jobAuthorization(this)

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
