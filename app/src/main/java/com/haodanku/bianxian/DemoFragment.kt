package com.haodanku.bianxian

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.haodanku.sdk.Hdk

/**
 * Author      : Stuart
 * Date        : 2021/8/20 15:45
 * Description :
 */
class DemoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_demo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val button: Button = view.findViewById(R.id.button)
        button.setOnClickListener {
            Hdk.openIndexPage()
        }
    }

}