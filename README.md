# DistributedParallelDataStorageSystemAccess_Simulation
The Primary objective of this project is to understand and simulate the parallel data storage system access.
The Project will cover how the indexing is done over distributed databases for efficient query retrieval i.e. creation of Global
and local indices over multiple records which are usually stored across different nodes. The concept of Global primary index and 
secondary index will be introduced, and their applicability will be simulated. The distributed storage systems are useful for efficient
access of record storage and retrieval over billions of data. Indexing over them is an overhead as records are over multiple tablespaces.
Therefore, we create global and local indices in order to make the query search efficient. The simulation is being done using Java by 
creating simple database. The Simulation of Global primary index will be done, i.e. how the records are partitioned and how the search 
is guided from Query → Global Primary Index, Global Secondary Index -→ node (local primary index).
