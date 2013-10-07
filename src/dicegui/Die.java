package dicegui;
import java.util.Random;

/**  	
 * Sean Lyons
 * 09/03/2013
 * @author Sean Lyons
 */

public class Die
	implements Comparable<Die>{	
	/*private instance variables */
	public int value;
	public int numsides;
		
	/**compareTo()
	 * @param _die
	 * @return integer
	 */
	@Override
	public int compareTo(Die _die) {
		return(this.value - _die.value); //Will Provide Ascending-order Sorting
	}

	/**toString() method
	 * @return String
	 */
	@Override
	public String toString(){
		return "\n \t Number of Sides: " + 
				numsides +
				"\n \t Top Surface Value: " +
				value;
	}
	
	/**equals() method 
	 * @param o
	 * @return boolean
	 */
	public boolean equals(Object o){
		return (((Die) o).numsides == this.numsides) && 
				(((Die) o).value == this.value);
	}
	
	/**hashcode() 
	 * 
	 * @return integer
	 */
	public int hashcode(){
		int result = 13;
		result = 29*result + value;
		result = 29*result + numsides;
		return result;
	}
	
	/**Die Object Default Constructor
	*/
	public Die() {
		this(6); //default die has 6 sides
	}
	
	/**Die Object Non-default Constructor
	 * @param n integer
	 */

	public Die(int n){
		if(n<=3){
			throw new IllegalArgumentException("Too Few Sides Assigned to Die");
		}
		
		Random rnd = new Random();
		int randnum = rnd.nextInt(n) + 1; //generate a single random number and assign
		value = randnum;
		numsides = n;
	}
	
	/*roll the die and return the resulting top surface value
	 * @return integer
	 */
	public int roll() {
		Random rnd = new Random();
		int randnum = rnd.nextInt(numsides)+1;
		value = randnum;
		return randnum;
	}
	
	/*Get current value on top surface of Die
	 * @param No Parameters
	 * @return Return Current Top Surface Value of Die
	 */
	public int getTop() {
		return value;
	}
	
	/**Loaded Die Subclass
	 * 9/07/2013
	 * @author Sean Lyons
	 */
	public class LoadedDie
		extends Die{
		
		/*Instance Variables for LoadedDie*/
		public int LoadedSide; //decides which side of die will have weighted probability of being the die's value 
		public double LoadPercentage; //decides the probability that LoadedSide will be the die's value
		
		/**LoadedDie default constructor
		 */
		public LoadedDie(){
			this(6, 6, (100/6)); //A Default Loaded Die will be fair
		}
                
                public LoadedDie(int n){
                    this(n, 6, (100/6));
                }
                
		
		/**LoadedDie Non-default constructor
		 * @param n _LoadedSide _LoadPercentage
		 */
		public LoadedDie(int n, int _LoadedSide, double _LoadPercentage){
			if(n<=3)
				throw new IllegalArgumentException("Too Few Sides Assigned to Die");
			numsides = n;
			LoadedSide = _LoadedSide; 
			LoadPercentage = _LoadPercentage;
			Random num = new Random();
			if(num.nextInt(100) + 0 <= (LoadPercentage - (100/n)))  //100/n corrects for extra 1/n probability given to _LoadedSide by following else statement
				value = _LoadedSide;
			else{
				Random newnum = new Random();
				value = newnum.nextInt(n) + 1; //Could be value _LoadedSide, so we correct the probability in above if-statement
			}
		}
		
                public void setTop(int x){
                    this.value = x;
                }
		
		/**hashcode()
		 * @return integer
		 */
		@Override
		public int hashcode(){
			int result = 19;
			result = result*31 + LoadedSide;
			result = result*31 + (int) LoadPercentage;
			result = result*31 + numsides;
			result = result*31 + value;
			return result;
		}
		
		/**equals()
		 * @param o
		 * @return boolean
		 */
		@Override
		public boolean equals(Object o){
			return(super.equals((Die) o)&&
					(LoadedSide == ((LoadedDie) o).LoadedSide)&&
					(LoadPercentage == ((LoadedDie) o).LoadPercentage));						
		}
		
		/**toString() 
		 * @return String
		 */
		@Override
		public String toString(){
			return("\t" + super.toString() +
					"\n \t Loaded Side: " + LoadedSide +
					"\n \t Load Percentage: " + LoadPercentage);			
		}
		
		/**roll()
		 * @return integer
		 */
		@Override
		public int roll(){
			//Probabilities handled identically to the Non-default Constructor for this class
			Random num = new Random();
			if(num.nextInt(100) + 0 <= (LoadPercentage - (100/numsides)))  
				value = this.LoadedSide;
			else{
				Random newnum = new Random();
				value = newnum.nextInt(numsides) + 1;
			}
			
			return value;
		}

		
	}

}
