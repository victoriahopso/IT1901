## Brukergrensesnitt
Her ligger koden som utgjør brukergrensesnittet i MyMovies. 
Under *main* ligger to kontroller-klasser, MyMoviesController og LogInController. Disse har hver sin tilhørende FXML fil under *resoruces*. 
I mappen ligger også et grensesnitt UserAccess som har to implementasjoner, henholdsvis RemoteUserAccess og MockUserAccess. Sistenevnte brukes til testformål, 
for å kunne teste GUI uavhengig av serveren. RemoteUserAccess er, ved kjøring av appen, bindeleddet mellom GUI og server. 
Under *test* ligger testkode for ovennevnte. 


## Skjermbilder av GUI:
https://gitlab.stud.idi.ntnu.no/it1901/groups-2020/gr2003/gr2003/-/wikis/Brukeregrensesnitt,-skjermbiler