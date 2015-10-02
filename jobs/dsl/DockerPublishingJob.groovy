package dsl

import javaposse.jobdsl.dsl.DslContext
import javaposse.jobdsl.dsl.Job
import javaposse.jobdsl.dsl.jobs.FreeStyleJob

/**
 * @author kevin
 * @since 10/2/15.
 */
class DockerPublishingJob extends ApiumJob {
    DockerPublishingJob(Job job) {
        super(job)
    }

    static DockerPublishingJob create(Job job, String dockerName, @DslContext(FreeStyleJob) Closure closure) {
        job.with {
            label("docker")

            steps {
                shell("docker login -e dev@apiumtech.com -u apium.developer -p 4p1umt3chr0cks docker.apiumtech.io")
                shell("docker build -t $dockerName .")
                shell("docker push $dockerName")
            }
        }

        job.with(closure)
        return new DockerPublishingJob(job)
    }
}
