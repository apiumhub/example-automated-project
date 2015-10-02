package dsl

import javaposse.jobdsl.dsl.DslContext
import javaposse.jobdsl.dsl.Job
import javaposse.jobdsl.dsl.jobs.FreeStyleJob

/**
 * @author kevin
 * @since 10/2/15.
 */
class DockerPullingJob extends ApiumJob {
    DockerPullingJob(Job job) {
        super(job)
    }

    static DockerPullingJob create(Job job, String dockerName, @DslContext(FreeStyleJob) Closure closure) {
        job.with {
            label("docker")

            steps {
                shell("docker login -e dev@apiumtech.com -u apium.developer -p 4p1umt3chr0cks docker.apiumtech.io")
                shell("docker pull $dockerName")
            }
        }

        job.with(closure)
        return new DockerPullingJob(job)
    }
}
