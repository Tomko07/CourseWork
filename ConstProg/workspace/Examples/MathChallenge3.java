
/**
 * Maths challenge 2004-2005 J5
 * 
 * My bank card has a 4 digit pin, abcd. 
 * I use the following facts to help me remember it:
 * - no two digits are the same
 * - the 2-digit number cd is 3 times the 2-digit number ab
 * - the 2-digit number da is 2 times the 2-digit number bc
 * What are the values of a, b, c, and d?
 */


import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.*;
import org.chocosolver.solver.constraints.*;
import org.chocosolver.solver.trace.Chatterbox;
import org.chocosolver.solver.exception.ContradictionException;


public class MathChallenge3 
{
	public static void main(String[] args) throws ContradictionException 
	{
		Solver solver = new Solver("Math Challenge");

		IntVar a = VF.enumerated("a",0,9,solver);
		IntVar b = VF.enumerated("b",0,9,solver);
		IntVar c = VF.enumerated("c",0,9,solver);
		IntVar d = VF.enumerated("d",0,9,solver);

		solver.post(ICF.alldifferent(new IntVar[]{a,b,c,d}));

		IntVar ab = VF.enumerated("ab",0,99,solver);
		IntVar bc = VF.enumerated("bc",0,99,solver);
		IntVar cd = VF.enumerated("cd",0,99,solver);
		IntVar da = VF.enumerated("da",0,99,solver);

		solver.post(ICF.scalar(new IntVar[]{a,b},new int[]{10,1},"=",ab)); // ab = 10.a + b
		solver.post(ICF.scalar(new IntVar[]{b,c},new int[]{10,1},"=",bc)); // bc = 10.b + c
		solver.post(ICF.scalar(new IntVar[]{c,d},new int[]{10,1},"=",cd)); // cd = 10.c + d
		solver.post(ICF.scalar(new IntVar[]{d,a},new int[]{10,1},"=",da)); // da = 10.d + a

		// Instead of using ICF.arithm(VF.scale(...),...), use ICF.times.
		solver.post(ICF.times(ab,3,cd)); // 3.ab = cd
		solver.post(ICF.times(bc,2,da)); // 2.bc = da

		solver.propagate();

		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		System.out.println(d);

		System.out.println(ab);
		System.out.println(bc);
		System.out.println(cd);
		System.out.println(da);

		/*
	      if (solver.findSolution()) 
	      {
	    	  do 
	    	  {
	    		  System.out.println(a.getValue() +","+ b.getValue() +","+ c.getValue() +","+ d.getValue());
	    	  }
	    	  while (solver.nextSolution());
	      }
	      */

	      System.out.println("there is a solution: "+ solver.findSolution());

	      System.out.println(a);
	      System.out.println(b);
	      System.out.println(c);
	      System.out.println(d);
	 
	      Chatterbox.printStatistics(solver);

	}
}
