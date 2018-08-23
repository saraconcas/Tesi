# tesi
Sviluppo di un software GestioneAgenda attraverso la metodologia del Test-Driven-Development

Il sistema GestioneAgenda dovrà soddisfare i seguenti requisiti:

REQUISITO 1: Inserendo una data (nel formato dd-mm-yyyy) si viene informati se si tratta di un giorno festivo (inizialmente solo domenica, in seguito potrà essere ampliato);

REQUISITO 2: Inserendo una data (nel formato dd-mm-yyyy), un orario e una descrizione, è possibile registrare degli impegni che potranno essere visualizzati inserendo la medesima data.

REQUISITO 3: Non è possibile inserire due impegni in corrispondenza della stessa data e dello stesso orario.

REQUISITO 4: i dati (data, ora e impegno) devono poter essere salvati su memoria persistente (database).

Ciascuno di essi sarà scomposto in requisiti minori (casi di test) a partire dai quali verranno scritti i test d’unità. 
La scrittura del codice implementativo sarà permessa solo in seguito al fallimento del relativo test d’unità.
Infine, verrà scritto un test d’integrazione che verifichi il corretto funzionamento del sistema nel suo insieme.

