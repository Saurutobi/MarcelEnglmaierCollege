/**
 * 
 */
package ast;

/**
 * @author carr
 *
 */
public interface Visitor {
	public void visit(AddNode n);
	public void visit(SubNode n);
	public void visit(IdNode n);
}
