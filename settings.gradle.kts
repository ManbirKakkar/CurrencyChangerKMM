pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
        mavenCentral()
        maven ("https://oss.sonatype.org/content/repositories/snapshots" )

    }
}

dependencyResolutionManagement {
    repositories {
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
        mavenCentral()
        maven ("https://oss.sonatype.org/content/repositories/snapshots" )

    }
}

rootProject.name = "CurrencyExchange"
include(":androidApp")
include(":shared")