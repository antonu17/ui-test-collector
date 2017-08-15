/**
 * Created by kit on 8/15/17.
 */


import hudson.model.Build
import jenkins.model.Jenkins

Jenkins jenkins = Jenkins.getInstance();
Build currentBuild = Thread.currentThread().executable

currentBuild.getAllActions().each { action ->
    if (action.hasProperty("causes")) {
        action.causes.each { cause ->
            if (cause.hasProperty("upstreamProject") && cause.hasProperty("upstreamBuild")) {
                def upstreamBuild = jenkins.getItem(cause.upstreamProject).getBuildByNumber(cause.upstreamBuild);
                processBuild(upstreamBuild)
            }
        }
    }
}

def processBuild(Build build) {
    println build.workspace
}

return 0
