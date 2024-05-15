/********************************************************************************
 * Name: Melissa Santos, Garrett Lam
 * ID: A16286089, A16423003
 * Email: mesantos@ucsd.edu, g1lam@ucsd.edu
 * Sources used: Tutors
 * 
 * This file contains the main method and initializes important instance varibles
 * needed for the game to function properly. It also stores another important
 * method that is needed to determine who wins.
 */

import java.util.Scanner;

/**
 * This concrete class extends the RPSAbstract class and it contains 3 methods.
 * The methods intialize moves, determine who wins, and runs the game.
 */
public class RPS extends RPSAbstract {
    
    /**
     *Takes the parameter and sets it to the array of possible moves. It also
     *declares and intializes the player and cpu moves.
     *
     *@param moves - moves that can be done
     */
    public RPS(String[] moves) {
        this.possibleMoves = moves;
        this.playerMoves = new String[MAX_GAMES];
        this.cpuMoves = new String[MAX_GAMES];
    }

    /**
     * Takes the user move, the CPU move, and determines who wins.
     * @param playerMove - move of the player
     * @param cpuMove - move of the CPU
     * @return -1 for invalid move, 0 for tie, 1 for player win, 2 for cpu win
     */
    public int determineWinner(String playerMove, String cpuMove){
        int result = 0;
        //checks if the players move is valid using the isValidMove method
        if(isValidMove(playerMove) == false){
            result = INVALID_INPUT_OUTCOME;
        }
        //checks if the cpu move is valid using the isValidMove method
        else if (isValidMove(cpuMove) == false){
            result = INVALID_INPUT_OUTCOME;
        }
        //checks if the player and cpu have the same move which is a tie
        else if (playerMove == cpuMove){
            result = TIE_OUTCOME;
        }
        else { 
            //iterates through the possible moves array
            for (int i = 0; i < possibleMoves.length; i++){
                //finds the index of the playermove
                if (playerMove.equals(possibleMoves[i%possibleMoves.length])){
                    //finds the cpu move which is an element after the player move
                    if (cpuMove.equals(possibleMoves[(i+1)%possibleMoves.
                    length])){
                        result = PLAYER_WIN_OUTCOME;
                    }  
                }
                //finds the index of the cpu move
                else if (cpuMove.equals(possibleMoves[i%possibleMoves.length])){
                    //finds the player move 
                    if(playerMove.equals(possibleMoves[(i+1)%possibleMoves
                    .length])){
                        result = CPU_WIN_OUTCOME;
                    }
                }
            }
        }
        //returns the integer based on the outcome
        return result;  
    }

    /**
     * Main method that reads user input, generates cpu move, and plays game
     * 
     * @param args - arguments passed in from command line in String form
     */
    public static void main(String[] args) {
        // If command line args are provided use those as the possible moves
        String[] moves = new String[args.length];
        if (args.length >= MIN_POSSIBLE_MOVES) {
            for (int i = 0; i < args.length; i++) {
                moves[i] = args[i];
            }
        } else {
            moves = RPS.DEFAULT_MOVES;
        }
        // Create new game and scanner
        RPS game = new RPS(moves);
        Scanner in = new Scanner(System.in);

        
        
        while (true){
            //generates cpu move
            String gameCpu = game.genCPUMove();
            //takes in players move and stored in playerInput
            String playerInput = in.nextLine();
            //prints out the move
            System.out.println(PROMPT_MOVE);
            //checks if the player wants to quit game
            if (playerInput.equals("q")){
                //if so the game ends and prints corresponding messages
                game.end();
                //breaks out of while loop
                break;
            }
            //calls the play method
            game.play(playerInput, gameCpu);
        }
        
        //closes the scanner
        in.close();
    }
}