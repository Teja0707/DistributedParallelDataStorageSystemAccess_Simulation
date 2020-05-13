public class PrimaryIndex {

	private Node nodes[];

	Integer[] globalIndex;

	public PrimaryIndex(int numOfNodes, int numOfRecords) {
		nodes = new Node[numOfNodes];
		for (int i = 0; i < numOfNodes; i++) {
			nodes[i] = new Node(numOfRecords);
		}
		globalIndex = new Integer[numOfNodes - 1];
	}

	public Integer[] getGlobalIndex() {
		return globalIndex;
	}

	public void setGlobalIndex() {

		for (int i = 1; i < nodes.length; i++) {
			globalIndex[i - 1] = nodes[i].getRecords()[0].getSSN();
		}
	}

	public String globalSearch(Integer searchSSN) {

		String toReturn = "";
		if (searchSSN < globalIndex[0]) {

			// search in node 0
			toReturn += "Global Index: " + globalIndex[0] + "\n" + nodes[0].searchLocal(searchSSN) + "\nAt Node: 1";

		} else if (searchSSN >= globalIndex[globalIndex.length - 1]) {
			// search in last node

			toReturn += "Global Primary Index: " + globalIndex[globalIndex.length - 1] + "\n"
					+ nodes[globalIndex.length].searchLocal(searchSSN) + "\nAt Node: " + (globalIndex.length + 1);
		} else {
			for (int i = 1; i < globalIndex.length; i++) {

				if (searchSSN < globalIndex[i] && searchSSN >= globalIndex[i - 1]) {
					// search in node i-1
					toReturn += "Global Index: " + globalIndex[i - 1] + "\n" + nodes[i].searchLocal(searchSSN)
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
	
	public String beforePartition() {

		
		String toReturn = "";
		for (int i = 0; i < nodes.length; i++) {
			toReturn += nodes[i].beforePartition() + "\n";
		}
		return toReturn;

	}
}
