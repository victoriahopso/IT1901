# MyMovies kjernen
Under mappen myMovies/core ligger kjerneklassene/kjernedata for prosjektet. Disse er klassene User, AllUsers, Film, og RW.
på samme nivå finner vi også applikasjonens persistenslag, som inneholder klasser serialisering og deserialisering av "kjerneobjektene". 
Den inneholder også definering av lesing og skriving. 
Tilsvarende mappestruktur ligger under test, som inneholder jUnit tester for ovennevnte. 


### Altså:
Under *main/java/mymovies* ligger *core* med kildekoden, og *json* som håndterer lagring. <br/>
Under *test/java/mymovies* ligger *core* med testkoden, og *json* hvor det ligger tester for applikasjonens persistens.