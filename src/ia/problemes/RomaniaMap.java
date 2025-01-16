package ia.problemes;

import ia.framework.common.Action;
import ia.framework.common.State;
import ia.framework.recherche.Problem;

/**
 * Représentation du problème du voyage en Roumanie
 *
 */

public class RomaniaMap extends Problem {

    // Les différents états (villes de Roumanie)
    
    public static RomaniaMapState ARAD           = new RomaniaMapState("Arad", 366);   
    public static RomaniaMapState BUCHAREST      = new RomaniaMapState("Bucharest",0);
    public static RomaniaMapState CRAIOVA        = new RomaniaMapState("Craiova",160);
    public static RomaniaMapState DOBRETA        = new RomaniaMapState("Dobreta",242);
    public static RomaniaMapState EFORIE         = new RomaniaMapState("Eforie", 161);
    public static RomaniaMapState FAGARAS        = new RomaniaMapState("Fagaras",176);
    public static RomaniaMapState GIURGIU        = new RomaniaMapState("Giurgiu",77);
    public static RomaniaMapState HIRSOVA        = new RomaniaMapState("Hirsova",151);
    public static RomaniaMapState IASI           = new RomaniaMapState("Iasi",226);
    public static RomaniaMapState LUGOJ          = new RomaniaMapState("Lugoj", 244);
    public static RomaniaMapState MEHADIA        = new RomaniaMapState("Mehadia", 241);
    public static RomaniaMapState NEAMT          = new RomaniaMapState("Neamt", 234);
    public static RomaniaMapState ORADEA         = new RomaniaMapState("Oradea",380);
	public static RomaniaMapState PITESTI        = new RomaniaMapState("Pitesti", 100);
	public static RomaniaMapState RIMNICU_VILCEA = new RomaniaMapState("Rimnicu Vilcea", 193);
    public static RomaniaMapState SIBIU          = new RomaniaMapState("Sibiu", 253);
    public static RomaniaMapState TIMISOARA      = new RomaniaMapState("Timisoara",329);
    public static RomaniaMapState URZICENI       = new RomaniaMapState("Urziceni", 80);
	public static RomaniaMapState VASLUI         = new RomaniaMapState("Vaslui", 199);
    public static RomaniaMapState ZERIND         = new RomaniaMapState("Zerind", 374);


    // Les différente actions possible  
    
    static Action GOTO_ARAD           = new Action("goto Arad");   
    static Action GOTO_BUCHAREST      = new Action("goto Bucharest");
    static Action GOTO_CRAIOVA        = new Action("goto Craiova");
    static Action GOTO_DOBRETA        = new Action("goto Dobreta");
    static Action GOTO_EFORIE         = new Action("goto Eforie");
    static Action GOTO_FAGARAS        = new Action("goto Fagaras");
    static Action GOTO_GIURGIU        = new Action("goto Giurgiu");
    static Action GOTO_HIRSOVA        = new Action("goto Hirsova");
    static Action GOTO_IASI           = new Action("goto Iasi");
    static Action GOTO_LUGOJ          = new Action("goto Lugoj");
    static Action GOTO_MEHADIA        = new Action("goto Mehadia");
    static Action GOTO_NEAMT          = new Action("goto Neamt");
    static Action GOTO_ORADEA         = new Action("goto Oradea");
	static Action GOTO_PITESTI        = new Action("goto Pitesti");
	static Action GOTO_RIMNICU_VILCEA = new Action("goto Rimnicu Vilcea");
    static Action GOTO_SIBIU          = new Action("goto Sibiu");
    static Action GOTO_TIMISOARA      = new Action("goto Timisoara");
    static Action GOTO_URZICENI       = new Action("goto Urziceni");
	static Action GOTO_VASLUI         = new Action("goto Vaslui");
    static Action GOTO_ZERIND         = new Action("goto Zerind");
    
