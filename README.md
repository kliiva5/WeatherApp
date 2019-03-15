# WeatherApp

6. Git projektis on README.md, mis kirjeldab projekti seadmistamist ja viide juhendile.
App tuleb endale alla laadida ja projekt Android Studios lahti võtta.
Käivitamisel tuleb valida soovitud seade, testid sai tehtud Nexus 5X API 24 (Android 7.0 versiooniga).
Juhendiks peamiselt kasutasime omi kogemusi ja sisse kulunud töövõtteid.
Abiks oli ka https://square.github.io/okhttp/?fbclid=IwAR2JooV1kUXy6-r_izF3ArXVHvEq-EPM9fpHgDXKBIjnt3VvCvFM8a64ODM . 


7. Git projektis on README.md, kus asub arvamus juhendist (min 2 lõiku).
Juhendi kohta saab öelda, et on kompakte ja arusaadav. Dokumentatsioon on täiesti olemas ja näited korretksed. Paigaldamine toimis valatult ja lihtsalt. Tuleb vajutada sync nuppu ja korras. Päringute tegemiseks tuli AndroidMaanifest.xml faili ligipääs lubada <uses-permission android:name="android.permission.INTERNET"/> .


8. Git projektis on README.md, kus kirjeldatakse vajalikest muutustest seoses SDK versiooni muutustega.
Ainukene muudatus tuli teha seoses millise Java versiooniga projekti jooksutatakse. Probleemi lahendasime kui määrasime kompileerimiseks Java 8 versiooni.


9. Git projektis on README.md, kus kirjeldatakse juhendi muudest muutustest rakenduse terviklikumaks muutmise nimel.
Projektis kasutusel olevad koodijupuid mis sai võetud juhendist tuli ümber teha vastavalt vajadusele, pidime implementeerima custom adapterid, et parseda vastu tulevat jsonit objekti kujule ning selle kauda siis uuendada kasutajale vastavat infot.
