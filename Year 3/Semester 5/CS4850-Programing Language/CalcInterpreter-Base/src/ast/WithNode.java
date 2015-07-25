/**
 * 
 */
package ast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.CalcException;

/**
 * @author carr
 *
 */
public class WithNode implements ASTNode {

	private String var;
	private ASTNode def;
	private ASTNode body;
	
	/**
	 * @param varDefs
	 * @param body
	 */
	public WithNode(String var, ASTNode def, ASTNode body) {
		super();
		this.var = var;
		this.def = def;
		this.body = body;
	}

	/* (non-Javadoc)
	 * @see ast.ASTNode#acceptDF(ast.Visitor)
	 */
	@Override
	public void acceptDF(Visitor v) throws CalcException {
		v.visit(this);
	}

	/* (non-Javadoc)
	 * @see ast.ASTNode#accept(ast.Visitor)
	 */
	@Override
	public Object accept(Visitor v) throws CalcException {
		return v.visit(this);
	}

	/**
	 * @return the body
	 */
	public ASTNode getBody() {
		return body;
	}

	/**
	 * @return the var
	 */
	public String getVar() {
		return var;
	}

	/**
	 * @return the def
	 */
	public ASTNode getDef() {
		return def;
	}

}
