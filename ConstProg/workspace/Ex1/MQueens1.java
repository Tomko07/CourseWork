
/**
 * Place as many queens as possible so that no queen can take another,
 * and no more queens can be placed on the board without being taken.
 */

import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.*;
import org.chocosolver.solver.constraints.*;
import org.chocosolver.solver.trace.Chatterbox;
import org.chocosolver.solver.exception.ContradictionException;


public class MQueens1 
{
	public static void main(String args[]) throws ContradictionException 
	{
		Solver solver = new Solver("m-queens");
		
		final int N = 5;
		IntVar[] queens = VF.enumeratedArray("queens", N, 0, N, solver);	// queens[i] = column where the queen is placed
		
		solver.post(ICF.alldifferent(queens));	// no two queens can be placed on the same column
		
		for(int i = 0; i < N-1; i++)
			for(int j = i+1; j < N; j++)
				solver.post(ICF.distance(queens[i], queens[j], "!=",j-i));	// no two queens can be placed on the same diagonal
		
		solver.propagate();
		
		/* FANCY OUTPUT OF ALL THE SOLUTIONS
		if(solver.findSolution())
		{
			int min = N;
			do
			{
				int placed = 0;
				for(int row = 0; row < N; row++)
				{
					for(int col = 1; col <= N; col++)
						if(queens[row].getValue() == col)
						{
							System.out.print("Q ");
							placed++;
						}
						else
							System.out.print(". ");
					System.out.println();
				}
				System.out.println();
				if(placed < min)
					min = placed;
			}
			while(solver.nextSolution());
			System.out.println("min nb of queens: " + min);
		}
		*/
		solver.findAllSolutions();
		Chatterbox.printStatistics(solver);
	}
}
