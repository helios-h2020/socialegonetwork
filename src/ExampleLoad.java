import contextualegonetwork.Context;
import contextualegonetwork.ContextualEgoNetwork;
import contextualegonetwork.Edge;
import contextualegonetwork.Utils;
import contextualegonetwork.dataExamples.DefaultContextData;

public class ExampleLoad {

	public static void main(String[] args) {
		Utils.development = false;//disable logging and make exceptions try to forcefully continue execution
		
		ContextualEgoNetwork egoNetwork = ContextualEgoNetwork.createOrLoad("user-00001", null);
		
		//assert that deserialization loads the ego correctly
		System.out.println(egoNetwork.testattr);
		System.out.println(egoNetwork.getEgo().getId());
		System.out.println(egoNetwork.getEgo().getData());
		
		//assert that deserialization finds contexts (these aren't loaded yet)
		System.out.println(egoNetwork.getContexts());
		//assert that deserialization identifies the same data objects
		System.out.println(egoNetwork+" == "+egoNetwork.getContexts().get(0).getContextualEgoNetwork());
		//assert that deserialization works for nested data types
		System.out.println(((DefaultContextData)egoNetwork.getContexts().get(0).getData()).getName());
		//assert that deserialization works for arrays (it would throw a nullptr exception if it didn't work)
		System.out.println(egoNetwork.getContexts().get(1).getTimeCounter()[6][23]);
		
		//print the edges of all contexts
		for(Context context : egoNetwork.getContexts()) {
			context.cleanup();
			System.out.println("Context: "+context.getData().toString());
			for(Edge edge : context.getEdges()) {
				System.out.println(edge.getSrc().getData()+" -> "+edge.getDst().getData());
			}
			System.out.println();
		}
	}
}