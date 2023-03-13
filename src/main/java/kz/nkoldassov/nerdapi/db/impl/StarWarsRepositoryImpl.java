package kz.nkoldassov.nerdapi.db.impl;

import kz.nkoldassov.nerdapi.db.jdbs.StarWarsRepository;
import kz.nkoldassov.nerdapi.db.model.StarWarsFilm;
import kz.nkoldassov.nerdapi.db.model.StarWarsPerson;
import kz.nkoldassov.nerdapi.db.model.StarWarsPlanet;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("SqlDialectInspection")
public class StarWarsRepositoryImpl implements StarWarsRepository {

    private final JdbcTemplate jdbcTemplate;

    public StarWarsRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveFilms(List<StarWarsFilm> films) {
        jdbcTemplate.batchUpdate(
                "insert into star_wars_film " +
                        "(external_id, title, director, producer, release_date, updated_at) " +
                        "values (?, ?, ?, ?, ?, current_timestamp) " +
                        "on conflict(external_id) " +
                        "do " +
                        "update set title = excluded.title, director = excluded.director, " +
                        "producer = excluded.producer, release_date = excluded.release_date, " +
                        "updated_at = excluded.updated_at",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(@NotNull PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, films.get(i).externalId);
                        ps.setString(2, films.get(i).title);
                        ps.setString(3, films.get(i).director);
                        ps.setString(4, films.get(i).producer);
                        ps.setString(5, films.get(i).releaseDate);
                    }

                    @Override
                    public int getBatchSize() {
                        return films.size();
                    }
                });

        for (StarWarsFilm film : films) {

            jdbcTemplate.batchUpdate(
                    "insert into star_wars_film_planet (film_id, planet_id) " +
                            "values (?, ?) " +
                            "on conflict (film_id, planet_id) " +
                            "do nothing",
                    new BatchPreparedStatementSetter() {
                        @Override
                        public void setValues(@NotNull PreparedStatement ps, int i) throws SQLException {
                            ps.setString(1, film.externalId);
                            ps.setString(2, film.planets.get(i).externalId);
                        }

                        @Override
                        public int getBatchSize() {
                            return film.planets.size();
                        }
                    });
        }
    }

    @Override
    public void savePeople(List<StarWarsPerson> people) {
        jdbcTemplate.batchUpdate(
                "insert into star_wars_person " +
                        "(external_id, name, height, mass, birth_year, home_world_id, updated_at) " +
                        "values (?, ?, ?, ?, ?, ?, current_timestamp) " +
                        "on conflict(external_id) " +
                        "do " +
                        "update set name = excluded.name, height = excluded.height, " +
                        "mass = excluded.mass, birth_year = excluded.birth_year, " +
                        "home_world_id = excluded.home_world_id, updated_at = excluded.updated_at",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(@NotNull PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, people.get(i).externalId);
                        ps.setString(2, people.get(i).name);
                        ps.setString(3, people.get(i).height);
                        ps.setString(4, people.get(i).mass);
                        ps.setString(5, people.get(i).birthYear);
                        ps.setString(6, people.get(i).homeWorld.externalId);
                    }

                    @Override
                    public int getBatchSize() {
                        return people.size();
                    }
                });

        for (StarWarsPerson person : people) {

            jdbcTemplate.batchUpdate(
                    "insert into star_wars_person_film (person_id, film_id) " +
                            "values (?, ?) " +
                            "on conflict (person_id, film_id) " +
                            "do nothing",
                    new BatchPreparedStatementSetter() {
                        @Override
                        public void setValues(@NotNull PreparedStatement ps, int i) throws SQLException {
                            ps.setString(1, person.externalId);
                            ps.setString(2, person.films.get(i).externalId);
                        }

                        @Override
                        public int getBatchSize() {
                            return person.films.size();
                        }
                    });
        }
    }

    @Override
    public void savePlanets(List<StarWarsPlanet> planets) {
        jdbcTemplate.batchUpdate(
                "insert into star_wars_planet " +
                        "(external_id, name, diameter, climate, terrain, population, updated_at) " +
                        "values (?, ?, ?, ?, ?, ?, current_timestamp) " +
                        "on conflict(external_id) " +
                        "do " +
                        "update set name = excluded.name, diameter = excluded.diameter, " +
                        "climate = excluded.climate, terrain = excluded.terrain, " +
                        "population = excluded.population, updated_at = excluded.updated_at",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(@NotNull PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, planets.get(i).externalId);
                        ps.setString(2, planets.get(i).name);
                        ps.setString(3, planets.get(i).diameter);
                        ps.setString(4, planets.get(i).climate);
                        ps.setString(5, planets.get(i).terrain);
                        ps.setString(6, planets.get(i).population);
                    }

                    @Override
                    public int getBatchSize() {
                        return planets.size();
                    }
                });

        for (StarWarsPlanet planet : planets) {

            jdbcTemplate.batchUpdate(
                    "insert into star_wars_planet_person (planet_id, person_id) " +
                            "values (?, ?) " +
                            "on conflict (planet_id, person_id) " +
                            "do nothing",
                    new BatchPreparedStatementSetter() {
                        @Override
                        public void setValues(@NotNull PreparedStatement ps, int i) throws SQLException {
                            ps.setString(1, planet.externalId);
                            ps.setString(2, planet.residents.get(i).externalId);
                        }

                        @Override
                        public int getBatchSize() {
                            return planet.residents.size();
                        }
                    });
        }
    }
}
