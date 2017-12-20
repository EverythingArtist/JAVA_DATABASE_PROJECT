import java.util.*;
import javax.jdo.*;

@javax.jdo.annotations.PersistenceCapable

public class Company
{
	String name; // key

	HashSet<Laptop> makeLaptops = new HashSet<Laptop>(); 
	  // The set of laptops this company makes

	HashSet<Processor> makeProcessors = new HashSet<Processor>(); 
	  // The set of processors this company makes


	public Company(String s)
	{
		name = s;
	}

	public String toString()
	{
		return name;
	}
	
	public static Collection<Company> memoryProcessor(float c, int s, Query q)

	/* Returns the collection of all companies that make laptops that
	   have a processor clock speed of at least "c" GHz and a memory size of
	   at least "s" GB. Sort the result by name. */

	{
		q.setClass(Company.class);
		q.declareParameters("float x, int y");
		q.declareVariables("Laptop l");
		q.setFilter("this.makeLaptops.contains(l) && l.processor.clockSpeed >= x && l.memory.size >= y");
		q.setOrdering("this.name ascending");
		return (Collection<Company>) q.execute(c,s);

	}

	public static Collection<Company> differentCompanyProcessor(Query q)

	/* Returns the collection of all companies that make at least two laptops 
	   preinstalled with processors made by different companies. Sort the result by name. */

	{
		q.setClass(Company.class);
		q.declareVariables("Laptop l1; Laptop l2");
		q.setFilter("this.makeLaptops.size() >=2 && this.makeLaptops.contains(l1) && this.makeLaptops.contains(l2) && l1.processor.madeBy.name != l2.processor.madeBy.name");
		q.setOrdering("this.name ascending");
		return (Collection<Company>) q.execute();
	
	}
}