/**
 * Done 
 */
package jeopardyLab;

/**
 * @author Akash Chandra & William Spencer
 * Teacher Miss Denna PER 7
 *
 * Created Date: 11-19-2018
 */
public class Player {

	private int money;
	private String name;
	
	public Player(String n)
	{
		this.name = n;
		this.money = 0;
	}
	
	public int add(int m)
	{
		this.money += m;
		return this.money;
	}
	
	//Pre-generated Methods

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name + "\t$" + money;
	}

	/**
	 * @return the money
	 */
	public int getMoney() {
		return money;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
}