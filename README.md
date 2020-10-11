# Gruppe gr2003 repo
#### Åpne prosjektet i Gitpod:
[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.idi.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2020/gr2003/gr2003) 


## MyMovies prosjektet
Projektet er bygget med **Maven**, og har støtte for direkte tilgang til **Gitpod**.
Bygget er rigget for å rapportere testdekningsgrad ved kjøring av *mvn verify*.
For å forsikre kodekvalitet benyttes **Checkstyle**, **Spotbugs**, og **JaCoCo**. 
Vi implementerer fillagring med **JSON**.

Koden kjøres ved hjelp av kommandoen *mvn javafx:run -f fxui/pom.xml*


### Organisering av koden 
- Kildekode: [mymovies/core/src/main/java/mymovies/core](https://gitlab.stud.idi.ntnu.no/it1901/groups-2020/gr2003/gr2003/-/tree/master/mymovies%2Fcore%2Fsrc%2Fmain%2Fjava%2Fmymovies%2Fcore)
- Testkode for applikasjonen: [mymovies/core/src/test/java/mymovies/core](https://gitlab.stud.idi.ntnu.no/it1901/groups-2020/gr2003/gr2003/-/tree/master/mymovies%2Fcore%2Fsrc%2Ftest%2Fjava%2Fmymovies%2Fcore)
- Persistenslaget: [mymovies/core/src/main/java/mymovies/json](https://gitlab.stud.idi.ntnu.no/it1901/groups-2020/gr2003/gr2003/-/tree/master/mymovies%2Fcore%2Fsrc%2Fmain%2Fjava%2Fmymovies%2Fjson)
- Brukergrensesnitt: [mymovies/fxui/src/main/java/mymovies/ui](https://gitlab.stud.idi.ntnu.no/it1901/groups-2020/gr2003/gr2003/-/tree/master/mymovies%2Ffxui%2Fsrc%2Fmain%2Fjava%2Fmymovies%2Fui)
- Tilhørende ressurser: [mymovies/fxui/src/main/resources/mymovies/ui](https://gitlab.stud.idi.ntnu.no/it1901/groups-2020/gr2003/gr2003/-/tree/master/mymovies%2Ffxui%2Fsrc%2Fmain%2Fresources%2Fmymovies%2Fui)
- Testkode for brukergrensesnitt: [mymovies/fxui/src/test/java/mymovies/ui](https://gitlab.stud.idi.ntnu.no/it1901/groups-2020/gr2003/gr2003/-/tree/master/mymovies%2Ffxui%2Fsrc%2Ftest%2Fjava%2Fmymovies%2Fui)
---

#### Arbeidet 
Prosjektet deles inn i brukerhistorier, som deretter deles inn i mindre utviklingsoppgaver  
og tilordnes et gruppemedlem.  
