# Toteutusdokumentti

## Ohjelman rakenne

Ohjelman  main pakkauksessa on tiedosto ”Play” jonka avulla voi pelata shakkia tekoälyä vastaan komentorivillä toteutetulla käyttöliittymällä. Tiedostalla ”ComputerVsComputer” voi katsoa kuin kone pelaa itseään vastaan.

Pakkaus ”game” sisältää peli logiikkaan liittyvät tiedostot, kuten ”Move” ja ”Game” luokat. ”Move” esittää siirtoja ja ”Game” peliä. Näiden avulla luodaan uusia siirtoja ja pelejä. ”Board” luokka taas esittää shakkilautaa ja se pitää kirjaa nappuloista ja niiden sijainneista. Pakkaus ”pieces” sisältää logiikan liittyen nappuloihin, jokaisella nappulalla on oma luokka, jossa on määritelty lailliset siirrot. 

Pakkauksessa ”data.structures” on toteutettu tarvittavat tietorakenteet. 

## Suorituskyky ja O-analyysi

Ohjelman vaativin osio on tekoälyn siirtojen haku. Siirtoja haetaaan rekursiivisesti. Haemma siirtoja käymällä läpi kaikki tietyn väriset nappula. Jokaiselle nappulalle haemma lailliset mahdolliset siirrot. Jokaiselle mahdolliselle siirrolle joudumme katsomaan laskemaan pelitilanteen arvon laskemalla laudan nappulat. Eli 64 nappulaa x noin 10 mahdollista siirtoa per nappula x 64 ruutua siirron arvon määrittämiseksi eli noin 40 000 iteraatiota. 

## Puuttet

Ohjelma on hidas, koska pelitilanne esitetään listoina. Lautaa ja nappuloita nopeuden maksimoinniksi pitäisi esittää ”bitboard” muodossa, jossa jokaista ruutua vastaa yksi bitti. Jokaiselle nappulalle olisi siis oma ”bitboard”, jossa kaikki bitit 1 on jos kyseisessä ruudussa on nappula. 
