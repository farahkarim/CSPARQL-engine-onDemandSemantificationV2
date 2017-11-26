package eu.larkc.csparql.core.new_parser ;

import java.util.HashSet;
import java.util.Set;

public class KGSemanticDescription {
	private static Set<String> moleculeTypeSet = new HashSet();
	
	public static void updateSemanticDescription(final String mtype) {
		KGSemanticDescription.moleculeTypeSet.add(mtype);
	}

	public static boolean hasMoleculeType(final String mtype) {
		return KGSemanticDescription.moleculeTypeSet.contains(mtype);
	}
	
	public static Set<String> getSemanticDescription()
	{
		return KGSemanticDescription.moleculeTypeSet;
	}
	public static boolean isEmpty()
	{
		return KGSemanticDescription.moleculeTypeSet.isEmpty();
	}
}