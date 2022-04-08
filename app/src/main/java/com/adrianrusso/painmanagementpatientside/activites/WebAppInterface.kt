package com.adrianrusso.painmanagementpatientside.activites

import android.webkit.JavascriptInterface
import com.adrianrusso.painmanagementpatientside.fragments.GraphFragment
import com.adrianrusso.painmanagementpatientside.models.AppUser

class WebAppInterface(private val mContext: GraphFragment) {


    @JavascriptInterface

    fun insertID(): String {

        return AppUser.mdbUser.id
    }

}
