/**
 * @author kevin
 * @since 10/1/15.
 */
job("example-automated-project-build") {
    steps {
        shell("docker build -t docker.apiumtech.io/example-automated-project .")
    }
}
