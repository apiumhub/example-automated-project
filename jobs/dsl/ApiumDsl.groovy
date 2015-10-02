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

    static pollingScm(Job job, String repoUrl, String repoBranch = "master", String repoCredentials = "") {
        job.with {
            scm {
                git {
                    remote {
                        url(repoUrl)
                        branch(repoBranch)
                        credentials(repoCredentials)
                    }
                    clean()
                }
            }

            triggers {
                scm("H/3 * * * *")
            }
        }
    }

    static jobAuthorization(Job job, String ...groups) {
        job.with {
            authorization {
                blocksInheritance()
                permissionAll("jenkins-admin")
                groups.each {
                    permissionAll(it)
                }
            }
        }
    }
}
