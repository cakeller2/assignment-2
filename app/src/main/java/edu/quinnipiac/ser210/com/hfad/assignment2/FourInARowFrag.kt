package edu.quinnipiac.ser210.com.hfad.assignment2

/**
 * Assignment 2; Four in a Row
 * @author Camryn Keller
 * @date 2/24/2023
 */

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView


class FourInARowFrag : Fragment(), View.OnClickListener, IGame  {

//creates a random number between 0 and 35
    override val computerMove: Int
        get() =
            ((0..35).random())

    // creates a 2D array and fills with zeros to act as win check
    val board = Array(GameConstants.ROWS) { IntArray(GameConstants.COLS){0}}

    //array holding all cellNames
    var cellNames = ArrayList<Int>()

    //win condition
    var win = false

    //variables for num of wins
    var numPlayerWins = 0
    var numCompWins = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_game, container, false)
        //message info passed from WelcomeScreenFrag
        val message = FourInARowFragArgs.fromBundle(requireArguments()).message

        //player view
        val usernameView = view.findViewById<TextView>(R.id.username_message)
        //computer player view
        val computerView = view.findViewById<TextView>(R.id.comp_points)

        //reset button view
        val reset = view.findViewById<Button>(R.id.reset_button)
        //button set invisible when not needed
        reset.visibility = View.INVISIBLE

        //set text for point counter with inputed username
        usernameView.text = "Points for Player $message: $numPlayerWins"
        computerView.text = "Points for Computer: $numCompWins"

        //on click calls the resets method
        reset.setOnClickListener{
            clearBoard()
        }

        //allows for one on Click to handle all the buttons
        view.findViewById<ImageButton>(R.id.cell1).setOnClickListener(this)
        view.findViewById<ImageButton>(R.id.cell2).setOnClickListener(this)
        view.findViewById<ImageButton>(R.id.cell3).setOnClickListener(this)
        view.findViewById<ImageButton>(R.id.cell4).setOnClickListener(this)
        view.findViewById<ImageButton>(R.id.cell5).setOnClickListener(this)
        view.findViewById<ImageButton>(R.id.cell6).setOnClickListener(this)
        view.findViewById<ImageButton>(R.id.cell7).setOnClickListener(this)
        view.findViewById<ImageButton>(R.id.cell8).setOnClickListener(this)
        view.findViewById<ImageButton>(R.id.cell9).setOnClickListener(this)
        view.findViewById<ImageButton>(R.id.cell10).setOnClickListener(this)
        view.findViewById<ImageButton>(R.id.cell11).setOnClickListener(this)
        view.findViewById<ImageButton>(R.id.cell12).setOnClickListener(this)
        view.findViewById<ImageButton>(R.id.cell13).setOnClickListener(this)
        view.findViewById<ImageButton>(R.id.cell14).setOnClickListener(this)
        view.findViewById<ImageButton>(R.id.cell15).setOnClickListener(this)
        view.findViewById<ImageButton>(R.id.cell16).setOnClickListener(this)
        view.findViewById<ImageButton>(R.id.cell17).setOnClickListener(this)
        view.findViewById<ImageButton>(R.id.cell18).setOnClickListener(this)
        view.findViewById<ImageButton>(R.id.cell19).setOnClickListener(this)
        view.findViewById<ImageButton>(R.id.cell20).setOnClickListener(this)
        view.findViewById<ImageButton>(R.id.cell21).setOnClickListener(this)
        view.findViewById<ImageButton>(R.id.cell22).setOnClickListener(this)
        view.findViewById<ImageButton>(R.id.cell23).setOnClickListener(this)
        view.findViewById<ImageButton>(R.id.cell24).setOnClickListener(this)
        view.findViewById<ImageButton>(R.id.cell25).setOnClickListener(this)
        view.findViewById<ImageButton>(R.id.cell26).setOnClickListener(this)
        view.findViewById<ImageButton>(R.id.cell27).setOnClickListener(this)
        view.findViewById<ImageButton>(R.id.cell28).setOnClickListener(this)
        view.findViewById<ImageButton>(R.id.cell29).setOnClickListener(this)
        view.findViewById<ImageButton>(R.id.cell30).setOnClickListener(this)
        view.findViewById<ImageButton>(R.id.cell31).setOnClickListener(this)
        view.findViewById<ImageButton>(R.id.cell32).setOnClickListener(this)
        view.findViewById<ImageButton>(R.id.cell33).setOnClickListener(this)
        view.findViewById<ImageButton>(R.id.cell34).setOnClickListener(this)
        view.findViewById<ImageButton>(R.id.cell35).setOnClickListener(this)
        view.findViewById<ImageButton>(R.id.cell36).setOnClickListener(this)

        //adds all the cell names to the cellNames array
        cellNames.add(R.id.cell1)
        cellNames.add(R.id.cell2)
        cellNames.add(R.id.cell3)
        cellNames.add(R.id.cell4)
        cellNames.add(R.id.cell5)
        cellNames.add(R.id.cell6)
        cellNames.add(R.id.cell7)
        cellNames.add(R.id.cell8)
        cellNames.add(R.id.cell9)
        cellNames.add(R.id.cell10)
        cellNames.add(R.id.cell11)
        cellNames.add(R.id.cell12)
        cellNames.add(R.id.cell13)
        cellNames.add(R.id.cell14)
        cellNames.add(R.id.cell15)
        cellNames.add(R.id.cell16)
        cellNames.add(R.id.cell17)
        cellNames.add(R.id.cell18)
        cellNames.add(R.id.cell19)
        cellNames.add(R.id.cell20)
        cellNames.add(R.id.cell21)
        cellNames.add(R.id.cell22)
        cellNames.add(R.id.cell23)
        cellNames.add(R.id.cell24)
        cellNames.add(R.id.cell25)
        cellNames.add(R.id.cell26)
        cellNames.add(R.id.cell27)
        cellNames.add(R.id.cell28)
        cellNames.add(R.id.cell29)
        cellNames.add(R.id.cell30)
        cellNames.add(R.id.cell31)
        cellNames.add(R.id.cell32)
        cellNames.add(R.id.cell33)
        cellNames.add(R.id.cell34)
        cellNames.add(R.id.cell35)
        cellNames.add(R.id.cell36)


        return view

    }

    @SuppressLint("SetTextI18n")
    //One onClick for 36  image buttons
    override fun onClick(v: View?) {
        //doesn't allow for clicking if the game is already won
        if (!win){
            when (v?.id) {
                //every cell is the the same
                R.id.cell1 -> {
                    //calls set move because the player goes first
                    setMove(1, 0)

                    //calls checksForWinner to see if player won
                    if (checkForWinner() == GameConstants.BLUE_WON) {
                        //displays the win text
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You won!\nWanna play again?"
                        //sets win condition to true
                        win = true
                        //updates score that affects player points
                        numPlayerWins += 1
                        //makes the reset button visible now that there is a winner
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }
                    //checks for a tie after the player's turn
                    if (checkForWinner() == GameConstants.TIE) {
                        //displays the tie text
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        //sets win condition to true
                        win = true
                        //updates both the player and computer variable for winning
                        numPlayerWins += 1
                        numCompWins += 1
                        //makes the reset button visible now that there is a winner
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                    //calls setMove with computermove being a random number
                    setMove(2, computerMove)

                    //calls checkForWinner to see if computer won
                    if (checkForWinner() == GameConstants.RED_WON) {
                        //displays the loser text
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You Lost :(\nWanna play again?"
                        //sets the win condition to true
                        win = true
                        //updates the computer variable for winning
                        numCompWins += 1
                        //makes the reset button visible now that there is a winner
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }
                    //checking for a tie after the computer's turn
                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }
                }
                //every cell past this is the same as the first one
                R.id.cell2 -> {
                    setMove(1, 1)

                    if (checkForWinner() == GameConstants.BLUE_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You won!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                    setMove(2, computerMove)

                    if (checkForWinner() == GameConstants.RED_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You Lost :(\nWanna play again?"
                        win = true
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }
                }
                R.id.cell3 -> {
                    setMove(1, 2)

                    if (checkForWinner() == GameConstants.BLUE_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You won!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                    setMove(2, computerMove)

                    if (checkForWinner() == GameConstants.RED_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You Lost :(\nWanna play again?"
                        win = true
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }
                }
                R.id.cell4 -> {
                    setMove(1, 3)

                    if (checkForWinner() == GameConstants.BLUE_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You won!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                    setMove(2, computerMove)

                    if (checkForWinner() == GameConstants.RED_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You Lost :(\nWanna play again?"
                        win = true
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }
                }
                R.id.cell5 -> {
                    setMove(1, 4)

                    if (checkForWinner() == GameConstants.BLUE_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You won!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                    setMove(2, computerMove)

                    if (checkForWinner() == GameConstants.RED_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You Lost :(\nWanna play again?"
                        win = true
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }
                }

                R.id.cell6 -> {
                    setMove(1, 5)

                    if (checkForWinner() == GameConstants.BLUE_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You won!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                    setMove(2, computerMove)

                    if (checkForWinner() == GameConstants.RED_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You Lost :(\nWanna play again?"
                        win = true
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }
                }
                R.id.cell7 -> {
                    setMove(1, 6)

                    if (checkForWinner() == GameConstants.BLUE_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You won!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                    setMove(2, computerMove)

                    if (checkForWinner() == GameConstants.RED_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You Lost :(\nWanna play again?"
                        win = true
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }
                }
                R.id.cell8 -> {
                    setMove(1, 7)

                    if (checkForWinner() == GameConstants.BLUE_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You won!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                    setMove(2, computerMove)

                    if (checkForWinner() == GameConstants.RED_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You Lost :(\nWanna play again?"
                        win = true
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                }
                R.id.cell9 -> {
                    setMove(1, 8)

                    if (checkForWinner() == GameConstants.BLUE_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You won!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                    setMove(2, computerMove)

                    if (checkForWinner() == GameConstants.RED_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You Lost :(\nWanna play again?"
                        win = true
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }
                }
                R.id.cell10 -> {
                    setMove(1, 9)

                    if (checkForWinner() == GameConstants.BLUE_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You won!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                    setMove(2, computerMove)

                    if (checkForWinner() == GameConstants.RED_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You Lost :(\nWanna play again?"
                        win = true
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }
                }
                R.id.cell11 -> {
                    setMove(1, 10)

                    if (checkForWinner() == GameConstants.BLUE_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You won!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                    setMove(2, computerMove)

                    if (checkForWinner() == GameConstants.RED_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You Lost :(\nWanna play again?"
                        win = true
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }
                }
                R.id.cell12 -> {
                    setMove(1, 11)

                    if (checkForWinner() == GameConstants.BLUE_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You won!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                    setMove(2, computerMove)

                    if (checkForWinner() == GameConstants.RED_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You Lost :(\nWanna play again?"
                        win = true
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }
                }
                R.id.cell13 -> {
                    setMove(1, 12)

                    if (checkForWinner() == GameConstants.BLUE_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You won!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                    setMove(2, computerMove)

                    if (checkForWinner() == GameConstants.RED_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You Lost :(\nWanna play again?"
                        win = true
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }
                }
                R.id.cell14 -> {
                    setMove(1, 13)

                    if (checkForWinner() == GameConstants.BLUE_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You won!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                    setMove(2, computerMove)

                    if (checkForWinner() == GameConstants.RED_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You Lost :(\nWanna play again?"
                        win = true
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }
                }
                R.id.cell15 -> {
                    setMove(1, 14)

                    if (checkForWinner() == GameConstants.BLUE_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You won!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                    setMove(2, computerMove)

                    if (checkForWinner() == GameConstants.RED_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You Lost :(\nWanna play again?"
                        win = true
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }
                }
                R.id.cell16 -> {
                    setMove(1, 15)

                    if (checkForWinner() == GameConstants.BLUE_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You won!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                    setMove(2, computerMove)

                    if (checkForWinner() == GameConstants.RED_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You Lost :(\nWanna play again?"
                        win = true
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }
                }
                R.id.cell17 -> {
                    setMove(1, 16)

                    if (checkForWinner() == GameConstants.BLUE_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You won!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                    setMove(2, computerMove)

                    if (checkForWinner() == GameConstants.RED_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You Lost :(\nWanna play again?"
                        win = true
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }
                }
                R.id.cell18 -> {
                    setMove(1, 17)

                    if (checkForWinner() == GameConstants.BLUE_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You won!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                    setMove(2, computerMove)

                    if (checkForWinner() == GameConstants.RED_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You Lost :(\nWanna play again?"
                        win = true
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                }
                R.id.cell19 -> {
                    setMove(1, 18)

                    if (checkForWinner() == GameConstants.BLUE_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You won!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                    setMove(2, computerMove)

                    if (checkForWinner() == GameConstants.RED_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You Lost :(\nWanna play again?"
                        win = true
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }
                }
                R.id.cell20 -> {
                    setMove(1, 19)

                    if (checkForWinner() == GameConstants.BLUE_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You won!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                    setMove(2, computerMove)

                    if (checkForWinner() == GameConstants.RED_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You Lost :(\nWanna play again?"
                        win = true
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                }
                R.id.cell21 -> {
                    setMove(1, 20)

                    if (checkForWinner() == GameConstants.BLUE_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You won!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                    setMove(2, computerMove)

                    if (checkForWinner() == GameConstants.RED_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You Lost :(\nWanna play again?"
                        win = true
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                }
                R.id.cell22 -> {
                    setMove(1, 21)

                    if (checkForWinner() == GameConstants.BLUE_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You won!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                    setMove(2, computerMove)

                    if (checkForWinner() == GameConstants.RED_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You Lost :(\nWanna play again?"
                        win = true
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                }
                R.id.cell23 -> {
                    setMove(1, 22)

                    if (checkForWinner() == GameConstants.BLUE_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You won!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                    setMove(2, computerMove)

                    if (checkForWinner() == GameConstants.RED_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You Lost :(\nWanna play again?"
                        win = true
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                }
                R.id.cell24 -> {
                    setMove(1, 23)

                    if (checkForWinner() == GameConstants.BLUE_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You won!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                    setMove(2, computerMove)

                    if (checkForWinner() == GameConstants.RED_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You Lost :(\nWanna play again?"
                        win = true
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                }
                R.id.cell25 -> {
                    setMove(1, 24)

                    if (checkForWinner() == GameConstants.BLUE_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You won!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                    setMove(2, computerMove)

                    if (checkForWinner() == GameConstants.RED_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You Lost :(\nWanna play again?"
                        win = true
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                }
                R.id.cell26 -> {
                    setMove(1, 25)

                    if (checkForWinner() == GameConstants.BLUE_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You won!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                    setMove(2, computerMove)

                    if (checkForWinner() == GameConstants.RED_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You Lost :(\nWanna play again?"
                        win = true
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                }
                R.id.cell27 -> {
                    setMove(1, 26)

                    if (checkForWinner() == GameConstants.BLUE_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You won!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                    setMove(2, computerMove)

                    if (checkForWinner() == GameConstants.RED_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You Lost :(\nWanna play again?"
                        win = true
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                }
                R.id.cell28 -> {
                    setMove(1, 27)

                    if (checkForWinner() == GameConstants.BLUE_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You won!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                    setMove(2, computerMove)

                    if (checkForWinner() == GameConstants.RED_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You Lost :(\nWanna play again?"
                        win = true
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                }
                R.id.cell29 -> {
                    setMove(1, 28)

                    if (checkForWinner() == GameConstants.BLUE_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You won!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                    setMove(2, computerMove)

                    if (checkForWinner() == GameConstants.RED_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You Lost :(\nWanna play again?"
                        win = true
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                }
                R.id.cell30 -> {
                    setMove(1, 29)

                    if (checkForWinner() == GameConstants.BLUE_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You won!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                    setMove(2, computerMove)

                    if (checkForWinner() == GameConstants.RED_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You Lost :(\nWanna play again?"
                        win = true
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                }
                R.id.cell31 -> {
                    setMove(1, 30)

                    if (checkForWinner() == GameConstants.BLUE_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You won!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                    setMove(2, computerMove)

                    if (checkForWinner() == GameConstants.RED_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You Lost :(\nWanna play again?"
                        win = true
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                }
                R.id.cell32 -> {
                    setMove(1, 31)

                    if (checkForWinner() == GameConstants.BLUE_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You won!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                    setMove(2, computerMove)

                    if (checkForWinner() == GameConstants.RED_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You Lost :(\nWanna play again?"
                        win = true
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                }
                R.id.cell33 -> {
                    setMove(1, 32)

                    if (checkForWinner() == GameConstants.BLUE_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You won!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                    setMove(2, computerMove)

                    if (checkForWinner() == GameConstants.RED_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You Lost :(\nWanna play again?"
                        win = true
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                }
                R.id.cell34 -> {
                    setMove(1, 33)

                    if (checkForWinner() == GameConstants.BLUE_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You won!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                    setMove(2, computerMove)

                    if (checkForWinner() == GameConstants.RED_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You Lost :(\nWanna play again?"
                        win = true
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                }
                R.id.cell35 -> {
                    setMove(1, 34)

                    if (checkForWinner() == GameConstants.BLUE_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You won!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                    setMove(2, computerMove)

                    if (checkForWinner() == GameConstants.RED_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You Lost :(\nWanna play again?"
                        win = true
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                }
                R.id.cell36 -> {
                    setMove(1, 35)

                    if (checkForWinner() == GameConstants.BLUE_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You won!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                    setMove(2, computerMove)

                    if (checkForWinner() == GameConstants.RED_WON) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "You Lost :(\nWanna play again?"
                        win = true
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE

                        return
                    }

                    if (checkForWinner() == GameConstants.TIE) {
                        view?.findViewById<TextView>(R.id.win_message)?.text =
                            "It's a Tie!\nWanna play again?"
                        win = true
                        numPlayerWins += 1
                        numCompWins += 1
                        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.VISIBLE
                        return
                    }

                }
            }
        }
    }

    //clear board function

    //goes through and clears the 2D array back to zeros
    override fun clearBoard() {
        for (row in 0 until 6) {
            for (col in 0 until 6) {
                board[row][col] = 0
            }
        }

        //sets end games text back to blank
        view?.findViewById<TextView>(R.id.win_message)?.setText("")

        //turns the image buttons back to empty purple
        view?.findViewById<ImageButton>(R.id.cell1)?.setImageResource(R.drawable.purple)
        view?.findViewById<ImageButton>(R.id.cell2)?.setImageResource(R.drawable.purple)
        view?.findViewById<ImageButton>(R.id.cell3)?.setImageResource(R.drawable.purple)
        view?.findViewById<ImageButton>(R.id.cell4)?.setImageResource(R.drawable.purple)
        view?.findViewById<ImageButton>(R.id.cell5)?.setImageResource(R.drawable.purple)
        view?.findViewById<ImageButton>(R.id.cell6)?.setImageResource(R.drawable.purple)
        view?.findViewById<ImageButton>(R.id.cell7)?.setImageResource(R.drawable.purple)
        view?.findViewById<ImageButton>(R.id.cell8)?.setImageResource(R.drawable.purple)
        view?.findViewById<ImageButton>(R.id.cell9)?.setImageResource(R.drawable.purple)
        view?.findViewById<ImageButton>(R.id.cell10)?.setImageResource(R.drawable.purple)
        view?.findViewById<ImageButton>(R.id.cell11)?.setImageResource(R.drawable.purple)
        view?.findViewById<ImageButton>(R.id.cell12)?.setImageResource(R.drawable.purple)
        view?.findViewById<ImageButton>(R.id.cell13)?.setImageResource(R.drawable.purple)
        view?.findViewById<ImageButton>(R.id.cell14)?.setImageResource(R.drawable.purple)
        view?.findViewById<ImageButton>(R.id.cell15)?.setImageResource(R.drawable.purple)
        view?.findViewById<ImageButton>(R.id.cell16)?.setImageResource(R.drawable.purple)
        view?.findViewById<ImageButton>(R.id.cell17)?.setImageResource(R.drawable.purple)
        view?.findViewById<ImageButton>(R.id.cell18)?.setImageResource(R.drawable.purple)
        view?.findViewById<ImageButton>(R.id.cell19)?.setImageResource(R.drawable.purple)
        view?.findViewById<ImageButton>(R.id.cell20)?.setImageResource(R.drawable.purple)
        view?.findViewById<ImageButton>(R.id.cell21)?.setImageResource(R.drawable.purple)
        view?.findViewById<ImageButton>(R.id.cell22)?.setImageResource(R.drawable.purple)
        view?.findViewById<ImageButton>(R.id.cell23)?.setImageResource(R.drawable.purple)
        view?.findViewById<ImageButton>(R.id.cell24)?.setImageResource(R.drawable.purple)
        view?.findViewById<ImageButton>(R.id.cell25)?.setImageResource(R.drawable.purple)
        view?.findViewById<ImageButton>(R.id.cell26)?.setImageResource(R.drawable.purple)
        view?.findViewById<ImageButton>(R.id.cell27)?.setImageResource(R.drawable.purple)
        view?.findViewById<ImageButton>(R.id.cell28)?.setImageResource(R.drawable.purple)
        view?.findViewById<ImageButton>(R.id.cell29)?.setImageResource(R.drawable.purple)
        view?.findViewById<ImageButton>(R.id.cell30)?.setImageResource(R.drawable.purple)
        view?.findViewById<ImageButton>(R.id.cell31)?.setImageResource(R.drawable.purple)
        view?.findViewById<ImageButton>(R.id.cell32)?.setImageResource(R.drawable.purple)
        view?.findViewById<ImageButton>(R.id.cell33)?.setImageResource(R.drawable.purple)
        view?.findViewById<ImageButton>(R.id.cell34)?.setImageResource(R.drawable.purple)
        view?.findViewById<ImageButton>(R.id.cell35)?.setImageResource(R.drawable.purple)
        view?.findViewById<ImageButton>(R.id.cell36)?.setImageResource(R.drawable.purple)

        //makes the reset button invisible again
        view?.findViewById<Button>(R.id.reset_button)?.visibility = View.INVISIBLE

        //makes the win false
        win = false

        //remakes the view variables so they can be accessed here
        var message = FourInARowFragArgs.fromBundle(requireArguments()).message
        val usernameView = view?.findViewById<TextView>(R.id.username_message)
        val computerView = view?.findViewById<TextView>(R.id.comp_points)

        //updates the point display
        usernameView?.text = "Points for Player $message: $numPlayerWins"
        computerView?.text = "Points for Computer: $numCompWins"
    }

    //set move function intaking player and specific cell
    override fun setMove(player: Int, location: Int) {
        //updates the 2D array
        if (board[location/GameConstants.ROWS][location%GameConstants.COLS] == GameConstants.EMPTY) {
            board[location/GameConstants.ROWS][location%GameConstants.COLS] = player
            //updates the visible board depending on who is the player
            if (player == GameConstants.BLUE) {
                view?.findViewById<ImageButton>(cellNames[location])?.setImageResource(R.drawable.blue)
            }else {
                view?.findViewById<ImageButton>(cellNames[location])?.setImageResource(R.drawable.red)
            }
            //checks so the random number from computerMove doesn't override an existing spot
        } else {
            if (player == GameConstants.RED){
                setMove(GameConstants.RED, computerMove)
            }
        }

    }

    //function checking for winner in 2D array
    override fun checkForWinner(): Int {
        //horizontal check
        for (rowNum in 0 until 6) {
            for (numInRow in 0 until 3) {
                var playerColor: Int
                if (board[rowNum][numInRow] != 0) {
                    playerColor = board[rowNum][numInRow]
                    if (playerColor == 1 && board[rowNum][numInRow + 1] == playerColor && board[rowNum][numInRow + 2] == playerColor && board[rowNum][numInRow + 3] == playerColor) {
                        return playerColor + 2
                    } else if (board[rowNum][numInRow + 1] == playerColor && board[rowNum][numInRow + 2] == playerColor && board[rowNum][numInRow + 3] == playerColor) {
                        return playerColor
                    }
                }
            }

        }

        //vertical check
        for (colNum in 0 until 6) {
            for (numInCol in 0 until 3) {
                var playerColor :Int
                if (board[numInCol][colNum] != 0) {
                    playerColor = board[numInCol][colNum]
                    if (playerColor == 1 && board[numInCol + 1][colNum] == playerColor && board[numInCol + 2][colNum] == playerColor && board[numInCol + 3][colNum] == playerColor) {
                        return playerColor + 2
                    } else if (board[numInCol + 1][colNum] == playerColor && board[numInCol + 2][colNum] == playerColor && board[numInCol + 3][colNum] == playerColor) {
                        return playerColor
                    }
                }
            }
        }

        //diagonal left check
        for (diaCol in 0 until 3) {
            for (diaRow in 0 until 3) {
                var playerColor :Int
                if (board[diaRow][diaCol] != 0) {
                    playerColor = board[diaRow][diaCol]
                    if (playerColor == 1 && board[diaRow + 1][diaCol + 1] == playerColor && board[diaRow + 2][diaCol + 2] == playerColor && board[diaRow + 3][diaCol + 3] == playerColor) {
                        return playerColor + 2
                    } else if (board[diaRow + 1][diaCol + 1] == playerColor && board[diaRow + 2][diaCol + 2] == playerColor && board[diaRow + 3][diaCol + 3] == playerColor) {
                        return playerColor
                    }
                }
            }
        }

        //diagonal right check
        for (diaCol in 3 until 6) {
            for (diaRow in 0 until 3) {
                var playerColor :Int
                if (board[diaRow][diaCol] != 0) {
                    playerColor = board[diaRow][diaCol]
                    if (playerColor == 1 && board[diaRow + 1][diaCol - 1] == playerColor && board[diaRow + 2][diaCol - 2] == playerColor && board[diaRow + 3][diaCol - 3] == playerColor) {
                        return playerColor + 2
                    } else if (board[diaRow + 1][diaCol - 1] == playerColor && board[diaRow + 2][diaCol - 2] == playerColor && board[diaRow + 3][diaCol - 3] == playerColor) {
                        return playerColor
                    }
                }
            }
        }

        //tie condition
        var num = 0
        for (row in 0 until 6) {
            for (col in 0 until 6) {
                if (board[row][col] != 0) {
                    num += 1
                }
            }
        }

        //a way to stop the game from going on forever
        if (num == 36) {
            return 1
        }

        return 0
    }
}





