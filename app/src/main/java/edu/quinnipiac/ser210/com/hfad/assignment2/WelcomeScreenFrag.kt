package edu.quinnipiac.ser210.com.hfad.assignment2

/**
 * Assignment 2; Four in a Row
 * @author Camryn Keller
 * @date 2/24/2023
 */

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.findNavController

class WelcomeScreenFrag : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_welcome_screen,container,false)
        //variable for start button
        val startButton = view.findViewById<Button>(R.id.start_button)
        //variable for username edit text
        val messageView = view.findViewById<EditText>(R.id.username)


        startButton.setOnClickListener {
            //saves the typed text as a string
            val username = messageView.text.toString()
            //sends the string over to the next fragment
            val action = WelcomeScreenFragDirections.actionWelcomeScreenFragToGameFrag(username)
            view.findNavController().navigate(action)
        }

        return view
    }
}