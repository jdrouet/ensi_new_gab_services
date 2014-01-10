Ce projet permet de faire une authentification par emprunte digitale.

C'est un module qui vient se greffer sur certaine page du projet principal.

Il permet de prouver que l'on est capable de communquer avec les device USB de la machine client.

Si on veut recompiler le projet, il est nécessaire de signer l'applet avec l'outil jarsigner.bat fournis dans /signature.
Attentiuon a ne e pas oublier d'inclure le manifest.mf a la compilation du jar.

Lors du deploiment de l'applet sur le servuer J2EE, il est nécessaire déposer toutes les dépenses du projet (qui doivent être signés).
Les .jar obtenues sont à déposer dans le repétoire webapp/lib
PS : Une version fonctionnelle est deja incluse dans le projet principale.
