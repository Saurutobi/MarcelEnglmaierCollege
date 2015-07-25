/**
 * 
 */
package interpreter;

/**
 * @author carr
 *
 */
public class PrintVisitor implements Visitor
{
	@Override
	public void visit(AddNode n)
	{
		System.out.print(" + ");
	}

	@Override
	public void visit(SubNode n)
	{
		System.out.print(" - ");
	}
	
	@Override
	public void visit(MultiplyNode n)
	{
		System.out.print(" * ");
	}
	
	@Override
	public void visit(DivideNode n)
	{
		System.out.print(" / ");
	}

	@Override
	public void visit(IdNode n) {
		System.out.print(n.getName());
	}

}
