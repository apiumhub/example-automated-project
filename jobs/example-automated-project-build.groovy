import com.apiumtech.dsl.DockerPublishingJob
import com.apiumtech.dsl.DockerPullingJob

/**
 * @author kevin
 * @since 10/1/15.
 */

def DockerImage = 'docker.apiumtech.io/example-automated-project'

DockerPublishingJob.create(job("example-automated-project-build"), DockerImage) {
    publishers {
        downstream('example-automated-project-run-docker')
    }
}
.pollingScm('https://github.com/apiumtech/example-automated-project.git')
.jobAuthorization()


DockerPullingJob.create(job("example-automated-project-run-docker"), DockerImage) {
    steps {
        shell("docker run " + DockerImage)
    }
}.jobAuthorization()
