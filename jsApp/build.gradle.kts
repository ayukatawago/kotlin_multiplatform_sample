plugins {
    id("org.jetbrains.kotlin.js")
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/kotlin-js-wrappers")
    mavenCentral()
}

dependencies {
    implementation(project(":shared"))
    implementation(kotlin("stdlib-js"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0")

    //React, React DOM + Wrappers
    implementation("org.jetbrains:kotlin-react:17.0.2-pre.154-kotlin-1.5.0")
    implementation("org.jetbrains:kotlin-react-dom:17.0.2-pre.154-kotlin-1.5.0")
    implementation(npm("react", "17.0.2"))
    implementation(npm("react-dom", "17.0.2"))

    //Kotlin styled
    implementation("org.jetbrains:kotlin-styled:5.2.1-pre.148-kotlin-1.4.21")
    implementation(npm("styled-components", "~5.2.1"))

    //Video Player
    implementation(npm("react-youtube-lite", "1.0.1"))

    //Share Buttons
    implementation(npm("react-share", "~4.2.1"))
}

kotlin {
    js {
        browser {
            webpackTask {
                cssSupport.enabled = true
            }

            runTask {
                cssSupport.enabled = true
            }

            testTask {
                useKarma {
                    useChromeHeadless()
                    webpackConfig.cssSupport.enabled = true
                }
            }
        }
        binaries.executable()
    }
}
