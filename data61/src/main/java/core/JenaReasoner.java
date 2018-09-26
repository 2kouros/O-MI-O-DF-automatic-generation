package core;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.jena.ext.com.google.common.collect.Lists;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntResource;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.iterator.ExtendedIterator;

public class JenaReasoner {
	
	private static final String filename="C:\\Users\\Kristi.KristianKuro-PC\\Desktop\\object.owl";  //Ontology file
	private static final	ArrayList<String> description= new ArrayList<String>();
	
    public static  boolean sparqlQuery(String id) {
    	
    	String object=determiner(id);
        FileManager.get().addLocatorClassLoader(JenaReasoner.class.getClassLoader());
        Model model = FileManager.get().loadModel(filename); //Create a Jena model for the Ontology
        
         //SPARQL query
        String queryStringObjects = 
               
        		"PREFIX sosa: <http://www.w3.org/ns/sosa/> " +
				"PREFIX qu: <http://purl.oclc.org/NET/ssnx/qu/qu#> " +          
				"SELECT  ?Sensor ?result ?observable ?unit  "
        		
		+ "WHERE { " 
        		
		+ "?Sensor sosa:isHostedBy ?Platform . "
		+ "?Sensor  sosa:hasSimpleResult ?result ."
		+ "?Sensor sosa:observes ?observable ."
		+ "?observable qu:unit ?unit ."
		
		+ "FILTER (STR(?Platform) = \"http://www.semanticweb.org/kristian/ontologies/test2#" + object + "\") . " +
		"}";
         
    
        Query queryobjects = QueryFactory.create(queryStringObjects);
        QueryExecution qexec = QueryExecutionFactory.create(queryobjects, model);
        
        try {
            ResultSet results = qexec.execSelect();
            
            while ( results.hasNext() ) {
             QuerySolution soln = results.nextSolution(); 
               
              Iterator<String> itVars = soln.varNames();

                while (itVars.hasNext()) {
                	
                               String szVar = itVars.next().toString();
                               String szVal = soln.get(szVar).toString();
                               String sh = szVal.substring(szVal.indexOf("#")+1);
                              description.add(sh);
                             
                               }  
                             }    
                         } 
        
        finally 
        {
            qexec.close();
        }
        
		return true;
      }
    
    
       public static String determiner(String id) {
		
    	boolean individual;
    	
    	OntModel ontModel = ModelFactory.createOntologyModel();
        InputStream in = FileManager.get().open(filename);
        
        if (in == null) {
            throw new IllegalArgumentException( "File: " + filename + " not found");
        }
        
        ontModel.read(in, "");
        Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
        reasoner = reasoner.bindSchema(ontModel);
        OntModelSpec ontModelSpec = OntModelSpec.OWL_DL_MEM;
        ontModelSpec.setReasoner(reasoner);
        
        // Create ontology model with reasoner support
        OntModel model = ModelFactory.createOntologyModel(ontModelSpec, ontModel);
        
        Property property= model.createProperty("http://www.w3.org/ns/sosa/hosts");
        Property sameAs=model.createProperty("sameAs");
        Individual individ=model.getIndividual("http://www.semanticweb.org/kristian/ontologies/test2#" + id);
        OntResource same= individ.getSameAs();
        OntClass ontclass=  individ.getOntClass();
        ExtendedIterator<? extends OntResource> iter = ontclass.listInstances();
     
	if( individ.hasProperty(property)==false && same != null && !same.equals(individ))
    {  
		
		id=same.getLocalName();
		
		}
	
	else if (individ.hasProperty(property)==false  )
	{
		while(iter.hasNext()){
			OntResource next=iter.next();
			String name=next.getLocalName();
			if(!name.equals(id))
			{  id=name;
			
			}
			
		}
		
		
	}
	
	 if (id == null) {
         throw new IllegalArgumentException( "Device with ID: " + id + " not found");
     }
	 
    return id;	
    
    }
       
public static ArrayList<String> getList() {
	return description;
	}

 
}

