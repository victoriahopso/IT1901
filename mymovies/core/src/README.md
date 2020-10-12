# MyMovies

MyMovies er et prosjekt som skal utvikles i faget IT1901. Funksjonaliteten på det ferdige produktet skal være som følger:

- MyMovies er en app hvor brukere kan holde styr på filmer de har sett
- Det skal være mulig å legge inn filmer med sjanger og rating og også å gå tilbake å endre på dette i ettertid
- Det skal være mulig å se en oversikt over filmer man har sett før, og hvilken info som er lagt til den aktuelle filmen
- Oversikten over filmene til en bruker er privat, og man må derfor logge inn for å bruke appen

## Brukerhistorier
- Jeg, som bruker, vil ha en app hvor jeg kan anmelde filmer
- Jeg, som bruker, vil kunne registrere filmer jeg har sett, samt se en ryddig oversikt over alle filmene og hva jeg syntes om dem.
- Jeg, som bruker, vil kunne lagre filmene mine slik at alle ligger samme sted, og fortsette senere.

### Oppbygning

Under *main/java/mymovies* ligger *core* med kildekoden, og *json* som håndterer lagring. <br/>
Under *test/java/mymovies* ligger *core* med testkoden, og *json* hvor det ligger tester for applikasjonens persistens.