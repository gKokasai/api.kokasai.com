import groovy.util.Node
import java.text.SimpleDateFormat
import java.util.Date

plugins {
    id("com.jfrog.bintray")
    `maven-publish`
}

val githubUrl = "https://github.com/gKokasai/FlowerKt"
val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZ")

bintray {
    user = System.getenv("BINTRAY_USER")
    key = System.getenv("BINTRAY_KEY")
    setPublications("maven")
    publish = true
    pkg.run {
        repo = "maven"
        name = "FlowerKt-${project.name}"
        setLicenses("Apache-2.0")
        websiteUrl = githubUrl
        vcsUrl = "$githubUrl.git"
        issueTrackerUrl = "$githubUrl/issues"
        publicDownloadNumbers = true
        version.name = Project.version
        version.released = dateFormat.format(Date())
    }
}

publishing.publications {
    create<MavenPublication>("maven") {
        from(components["kotlin"])
        artifact(tasks.getByName<Zip>("kotlinSourcesJar"))
        groupId = Project.group
        artifactId = project.name
        version = Project.version
        pom.withXml {
            asNode().apply {
                appendNode("description", "WebApp Framework Written in Kotlin")
                appendNode("name", "FlowerKt-${project.name}")
                appendNode("url", githubUrl)
                appendChild("licenses") {
                    appendChild("license") {
                        appendNode("name", "The MIT Licenses")
                        appendNode("url", "https://opensource.org/licenses/MIT")
                        appendNode("distribution", "repo")
                    }
                }
                appendChild("scm") {
                    appendNode("url", githubUrl)
                }
            }
        }
    }
}

fun Node.appendChild(name: String, action: Node.() -> Unit) = Node(this, name).apply(action)
