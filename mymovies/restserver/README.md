### RestServer
Her ligger koden som utgjør restserver og restApi for applikasjonen.  under *main*
likker en application-, controller-, og service -klasse, henholdsvis AllUsersaplication, AllUserscontroller
og AllUsersService.

Aplicationklassen starter Spring-Boot applikasjonen, controllerklassen styrer dataflyt til og fra server,
og AllUsersService brukes for å skille ut logikk fra kontrolleren.