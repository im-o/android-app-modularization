pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "Android App Modularization"
rootProject.buildFileName = "build.gradle.kts"

include(":app")
include(":base")
include(":features:test_feature")
include(":core")