Le présent projet est une ébauche à compléter du devoir 3.

Il est crucial de ne pas modifier les signatures des méthodes publiques 
présentes dans le programme de départ. Il ne faut pas non plus modifier le nom 
des classes et des packages existants dans le programme. La raison est qu'un 
programme de tests unitaires est déjà écrit selon ces signatures. Si les 
signatures sont modifiées, le programme ne les reconnaîtra pas et les tests ne 
seront pas exécutés (aucun points bonus dans ce cas).
La signature comprend les modificateurs (public, static...), le type de retour, 
le nom de la méthode et les paramètres.

Par exemple, considérons la méthode suivante.

public static Reader<Collection<Etudiant>> getEtudiantReader() {
		throw new NotImplementedException();
}

La signature est :
	public static Reader<Collection<Etudiant>> getEtudiantReader()

Le corps de la méthode est:  {throw new NotImplementedException();}
	Il faut compléter le corps de la méthode sans changer la signature.
	
Par ailleurs, vous êtes libres d'ajouter de nouvelles méthodes, packages ou 
classes.