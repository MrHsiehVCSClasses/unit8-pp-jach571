package u8pp;


public class TicTacToeBoard {

    String[][] data;

    public TicTacToeBoard(String[][] data) {
        this.data = data;
    }

    public String toString() {
        String output = "";
        for(int r = 0; r < data.length; r++) {
            if(r != 0) {
                output += "\n";
                output += "-+".repeat(data[r].length - 1);
                output += "-";
                output += "\n";
            }
            for(int c = 0; c < data[r].length; c++) {
                if(c != 0) {
                    output += "|";
                }
                output += data[r][c];
            }
        }
        return output;
    }

    public boolean hasWin() {
        return hasDiagonalWin() || hasHorizontalWin() || hasVerticalWin();
    }

    /**
     * 
     * @return : Returns a boolean. Returns the true if the board has a win in a horizontal pattern and returns false otherwise.
     */
    public boolean hasHorizontalWin(){
        int score = 0;
        boolean result = false;

        for (int row = 0; row < this.data.length; row++){
            for (int col = 0; col < this.data[0].length; col++){
                if(this.data[row][col].equals("X")){
                    score += 1;
                } else if(this.data[row][col].equals("O")){
                    score -= 1;
                } else{
                    score += 0;
                }

                if(score == this.data.length || score == (this.data.length * -1)){
                    result = true;
                }
            }
            score = 0;
        }
        return result;
    }

    /**
     * 
     * @return : Returns a boolean. Returns the true if the board has a win in a vertical pattern and returns false otherwise.
     */
    public boolean hasVerticalWin(){
        int score = 0;
        boolean result = false;

        for (int col = 0; col < this.data[0].length; col++){
            for (int row = 0; row < this.data.length; row++){
                if(this.data[row][col].equals("X")){
                    score += 1;
                } else if(this.data[row][col].equals("O")){
                    score -= 1;
                } else{
                    score += 0;
                }

                if(score == this.data.length || score == (this.data.length * -1)){
                    result = true;
                }
            }
            score = 0;
        }
        return result;
    }

    /**
     * 
     * @return : Returns a boolean. Returns the true if the board has a win in a diagonal pattern and returns false otherwise.
     */
    public boolean hasDiagonalWin(){
        int score = 0;

        //for loop to check in the normal diagonal pattern
        for (int pos = 0; pos < this.data.length; pos++){
            if(this.data[pos][pos].equals("X")){
                score += 1;
            } else if(this.data[pos][pos].equals("O")){
                score -= 1;
            } else{
                score += 0;
            }

            if(score == this.data.length || score == (this.data.length * -1)){
                return true;
            }   
        }
        score = 0; 

        //for loop to check in the reverse diagonal pattern
        for (int pos = 0; pos < this.data.length; pos++){
            if(this.data[pos][this.data.length - pos - 1].equals("X")){
                score += 1;
            } else if(this.data[pos][this.data.length - pos - 1].equals("O")){
                score -= 1;
            } else{
                score += 0;
            }

            if(score == this.data.length || score == (this.data.length * -1)){
                return true;
            }   
        }
        return false;    
    }
}