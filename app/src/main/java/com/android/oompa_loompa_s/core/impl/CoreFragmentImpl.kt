package com.android.oompa_loompa_s.core.impl

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.oompa_loompa_s.R
import com.android.oompa_loompa_s.core.CoreFragment

open abstract class CoreFragmentImpl : Fragment(), CoreFragment{

    override fun onLoadError(error: String){
        setVisibilityView()
        showAlertDialog(error)
    }

    private fun showAlertDialog(error: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(context?.getString(R.string.title_alert_error))
        builder.setMessage(context?.getString(R.string.message_alert_error, error)) //error
        builder.setPositiveButton(context?.getString(R.string.accept_alert_error)) { dialog, which ->
        }
        builder.show()
    }

    protected open fun setVisibilityView(){
        view?.visibility = View.GONE
    }
}