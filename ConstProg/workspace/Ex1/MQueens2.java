
/**
 * Place the minimum number of queens so that no queen can take another,
 * and no more queens can be placed on the board without being taken.
 */

import org.chocosolver.solver.ResolutionPolicy;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.*;
import org.chocosolver.solver.constraints.*;
import org.chocosolver.solver.trace.Chatterbox;
import org.chocosolver.solver.exception.ContradictionException;


public class MQueens2 
{
	public static void main(String args[]) throws ContradictionException 
	{
		Solver solver = new Solver("m-queens");
		
		final int N = 5;
		/*
		// rows[i][j] = 1 if and only if there is a queen on cell (i,j)
		IntVar[][] rows = VF.enumeratedMatrix("board", N, N, new int[]{0,1}, solver);
		// columns[i][j] = 1 if and only if there is a queen on cell (j,i)
		IntVar[][] columns = VF.enumeratedMatrix("board", N, N, new int[]{0,1}, solver);
		
		// to make sure that the rows matrix is the same as the reversed columns matrix
		for(int i = 0; i < N; i++)
			for(int j = 0; j < N; j++)
				solver.post(ICF.arithm(rows[i][j], "=", columns[j][i]));
		
		for(int i = 0; i < N; i++)
		{
			// no two queens can be placed on the same row
			solver.post(ICF.sum(rows[i], "<=", VF.fixed(1, solver)));	
			// no two queens can be placed on the same column
			solver.post(ICF.sum(columns[i], "<=", VF.fixed(1, solver)));
		}
		
		IntVar[][] diagonals = VF.enumeratedMatrix("diagonals", N, N, new int[]{0,1},solver);
		
		for(int i = N-1; i >= 0; i++)
			for(int j = 0; j < N; j++)
				solver.post(ICF.arithm(diagonals[i][j], OP, CSTE));
		
		// number of queens placed on the board
		IntVar queensNb = VF.bounded("queensNb",0,N,solver);
		
		*/
		IntVar[] queens = VF.enumeratedArray("queens", N, 0, N, solver);	// queens[i] = column where the queen is placed
		
		solver.post(ICF.alldifferent(queens));	// no two queens can be placed on the same column
		
		for(int i = 0; i < N-1; i++)
			for(int j = i+1; j < N; j++)
				solver.post(ICF.distance(queens[i], queens[j], "!=",j-i));	// no two queens can be placed on the same diagonal
		
		//ntVar[] queensPlaced = VF.boundedArray("queensNb",N,0,1,solver);
		IntVar queensNb = VF.enumerated("queensNb",0,N,solver);
		
		for(int i = 0; i < N-1; i++)
		{
			Constraint c1 = ICF.arithm(queens[i], ">" ,VF.fixed(0, solver));
			Constraint c2 = ICF.scalar(new IntVar[]{queensNb, VF.fixed(0, solver)}, new int[]{1,0}, "=" ,queensNb);
			LCF.ifThen(c1, c2);
		}
		
		solver.propagate();

		solver.findOptimalSolution(ResolutionPolicy.MINIMIZE, queensNb);
		//solver.findOptimalSolutions(ResolutionPolicy.MINIMIZE, queensNb, true);
		
		/*
		if(solver.findSolution())
		{
			int min = N;
			do
			{
				int placed = 0;
				*/
				for(int row = 0; row < N; row++)
				{
					for(int col = 1; col <= N; col++)
						if(queens[row].getValue() == col)
						{
							System.out.print("Q ");
							//placed++;
						}
						else
							System.out.print(". ");
					System.out.println();
				}
				System.out.println();
				/*
				if(placed < min)
					min = placed;
			}
			while(solver.nextSolution());
			System.out.println("min nb of queens: " + min);
		}
	*/
		Chatterbox.printStatistics(solver);
	}
}
