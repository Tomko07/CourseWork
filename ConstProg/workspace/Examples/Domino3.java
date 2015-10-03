
/**
 * How many dominos are there?
 * Expressed as a CP
 * Can we prove this is correct using a counting argument?
 */

import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.*;
import org.chocosolver.solver.constraints.*;
import org.chocosolver.solver.search.strategy.*;//IntStrategyFactory;
import org.chocosolver.solver.trace.Chatterbox;
import org.chocosolver.solver.exception.ContradictionException;

public class Domino3 
{
	public static void main(String args[]) throws ContradictionException 
	{
		Solver solver = new Solver("domino3");
		
		IntVar v1 = VF.bounded("v1",0,6,solver); // NOTE: bound!
		IntVar v2 = VF.bounded("v2",0,6,solver);
		
		solver.post(ICF.arithm(v1,"<=",v2));
		
		System.out.println("Printing the solver object:\n" + solver);
		//solver.propagate();
		
		//System.out.println(solver);
		solver.set(ISF.lexico_LB(new IntVar[]{v1,v2})); // Variable ordering
		
		// find all solutions
		if (solver.findSolution()) 
		{
			do 
			{
				System.out.println("["+v1.getValue() +" : "+ v2.getValue() +"]");
			}
			while (solver.nextSolution());
		}
		
		Chatterbox.printStatistics(solver);
	}
}


