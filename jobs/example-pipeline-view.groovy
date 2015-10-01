/**
 * @author kevin
 * @since 10/1/15.
 */
buildPipelineView('example-project') {
    filterBuildQueue()
    filterExecutors()
    title('Example Project')
    displayedBuilds(5)
    selectedJob('example-automated-project-build')
    alwaysAllowManualTrigger()
    showPipelineParameters()
    refreshFrequency(5)
}
