/**
 * 
 */
package ast;

import util.CalcException;

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
	public void acceptDF(Visitor v) throws CalcException {
		v.visit(this);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	@Override
	public Object accept(Visitor v) throws CalcException {
		return v.visit(this);
	}

}
