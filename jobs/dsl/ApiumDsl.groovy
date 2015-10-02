package dsl

import javaposse.jobdsl.dsl.DslContext
import javaposse.jobdsl.dsl.Job
import javaposse.jobdsl.dsl.jobs.FreeStyleJob

/**
 * @author kevin
 * @since 10/2/15.
 */
class ApiumDsl {
    static dockerJob(Job job, String dockerName, @DslContext(FreeStyleJob) Closure closure) {
        job.with {
            steps {
                shell("docker login -e dev@apiumtech.com -u apium.developer -p 4p1umt3chr0cks docker.apiumtech.io")
                shell("docker build -t $dockerName .")
                shell("docker push $dockerName")
            }
        }

        job.with(closure)
    }

    static defaultCredentials() {
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
    }

    static pollingScm() {
        triggers {
            scm("H/3 * * * *")
        }
    }

    static accessFor(String ...groups) {
        authorization {
            blocksInheritance()
            permissionAll("jenkins-admin")
            groups.each {
                permissionAll(it)
            }
        }
    }
}
