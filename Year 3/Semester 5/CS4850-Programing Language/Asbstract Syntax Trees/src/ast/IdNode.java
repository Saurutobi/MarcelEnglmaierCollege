/**
 * 
 */
package ast;

/**
 * @author carr
 *
 */
public class IdNode implements ASTNode {
	
	private String name;

	/**
	 * @param name
	 */
	public IdNode(String name) {
		super();
		this.name = name;
	}

	@Override
	public void acceptDF(Visitor v) {
		v.visit(this);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

}
