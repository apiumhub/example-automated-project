import static dsl.ApiumDsl.*

/**
 * @author kevin
 * @since 10/1/15.
 */

def buildingJob = job("example-automated-project-build")
dockerJob(buildingJob, 'docker.apiumtech.io/example-automated-project') {
    pollingScm(buildingJob, 'https://github.com/apiumtech/example-automated-project.git')
    jobAuthorization(buildingJob)

    publishers {
        downstream('example-automated-project-run-docker')
    }
}

def runDockerJob = job("example-automated-project-run-docker")
runDockerJob.with {
    label("docker")

    jobAuthorization(runDockerJob)

    steps {
        shell("docker run docker.apiumtech.io/example-automated-project")
    }
}
