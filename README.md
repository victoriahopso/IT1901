# Gruppe gr2003 repo
#### Åpne prosjektet i Gitpod:
[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.idi.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2020/gr2003/gr2003) 

## MyMovies applikasjonen
Formål: registrere enkle filmanmeldelser samt kunne se og administrere en ryddig oversikt over "mine registreringer". 

En innlogget bruker kan legge til så mange filmanmeldelser som ønsket. Brukeren kan også se en oversikt over alle registreringer, sortere listen på ønsket kriterium, samt fjerne registreringer. 

## MyMovies prosjektet
Projektet er bygget med **Maven**, og har støtte for direkte tilgang til **Gitpod**.
Bygget er rigget for å rapportere testdekningsgrad (vha. **JaCoCo**) ved kjøring av `mvn verify`.
For å forsikre kodekvalitet benyttes **Checkstyle** som sjekker overfladiske og stilmessige egenskaper, og **Spotbugs** som analyserer koden for vanlige feil.
Vi implementerer fillagring med **Json**. Benyttet rammeverk for RestAPI og RestServer er **SpringBoot**.

### Bygging og kjøring 
- **Bygging:** kjør `mvn install`fra **mymovies**-mappen. 
- **Starte server:** åpne en ny terminal, skriv kommandoen `mvn sprin-boot:run -f restserver/pom.xml`i **mymovies**-mappen.
- **Kjøring:** gå tilbake til den første terminalen og skriv kommandoen `mvn javafx:run -f fxui/pom.xml`


### Organisering av koden 
Prosjektet er delt inn i 3 moduler, henholdsvis *core*, *fxui* og *restserver*.
- *core* inneholder dataklasser og persistens (Json format).
- *fxui* inneholder kontrollere for GUI, og grensesnitt med implementasjoner for tilgang til server. FXML filer for GUI finnes i *resources*.
- *restserver* inneholder håndering av dataflyt til og fra server. 

Hver modul har tilhørende tester under scr/test. 
- Sekvensdiagram: 
- Arkitektur: 

---

- Kildekode: [mymovies/core/src/main/java/mymovies/core](https://gitlab.stud.idi.ntnu.no/it1901/groups-2020/gr2003/gr2003/-/tree/master/mymovies%2Fcore%2Fsrc%2Fmain%2Fjava%2Fmymovies%2Fcore)
- Persistens: [mymovies/core/src/main/java/mymovies/json](https://gitlab.stud.idi.ntnu.no/it1901/groups-2020/gr2003/gr2003/-/tree/master/mymovies%2Fcore%2Fsrc%2Fmain%2Fjava%2Fmymovies%2Fjson)
- Brukergrensesnitt: [mymovies/fxui/src/main/java/mymovies/ui](https://gitlab.stud.idi.ntnu.no/it1901/groups-2020/gr2003/gr2003/-/tree/master/mymovies%2Ffxui%2Fsrc%2Fmain%2Fjava%2Fmymovies%2Fui)

---

#### Arbeidet 
Prosjektet deles inn i brukerhistorier (se *labels*), som deretter deles inn i mindre utviklingsoppgaver(*issues*) og tilordnes et gruppemedlem. Det opprettes en egen grein for hver utviklingsoppgave, og det benyttes parprogrammering der det er hensiktsmessig. 
