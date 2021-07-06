package com.example.shared

import kotlinx.browser.document

actual class Platform actual constructor() {
    actual val platform: String
        get() = document.title
}
