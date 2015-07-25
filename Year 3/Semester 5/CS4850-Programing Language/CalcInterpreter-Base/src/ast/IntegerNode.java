/**
 * 
 */
package ast;

import util.CalcException;

/**
 * @author carr
 *
 */
public class IntegerNode implements ASTNode {

	private int value;
	
	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param value
	 */
	public IntegerNode(int value) {
		super();
		this.value = value;
	}

	/* (non-Javadoc)
	 * @see ast.ASTNode#acceptDF(ast.Visitor)
	 */
	@Override
	public void acceptDF(Visitor v) throws CalcException {
		System.out.print(value);
	}

	/* (non-Javadoc)
	 * @see ast.ASTNode#accept(ast.Visitor)
	 */
	@Override
	public Object accept(Visitor v) throws CalcException {
		return v.visit(this);
	}

}
