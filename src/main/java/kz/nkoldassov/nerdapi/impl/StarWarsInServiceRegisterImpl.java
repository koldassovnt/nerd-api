package kz.nkoldassov.nerdapi.impl;

import kz.nkoldassov.nerdapi.db.jdbs.StarWarsRepository;
import kz.nkoldassov.nerdapi.db.model.StarWarsFilm;
import kz.nkoldassov.nerdapi.db.model.StarWarsPerson;
import kz.nkoldassov.nerdapi.db.model.StarWarsPlanet;
import kz.nkoldassov.nerdapi.in_service.star_wars.StarWarsInService;
import kz.nkoldassov.nerdapi.in_service.star_wars.model.film.StarWarsFilmDetailResponse;
import kz.nkoldassov.nerdapi.in_service.star_wars.model.film.StarWarsFilmsResponse;
import kz.nkoldassov.nerdapi.in_service.star_wars.model.people.StarWarsPeopleResponse;
import kz.nkoldassov.nerdapi.in_service.star_wars.model.people.StarWarsPersonDetailResponse;
import kz.nkoldassov.nerdapi.in_service.star_wars.model.planet.StarWarsPlanetDetailResponse;
import kz.nkoldassov.nerdapi.in_service.star_wars.model.planet.StarWarsPlanetResponse;
import kz.nkoldassov.nerdapi.logging.LOG;
import kz.nkoldassov.nerdapi.register.StarWarsInServiceRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static kz.nkoldassov.nerdapi.util.InServiceUtil.getIdFromStarWarsApiUrl;

@Component
public class StarWarsInServiceRegisterImpl implements StarWarsInServiceRegister {

    private final LOG log = LOG.forClass(getClass());

    @Autowired
    private StarWarsInService starWarsInService;

    @Autowired
    private StarWarsRepository starWarsRepository;

    @Override
    public List<StarWarsFilm> getFilms(int page) {

        StarWarsFilmsResponse films = starWarsInService.getFilms(page);

        List<StarWarsFilm> resultFilms = new ArrayList<>();

        for (StarWarsFilmDetailResponse result : films.results) {
            StarWarsFilm film = new StarWarsFilm();

            String idFromStarWarsApiUrlFilm = getIdFromStarWarsApiUrl(result.url);

            if (idFromStarWarsApiUrlFilm == null) {
                continue;
            }

            film.externalId = idFromStarWarsApiUrlFilm;
            film.title = result.title;
            film.director = result.director;
            film.producer = result.producer;
            film.releaseDate = result.releaseDate;

            for (String planetUrl : result.planets) {
                String idFromStarWarsApiUrlPlanet = getIdFromStarWarsApiUrl(planetUrl);

                if (idFromStarWarsApiUrlPlanet == null) {
                    continue;
                }

                film.planets.add(StarWarsPlanet.of(idFromStarWarsApiUrlPlanet));
            }

            resultFilms.add(film);
        }

        return resultFilms;
    }

    @Override
    public List<StarWarsPerson> getPeople(int page) {

        StarWarsPeopleResponse people = starWarsInService.getPeople(page);

        List<StarWarsPerson> resultPeople = new ArrayList<>();

        for (StarWarsPersonDetailResponse result : people.results) {
            StarWarsPerson person = new StarWarsPerson();

            String idFromStarWarsApiUrlPerson = getIdFromStarWarsApiUrl(result.url);

            if (idFromStarWarsApiUrlPerson == null) {
                continue;
            }

            person.externalId = idFromStarWarsApiUrlPerson;
            person.name = result.name;
            person.height = result.height;
            person.mass = result.mass;

            String idFromStarWarsApiUrlHomeWorld = getIdFromStarWarsApiUrl(result.homeWorld);

            person.homeWorld = StarWarsPlanet.of(idFromStarWarsApiUrlHomeWorld);

            for (String filmUrl : result.films) {
                String idFromStarWarsApiUrlFilm = getIdFromStarWarsApiUrl(filmUrl);

                if (idFromStarWarsApiUrlFilm == null) {
                    continue;
                }

                person.films.add(StarWarsFilm.of(idFromStarWarsApiUrlFilm));
            }

            resultPeople.add(person);
        }

        return resultPeople;
    }

    @Override
    public List<StarWarsPlanet> getPlanets(int page) {

        StarWarsPlanetResponse planetResponse = starWarsInService.getPlanets(page);

        List<StarWarsPlanet> planetList = new ArrayList<>();

        for (StarWarsPlanetDetailResponse result : planetResponse.results) {
            StarWarsPlanet planet = new StarWarsPlanet();

            String idFromStarWarsApiUrlPlanet = getIdFromStarWarsApiUrl(result.url);

            if (idFromStarWarsApiUrlPlanet == null) {
                continue;
            }

            planet.externalId = idFromStarWarsApiUrlPlanet;
            planet.name = result.name;
            planet.diameter = result.diameter;
            planet.climate = result.climate;
            planet.terrain = result.terrain;
            planet.population = result.population;

            for (String residentUrl : result.residents) {
                String idFromStarWarsApiUrlResident = getIdFromStarWarsApiUrl(residentUrl);

                if (idFromStarWarsApiUrlResident == null) {
                    continue;
                }

                planet.residents.add(StarWarsPerson.of(idFromStarWarsApiUrlResident));
            }

            for (String filmUrl : result.films) {
                String idFromStarWarsApiUrlFilm = getIdFromStarWarsApiUrl(filmUrl);

                if (idFromStarWarsApiUrlFilm == null) {
                    continue;
                }

                planet.films.add(StarWarsFilm.of(idFromStarWarsApiUrlFilm));
            }

            planetList.add(planet);
        }


        return planetList;
    }

    @Override
    public void migrateFilms() {
        log.info(() -> "d9dfS1Xjp1 :: Star Wars film migration start");
        long time1 = System.currentTimeMillis();

        int page = 1;

        while (true) {
            List<StarWarsFilm> films = getFilms(page);

            if (films.isEmpty()) {
                break;
            }

            starWarsRepository.saveFilms(films);
            page++;
        }

        long time2 = System.currentTimeMillis();
        log.info(() -> "P5afjXn72j :: Star Wars film migration finish in " + (time2 - time1) + "ms");
    }

    @Override
    public void migratePeople() {
        log.info(() -> "Re2PAQ7q6u :: Star Wars people migration start");
        long time1 = System.currentTimeMillis();

        int page = 1;

        while (true) {
            List<StarWarsPerson> people = getPeople(page);

            if (people.isEmpty()) {
                break;
            }

            starWarsRepository.savePeople(people);
            page++;
        }

        long time2 = System.currentTimeMillis();
        log.info(() -> "sAAkbGIH43 :: Star Wars people migration finish in " + (time2 - time1) + "ms");
    }

    @Override
    public void migratePlanets() {
        log.info(() -> "r017QfEurH :: Star Wars planet migration start");
        long time1 = System.currentTimeMillis();

        int page = 1;

        while (true) {
            List<StarWarsPlanet> planets = getPlanets(page);

            if (planets.isEmpty()) {
                break;
            }

            starWarsRepository.savePlanets(planets);
            page++;
        }

        long time2 = System.currentTimeMillis();
        log.info(() -> "2hbPuA1nt2 :: Star Wars planet migration finish in " + (time2 - time1) + "ms");
    }
}
