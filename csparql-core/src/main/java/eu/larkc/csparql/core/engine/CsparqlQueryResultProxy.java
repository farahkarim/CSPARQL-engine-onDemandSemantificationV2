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
package eu.larkc.csparql.core.engine;

import java.util.Collection;
import java.util.Observable;

import eu.larkc.csparql.common.NamedObject;
import eu.larkc.csparql.common.RDFTable;
import eu.larkc.csparql.common.RDFTuple;

public class CsparqlQueryResultProxy extends Observable implements NamedObject {

	private String id;
	private String sparqlQueryId;
	private String cepQueryId;


	public String getId() {
		return this.id;
	}

	public CsparqlQueryResultProxy(String id) {
		this.id = id;
	}
	
	public CsparqlQueryResultProxy(String id, String sparqlQueryId,
			String cepQueryId) {
		super();
		this.id = id;
		this.sparqlQueryId = sparqlQueryId;
		this.cepQueryId = cepQueryId;
	}

	public void notify(Object result) {
		
		RDFTable quads = (RDFTable) result;
		
		setChanged();

//        Collection<RDFTuple> tuples = quads.getTuples();
//        for (RDFTuple tuple : tuples){
//            System.out.println(tuple.toString());
//        }

		this.notifyObservers(quads);

	}

	public String getSparqlQueryId() {
		return sparqlQueryId;
	}

	public void setSparqlQueryId(String sparqlQueryId) {
		this.sparqlQueryId = sparqlQueryId;
	}

	public String getCepQueryId() {
		return cepQueryId;
	}

	public void setCepQueryId(String cepQueryId) {
		this.cepQueryId = cepQueryId;
	}

	public void setId(String id) {
		this.id = id;
	}
}
