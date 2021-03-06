/**
 * Copyright 2011-2015 DEIB - Politecnico di Milano
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Acknowledgements:
 * We would like to thank Davide Barbieri, Emanuele Della Valle,
 * Marco Balduini, Soheila Dehghanzadeh, Shen Gao, and
 * Daniele Dell'Aglio for the effort in the development of the software.
 *
 * This work is partially supported by
 * - the European LarKC project (FP7-215535) of DEIB, Politecnico di
 * Milano
 * - the ERC grant “Search Computing” awarded to prof. Stefano Ceri
 * - the European ModaClouds project (FP7-ICT-2011-8-318484) of DEIB,
 * Politecnico di Milano
 * - the IBM Faculty Award 2013 grated to prof. Emanuele Della Valle;
 * - the City Data Fusion for Event Management 2013 project funded
 * by EIT Digital of DEIB, Politecnico di Milano
 * - the Dynamic and Distributed Information Systems Group of the
 * University of Zurich;
 * - INSIGHT NUIG and Science Foundation Ireland (SFI) under grant
 * No. SFI/12/RC/2289
 */
package eu.larkc.csparql.sparql.jena.ext;

import java.util.Map;

import com.hp.hpl.jena.datatypes.RDFDatatype;
import com.hp.hpl.jena.datatypes.TypeMapper;
import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.impl.PropertyImpl;
import com.hp.hpl.jena.rdf.model.impl.ResourceImpl;
import com.hp.hpl.jena.rdf.model.impl.StatementImpl;
import com.hp.hpl.jena.sparql.expr.NodeValue;
import com.hp.hpl.jena.sparql.function.FunctionBase3;


public class timestamp extends FunctionBase3 {

	static int i = 0;
	public static Map<Statement,Long> timestamps;

	//	@Override
	//	public NodeValue exec(NodeValue v) {
	//		String key = v.asString();
	//		if (timestamps.containsKey(key)) {
	//			NodeValue ts=NodeValue.makeInteger(timestamps.get(key));
	//			
	//			return ts;
	//		} else {
	//			return NodeValue.makeBoolean(false);
	//		}
	//	}

	@Override
	public NodeValue exec(NodeValue arg0, NodeValue arg1, NodeValue arg2) {

		Statement key;

		if(arg2.isLiteral()){
			String[] objectParts = arg2.asString().split("\\^\\^");
			TypeMapper tm = TypeMapper.getInstance();
			//			RDFDatatype d = tm.getTypeByName(objectParts[1]);
			RDFDatatype d = null;
			if (objectParts.length > 1) {
				d = tm.getTypeByName(objectParts[1]);
			} else {
				d = XSDDatatype.XSDstring;
			}
			Model model = ModelFactory.createDefaultModel();
			Literal lObject = model.createTypedLiteral(objectParts[0].replaceAll("\"", ""),d);
			key = new StatementImpl(new ResourceImpl(arg0.asString()), new PropertyImpl(arg1.asString()), lObject); 

			lObject = null;
			model = null;

		} else {
			key = new StatementImpl(new ResourceImpl(arg0.asString()), new PropertyImpl(arg1.asString()), new ResourceImpl(arg2.asString())); 

		}

		if (timestamps.containsKey(key)) {
			NodeValue ts=NodeValue.makeInteger(timestamps.get(key));

			return ts;
		} else {
			return NodeValue.makeBoolean(false);
		}
	}

}
