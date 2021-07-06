pluginManagement {
    repositories {
        google()
        jcenter()
        gradlePluginPortal()
        mavenCentral()
    }
    
}
rootProject.name = "kotlin_multiplatform_sample"


include(":androidApp")
include(":shared")
include("jsApp")