    public RomaniaMap(){

        // L'ensemble des états (Définis plus hauts)

        STATES = new State[] {
            ARAD,             
            BUCHAREST,      
            CRAIOVA,        
            DOBRETA,        
            EFORIE,        
            FAGARAS,        
            GIURGIU,        
            HIRSOVA,        
            IASI,           
            LUGOJ,          
            MEHADIA,       
            NEAMT,          
            ORADEA,         
            PITESTI,       
            RIMNICU_VILCEA,
            SIBIU,          
            TIMISOARA,  
            URZICENI,   
            VASLUI,         
            ZERIND };

        // Les actions
        ACTIONS = new Action[] {
            GOTO_ARAD,
            GOTO_BUCHAREST,
            GOTO_CRAIOVA,
            GOTO_DOBRETA,
            GOTO_EFORIE,
            GOTO_FAGARAS,
            GOTO_GIURGIU,
            GOTO_HIRSOVA,
            GOTO_IASI,
            GOTO_LUGOJ,
            GOTO_MEHADIA,
            GOTO_NEAMT,
            GOTO_ORADEA,
            GOTO_PITESTI,
            GOTO_RIMNICU_VILCEA,
            GOTO_SIBIU,
            GOTO_TIMISOARA,
            GOTO_URZICENI,
            GOTO_VASLUI,
            GOTO_ZERIND};

            
        
        // Les transitions possibles
        
        TRANSITIONS.addTransition(ORADEA,         GOTO_ZERIND,        ZERIND,         71.0);
		TRANSITIONS.addTransition(ORADEA,         GOTO_SIBIU,         SIBIU,          151.0);
		TRANSITIONS.addTransition(ZERIND,         GOTO_ARAD,          ARAD,           75.0);
		TRANSITIONS.addTransition(ARAD,           GOTO_TIMISOARA,     TIMISOARA,      118.0);
		TRANSITIONS.addTransition(ARAD,           GOTO_SIBIU,         SIBIU,          140.0);
		TRANSITIONS.addTransition(TIMISOARA,      GOTO_LUGOJ,         LUGOJ,          111.0);
		TRANSITIONS.addTransition(LUGOJ,          GOTO_MEHADIA,       MEHADIA,        70.0);
		TRANSITIONS.addTransition(MEHADIA,        GOTO_DOBRETA,       DOBRETA,        75.0);
		TRANSITIONS.addTransition(DOBRETA,        GOTO_CRAIOVA,       CRAIOVA,        120.0);
		TRANSITIONS.addTransition(SIBIU,          GOTO_FAGARAS,       FAGARAS,        99.0);
		TRANSITIONS.addTransition(SIBIU,          GOTO_RIMNICU_VILCEA,RIMNICU_VILCEA, 80.0);
		TRANSITIONS.addTransition(RIMNICU_VILCEA, GOTO_PITESTI,       PITESTI,        97.0);
		TRANSITIONS.addTransition(RIMNICU_VILCEA, GOTO_CRAIOVA,       CRAIOVA,        146.0);
		TRANSITIONS.addTransition(CRAIOVA,        GOTO_PITESTI,       PITESTI,        138.0);
		TRANSITIONS.addTransition(FAGARAS,        GOTO_BUCHAREST,     BUCHAREST,      211.0);
		TRANSITIONS.addTransition(PITESTI,        GOTO_BUCHAREST,     BUCHAREST,      101.0);
		TRANSITIONS.addTransition(GIURGIU,        GOTO_BUCHAREST,     BUCHAREST,      90.0);
		TRANSITIONS.addTransition(BUCHAREST,      GOTO_URZICENI,      URZICENI,       85.0);
		TRANSITIONS.addTransition(NEAMT,          GOTO_IASI,          IASI,           87.0);
		TRANSITIONS.addTransition(URZICENI,       GOTO_VASLUI,        VASLUI,         142.0);
		TRANSITIONS.addTransition(URZICENI,       GOTO_HIRSOVA,       HIRSOVA,        98.0);
        TRANSITIONS.addTransition(HIRSOVA,        GOTO_EFORIE,        EFORIE,         86.0);
		TRANSITIONS.addTransition(IASI,           GOTO_VASLUI,        VASLUI,         92.0);

        // transistions inverses
        
        TRANSITIONS.addTransition(ZERIND,         GOTO_ORADEA,          ORADEA,         71.0);
		TRANSITIONS.addTransition(SIBIU,          GOTO_ORADEA,          ORADEA,         151.0);
		TRANSITIONS.addTransition(ARAD,           GOTO_ZERIND,          ZERIND,         75.0);
		TRANSITIONS.addTransition(TIMISOARA,      GOTO_ARAD,            ARAD,           118.0);
		TRANSITIONS.addTransition(SIBIU,          GOTO_ARAD,            ARAD,           140.0);
		TRANSITIONS.addTransition(LUGOJ,          GOTO_TIMISOARA,       TIMISOARA,      111.0);
		TRANSITIONS.addTransition(MEHADIA,        GOTO_LUGOJ,           LUGOJ,          70.0);
		TRANSITIONS.addTransition(DOBRETA,        GOTO_MEHADIA,         MEHADIA,        75.0);
		TRANSITIONS.addTransition(CRAIOVA,        GOTO_DOBRETA,         DOBRETA,        120.0);
		TRANSITIONS.addTransition(FAGARAS,        GOTO_SIBIU,           SIBIU,          99.0);
		TRANSITIONS.addTransition(RIMNICU_VILCEA, GOTO_SIBIU,           SIBIU,          80.0);
		TRANSITIONS.addTransition(PITESTI,        GOTO_RIMNICU_VILCEA,  RIMNICU_VILCEA, 97.0);
		TRANSITIONS.addTransition(CRAIOVA,        GOTO_RIMNICU_VILCEA,  RIMNICU_VILCEA, 146.0);
		TRANSITIONS.addTransition(PITESTI,        GOTO_CRAIOVA,         CRAIOVA,        138.0);
		TRANSITIONS.addTransition(BUCHAREST,      GOTO_FAGARAS,         FAGARAS,        211.0);
		TRANSITIONS.addTransition(BUCHAREST,      GOTO_PITESTI,         PITESTI,        101.0);
		TRANSITIONS.addTransition(BUCHAREST,      GOTO_GIURGIU,         GIURGIU,        90.0);
		TRANSITIONS.addTransition(URZICENI,       GOTO_BUCHAREST,       BUCHAREST,      85.0);
		TRANSITIONS.addTransition(IASI,           GOTO_NEAMT,           NEAMT,          87.0);
		TRANSITIONS.addTransition(VASLUI,         GOTO_URZICENI,        URZICENI,       142.0);
		TRANSITIONS.addTransition(HIRSOVA,        GOTO_URZICENI,        URZICENI,       98.0);
        TRANSITIONS.addTransition(EFORIE,         GOTO_HIRSOVA,         HIRSOVA,        86.0);
		TRANSITIONS.addTransition(VASLUI,         GOTO_IASI,            IASI,           92.0);

    }
    
    // L'état (la ville) but
    
    public boolean isGoalState(State s){
        return s.equals(BUCHAREST) ;
    }


}
