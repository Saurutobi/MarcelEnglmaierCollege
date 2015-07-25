/**
 * 
 */
package ast;

/**
 * @author carr
 *
 */
public class PrintVisitor implements Visitor {

	/* (non-Javadoc)
	 * @see ast.Visitor#visit(ast.AddNode)
	 */
	@Override
	public void visit(AddNode n) {
		System.out.print(" + ");
	}

	/* (non-Javadoc)
	 * @see ast.Visitor#visit(ast.SubNode)
	 */
	@Override
	public void visit(SubNode n) {
		System.out.print(" - ");
	}

	/* (non-Javadoc)
	 * @see ast.Visitor#visit(ast.IdNode)
	 */
	@Override
	public void visit(IdNode n) {
		System.out.print(n.getName());
	}

}
