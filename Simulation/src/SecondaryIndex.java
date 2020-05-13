
public class SecondaryIndex {

	private Node nodes[];

	String[] globalIndex;

	public SecondaryIndex(int numOfNodes, int numOfRecords) {
		nodes = new Node[numOfNodes];
		for (int i = 0; i < numOfNodes; i++) {
			nodes[i] = new Node(numOfRecords);
		}
		globalIndex = new String[numOfNodes - 1];
	}

	public String[] getGlobalIndex() {
		return globalIndex;
	}

	public void setGlobalIndex() {

		for (int i = 1; i < nodes.length; i++) {
			globalIndex[i - 1] = nodes[i].getRecords()[0].getName();
		}
	}

	public String globalSearch(String searchName) {

		String toReturn = "";
		if (searchName.compareTo(globalIndex[0]) < 0) {

			// search in node 0
			toReturn += "Global Index: " + globalIndex[0] + "\n" + nodes[0].searchLocal(searchName) + "\nAt Node: 1";

		} else if (searchName.compareTo(globalIndex[globalIndex.length - 1]) >= 0) {
			// search in last node

			toReturn += "Global Secondary Index: " + globalIndex[globalIndex.length - 1] + "\n"
					+ nodes[globalIndex.length].searchLocal(searchName) + "\nAt Node: " + (globalIndex.length + 1);
		} else {
			for (int i = 1; i < globalIndex.length; i++) {

				if (searchName.compareTo(globalIndex[i]) < 0 && searchName.compareTo(globalIndex[i - 1]) >= 0) {
					// search in node i-1
					toReturn += "Global Index: " + globalIndex[i - 1] + "\n" + nodes[i].searchLocal(searchName)
							+ "\nAt Node: " + (i + 1);
					break;
				}
			}
		}

		return toReturn;

	}

	public Node[] getNodes() {
		return nodes;
	}

	public void setNodes(Node nodes[]) {
		this.nodes = nodes;
	}

	@Override
	public String toString() {

		String toReturn = "";
		for (int i = 0; i < nodes.length; i++) {
			toReturn += "Node: " + (i + 1) + "\n";
			toReturn += nodes[i].toString() + "\n\n\n";
		}
		return toReturn;

	}
}
