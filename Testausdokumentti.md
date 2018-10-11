# Testausdokumentti

Testaus suoritetaan JUNIT kirjastolla. Nappuloiden siirtojen laillisuus on testattu pakkauksessa ”pieces”. Tiedostossa ”ComputerTest” on testattu, että minimax algoritmi toimii oikein. ”GameTest” tiedostossa on testattu, että peli toimii oikein eli esimerkiksi valkoisen vuorolla ei voi siirrellä mustia nappuloita. Myös ArrayList tietorakenne on testattu.
Luokassa PerformanceTest on tutkittu tietokonesiirtojen luomisen kestoa eri hakusyvyyksillä. Syvyydellä 3 kesto on n. 20-100ms. Syvyydellä 4 n. 600-800ms. Arviot on saatu toistamalla testejä useamman kerran eri pelitilanteissa. 
Jos haluttasiin kasvattaa syvyyttä vielä tästä, olisi ohjelman rakennetta muutettava huomattavasti.  