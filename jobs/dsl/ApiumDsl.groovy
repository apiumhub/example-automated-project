package dsl

import javaposse.jobdsl.dsl.DslContext
import javaposse.jobdsl.dsl.DslFactory
import javaposse.jobdsl.dsl.jobs.FreeStyleJob

/**
 * @author kevin
 * @since 10/2/15.
 */
class ApiumDsl {
    static dockerJob(DslFactory dsl, String name, String dockerName, @DslContext(FreeStyleJob) Closure closure) {
        dsl.job(name) {
            steps {
                shell("docker login -e dev@apiumtech.com -u apium.developer -p 4p1umt3chr0cks docker.apiumtech.io")
                shell("docker build -t $dockerName .")
                shell("docker push $dockerName")
            }

            with(closure)
        }
    }
}
