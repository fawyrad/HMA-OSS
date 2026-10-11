package icu.nullptr.hidemyapplist.util

import com.topjohnwu.superuser.Shell

object SuUtils {

    fun execPrivileged(cmd: String): Boolean {
        // Use libsu instead of spawning a raw superuser process. The raw approach splits the
        // command on whitespace, so multi-word commands (e.g. the KernelSU late-load
        // "monkey -p <pkg> ..." call) get mangled and fail. libsu builds and runs the command
        // correctly and is reliable with the Magisk manager, so we keep it here.
        if (!Shell.getShell().isRoot) return false
        return Shell.cmd(cmd).exec().isSuccess
    }
}
