
/**
 * We are given n guests to be allocated to m equally sized tables (where n % m = 0) at a banquest.  
 * There are constraint of the form together(i,j) and apart(i,j) where
 * together(i,j) means that guests i and j must sit at the same table and
 * apart(i,j) means that guests i and j must sit at different tables. 
 * By default, guests can sit at any table with any other guest
 */

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import org.chocosolver.solver.*;
import org.chocosolver.solver.variables.*;
import org.chocosolver.solver.constraints.*;


public class Banquet 
{
	private Solver solver;
	private final int N_GUESTS;
    private final int M_TABLES;
    private final int TABLE_SIZE;
    
    private IntVar[] placement;
    
     
 
    public Banquet(String fname) throws IOException 
    {
    	try (Scanner sc = new Scanner(new File(fname))) 
    	{
    		N_GUESTS = sc.nextInt(); // number of guests
    		M_TABLES = sc.nextInt(); // number of tables
    		TABLE_SIZE = N_GUESTS / M_TABLES;
            solver = new Solver("banquet planner");
            
            placement = VF.enumeratedArray("placement", N_GUESTS, 1, M_TABLES, solver);
            
            
    	}
    }
}
