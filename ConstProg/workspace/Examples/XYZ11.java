
/**
 * how many solutions are there for the equation x + y + z = 11
 * where x, y and z are positive integers?
 */

import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.*;
import org.chocosolver.solver.constraints.*;
import org.chocosolver.solver.search.strategy.*;
import org.chocosolver.solver.trace.Chatterbox;
import org.chocosolver.solver.exception.ContradictionException;


public class XYZ11 
{
    public static void main(String args[]) throws ContradictionException 
    {
    	Solver solver = new Solver("x+y+z=11");
    	
    	IntVar x = VF.enumerated("x",0,11,solver); // NOTE: enumerated!
    	IntVar y = VF.enumerated("y",0,11,solver);
    	IntVar z = VF.enumerated("z",0,11,solver);
    	
    	solver.post(ICF.sum(new IntVar[]{x,y,z},VF.fixed(11,solver)));
    	solver.set(ISF.lexico_LB(new IntVar[]{x,y,z}));
    	
    	// print out all solutions    	
    	/*
	  	if (solver.findSolution()) 
	  	{
	  		do 
	  		{
	  			System.out.println("("+ x.getValue() +","+ y.getValue() +","+ z.getValue() +")");
	  		}
	  		while (solver.nextSolution()); 
	  	}
    	*/
    	
    	System.out.println("number of solutions: "+ solver.findAllSolutions());
    	//System.out.println("there is a solution: "+ solver.findSolution());
    	
    	Chatterbox.printStatistics(solver);
    }
}