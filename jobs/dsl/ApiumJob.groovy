package dsl

import javaposse.jobdsl.dsl.Job

/**
 * @author kevin
 * @since 10/2/15.
 */
abstract class ApiumJob {
    protected Job job

    ApiumJob(Job job) {
        this.job = job
    }

    def pollingScm(String repoUrl, String repoBranch = "master", String repoCredentials = "2dc4f749-cf26-45b4-a3b6-694c795ebbb9") {
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

        return this
    }

    def jobAuthorization(String ...groups) {
        job.with {
            authorization {
                blocksInheritance()
                permissionAll("jenkins-admin")
                groups.each {
                    permissionAll(it)
                }
            }
        }

        return this
    }
}
