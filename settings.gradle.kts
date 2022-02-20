dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

include(":app")
include(":base")
include(":test_feature")
include(":core")

rootProject.name = "Android App Modularization"
rootProject.buildFileName = "build.gradle.kts"