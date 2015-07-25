/**
 * 
 */
package interpreter;

/**
 * @author carr
 *
 */
public interface Visitor
{
	public void visit(AddNode n);
	public void visit(SubNode n);
	public void visit(MultiplyNode n);
	public void visit(DivideNode n);
	public void visit(IdNode n);
}
