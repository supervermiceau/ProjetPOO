Trois fichiers sont fournis :
1) index_livres.txt : l'index des livres/magazines
2a) sale.txt : le fichier des recettes de la catégorie "salé"
2b) sucre.txt : le fichier des recettes de la catégorie "sucré"
Tous ces fichiers sont encodés en UTF-8.
Astuce : sous VI, la commande ":set list" vous permet de visualiser les tabulations et caractères de fin de ligne.

Leur structure :
1) Pour le fichier 1, la structure d'une ligne est :
"<numero>.<tabulation><nom_livre>"
sachant qu'il y a simplement autant de lignes que de livres indexés.

2) Pour les fichiers 2a et 2b, pour un fichier qui contient T catégories, la structure est la suivante (chaque nouvelle ligne est entre ""):
"* <nom_de_categorie 1>:"
"- <nom_recette_1> (<numero_index_livre>)"
"- <nom_recette_2> (<numero_index_livre>)"
[...]
"- <nom_recette_N> (<numero_index_livre>)"
"" (une ligne blanche)
"* <nom_de_categorie 2>:"
"- <nom_recette_1> (<numero_index_livre>)"
"- <nom_recette_2> (<numero_index_livre>)"
[...]
"- <nom_recette_M> (<numero_index_livre>)"
"" (une ligne blanche)
[...]
"" (une ligne blanche)
"* <nom_de_categorie K>:"
"- <nom_recette_1> (<numero_index_livre>)"
"- <nom_recette_2> (<numero_index_livre>)"
[...]
"- <nom_recette_T> (<numero_index_livre>)"

Le fichier respecte les contraintes suivantes :
- deux recettes peuvent avoir le même nom
- une recette n'est indexée que dans un seul livre
- un nom (recette/livre) peut contenir des tirets -, des guillements ou des double-points.
- un nom (recette/livre) ne contient pas de parenthèses (autrement dit : des parenthèses contiennent toujours un numéro d'index de livre).
- un nom (recette/livre) ne contient pas d'étoile "*" (autrement dit : une étoile signifie toujours le début d'une ligne contenant un nom de catégorie, cette ligne finit par ailleurs toujours par un double-point).
