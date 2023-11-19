package dev.sanmer.pi.utils.timber

import timber.log.Timber

class ReleaseTree : Timber.DebugTree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        super.log(priority, "<PI_REL>$tag", message, t)
    }

    override fun createStackElementTag(element: StackTraceElement): String {
        return super.createStackElementTag(element) + "(L${element.lineNumber})"
    }
}