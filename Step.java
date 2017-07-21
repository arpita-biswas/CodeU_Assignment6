package CodeU_Assignment6;

/**
 * An object of a step class is a two-tuple (move_from, move_to) representing one move.
 *
 */
public class Step {
    int move_from;
    int move_to;
    
    Step(int move_from, int move_to){
        this.move_from = move_from;
        this.move_to = move_to;
    }
    
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("move from "+this.move_from+" to "+this.move_to);
        return str.toString();
    }
    
    @Override
    public boolean equals(Object o){
        Step obj = (Step) o;
        if(this.move_from == obj.move_from && this.move_to == obj.move_to){
            return true;
        }
        return false;
    }

}
