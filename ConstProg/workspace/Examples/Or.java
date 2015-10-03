/**
 * An example of using LCF.or
 * 
 * How many dominos are there where 
 * - first dot is less than 2 OR
 * - first dot is greater than 4
 */

import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.*;
import org.chocosolver.solver.constraints.*;
import org.chocosolver.solver.search.strategy.*;
import org.chocosolver.solver.trace.Chatterbox;
import org.chocosolver.solver.exception.ContradictionException;


public class Or 
{
	public static void main(String args[]) throws ContradictionException 
	{
		Solver solver = new Solver("using LCF.or");
		
		IntVar v1 = VF.enumerated("v1",0,6,solver); // NOTE: enumerated
		IntVar v2 = VF.enumerated("v2",0,6,solver);
		
		Constraint c1 = ICF.arithm(v1,"<",2);
		Constraint c2 = ICF.arithm(v1,">",4);
		solver.post(LCF.or(new Constraint[]{c1,c2}));
		solver.post(ICF.arithm(v1,"<=",v2));
		solver.propagate();
		
		System.out.println(v1);
		System.out.println(v2);
		
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
