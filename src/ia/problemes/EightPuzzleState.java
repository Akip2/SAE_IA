package ia.problemes;

import java.util.Arrays;
import java.util.Collections; 
import java.util.Random;

import ia.framework.common.Action;
import ia.framework.common.State;
import ia.framework.recherche.HasHeuristic;

/**
 * Représente l'état d'un jeux taquin
 * <p>Les transition son réaliser avec </p>  
 */
public class EightPuzzleState extends State implements HasHeuristic{

    // Le puzzle : tableau de 9 entiers
    // Avec board[0] en haut à gauche board[8] en bas à droite.
    // Les valeurs représentent les pièces, avec 0 pour la case vide 

    private int[] board = null;

    // pour créer de puzzles aléatoires
    // pour recréer le même état de départ fixer une graine aléatoire
    private Random rng = new Random(); 

    /**
     * Générer un état avec un puzzle aléatoire
     */
    public EightPuzzleState() {
        board = randomPuzzle();
    }
    
    /**
     * Générer état avec un puzzle donné,
     */
	public EightPuzzleState(int[] b) {
        board = b.clone();
	}

    /**
     * @return Le puzzle de cette état
     */
    public int[] getBoard(){
        return board;
    }

	public EightPuzzleState cloneState() {
        return new EightPuzzleState(board);
	}

	public boolean equalsState(State o) {
        return Arrays.equals(board,((EightPuzzleState)o).getBoard());
	}
    
	public int hashState() {
		return Arrays.hashCode(board);
	}

	@Override
	public String toString() {
        return Arrays.toString(board);
        //return board[0] + " " + board[1] + " " + board[2] + "\n"
		//		+ board[3] + " " + board[4] + " " + board[5] + "\n"
		//		+ board[6] + " " + board[7] + " " + board[8];
	}

    
    /**
     * Test la possibilité de faire une action dans cet état
     * @param a L'action à tester
     * @return Vrai si l'action est possible  
     */
    
    public boolean isLegal(Action a){
        int pos = getPositionOf(0);
		if (a == EightPuzzle.LEFT)
			return (getXCoord(pos) != 0);
		if (a == EightPuzzle.RIGHT)
			return (getXCoord(pos) != 2);
		if (a == EightPuzzle.UP)
            return  (getYCoord(pos) != 0);
		if (a == EightPuzzle.DOWN)
			return  (getYCoord(pos) != 2);
		return false;
    }

    /**
     * Déplacer le carré vide à droite 
     */
    
    public void moveGapRight() {
		int gapPos = getGapPosition();
		int x = getXCoord(gapPos);
		int y = getYCoord(gapPos);
		if (x != 2) {
			setValue(x, y, getValueAt(x + 1, y));
			setValue(x + 1, y, 0);
		}
	}

    /**
     * Déplacer le carré vide à gauche 
     */
    
	public void moveGapLeft() {
		int gapPos = getGapPosition();
		int x = getXCoord(gapPos);
		int y = getYCoord(gapPos);
		if (x != 0) {
			setValue(x, y, getValueAt(x - 1, y));
			setValue(x - 1, y, 0);
		}
	}

    /**
     * Déplacer le carré vide en bas
     */
    
	public void moveGapDown() {
		int gapPos = getGapPosition();
		int x = getXCoord(gapPos);
		int y = getYCoord(gapPos);
		if (y != 2) {
			setValue(x, y, getValueAt(x, y + 1));
			setValue(x, y + 1, 0);
		}
	}
    
    /**
     * Déplacer le carré vide en haut
     */
    
	public void moveGapUp() {
		int gapPos = getGapPosition();
		int x = getXCoord(gapPos);
		int y = getYCoord(gapPos);
		if (y != 0) {
			setValue(x, y, getValueAt(x, y - 1));
			setValue(x, y - 1, 0);
		}
	}


    /**
     * {@inheritDoc}
     * <p>L'heuristique choisie est la somme des distance (manathan)
     * des pièces à leur position finales (cf. ci-dessous).</p>
     */
    
    public double getHeuristic(){
        return manathanDistance();
    }
    
    //
    // API privée, manipulation du jeux
    //
    
    // Calcule la distance entre le puzzle et la solution
    // https://en.wikipedia.org/wiki/Taxicab_geometry
    private double manathanDistance(){
        double result = 0;
		for (int val = 1; val <= 8; val++) {
			int locCurr = getPositionOf(val);
			int locGoal = EightPuzzle.GOAL_STATE.getPositionOf(val);
			result += Math.abs(getXCoord(locGoal)-getXCoord(locCurr));
			result += Math.abs(getYCoord(locGoal)-getYCoord(locCurr));
        }
        return result;
    }
    

    // Générer un puzzle aléatoire
    private int[] randomPuzzle(){
        
        int[] b = new int[] { 0,1,2,3,4,5,6,7,8 };
        do {
            shuffle(b);
        } while( ! checkSolvable(b) );

        return b;
    }
    
    // Vérifier que le puzzle généré aléatoirement a une solution
    // https://en.wikipedia.org/wiki/15_Puzzle
    private boolean checkSolvable( int[] b ) {
        int inversions = 0;
        for(int i=0;i<b.length;i++)
            for(int j=i+1;j<b.length;j++)
                if(b[i]!=0 && b[j]!=0 && b[j]>b[i])
                    inversions++;
        if(inversions%2 == 1)
            return false;
        return true;
    }

    // Mélanger le puzzle pour la génération aléatoire
    private void shuffle(int[] b) {
        for (int i = b.length; i > 1; i--) {
            int j = rng.nextInt(i);
            int temp = b[i-1];
            b[i-1] = b[j];
            b[j] = temp;
        }
    }
    
	private int getXCoord(int pos) {
		return pos % 3;
	}

	private int getYCoord(int pos) {
		return pos / 3;
	}

	private int getPosition(int x, int y) {
		return x + 3 * y;
	}

	private int getValueAt(int x, int y) {
		return board[getPosition(x, y)];
	}

	private int getGapPosition() {
		return getPositionOf(0);
	}

	private int getPositionOf(int val) {
		for (int i = 0; i < 9; i++)
			if (board[i] == val)
				return i;
		return -1;
	}

	private void setValue(int x, int y, int val) {
		int pos = getPosition(x, y);
		board[pos] = val;
	}
    
}
