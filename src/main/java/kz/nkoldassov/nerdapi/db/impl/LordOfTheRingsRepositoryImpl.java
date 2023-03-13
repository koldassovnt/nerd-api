package kz.nkoldassov.nerdapi.db.impl;

import kz.nkoldassov.nerdapi.db.jdbs.LordOfTheRingsRepository;
import kz.nkoldassov.nerdapi.db.model.LordOfTheRingsCharacter;
import kz.nkoldassov.nerdapi.db.model.LordOfTheRingsMovie;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("SqlDialectInspection")
public class LordOfTheRingsRepositoryImpl implements LordOfTheRingsRepository {

    private final JdbcTemplate jdbcTemplate;

    public LordOfTheRingsRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveCharacters(List<LordOfTheRingsCharacter> characters) {
        jdbcTemplate.batchUpdate(
                "insert into lord_of_the_rings_character " +
                        "(external_id, height, race, gender, birth, spouse, death, realm, hair, name, updated_at) " +
                        "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, current_timestamp) " +
                        "on conflict (external_id) " +
                        "do " +
                        "update set height = excluded.height, " +
                        "race = excluded.race, gender = excluded.gender, birth = excluded.birth, " +
                        "spouse = excluded.spouse, death = excluded.death, realm = excluded.realm, " +
                        "hair = excluded.hair, name = excluded.name, updated_at = excluded.updated_at",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(@NotNull PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, characters.get(i).externalId);
                        ps.setString(2, characters.get(i).height);
                        ps.setString(3, characters.get(i).race);
                        ps.setString(4, characters.get(i).gender);
                        ps.setString(5, characters.get(i).birth);
                        ps.setString(6, characters.get(i).spouse);
                        ps.setString(7, characters.get(i).death);
                        ps.setString(8, characters.get(i).realm);
                        ps.setString(9, characters.get(i).hair);
                        ps.setString(10, characters.get(i).name);
                    }

                    @Override
                    public int getBatchSize() {
                        return characters.size();
                    }
                });
    }

    @Override
    public void saveMovies(List<LordOfTheRingsMovie> movies) {
        jdbcTemplate.batchUpdate(
                "insert into lord_of_the_rings_movie " +
                        "(external_id, name, runtime_in_minutes, budget_in_millions, box_office_revenue_in_millions, " +
                        "academy_award_nominations, academy_award_wins, rotten_tomatoes_score, updated_at) " +
                        "values (?, ?, ?, ?, ?, ?, ?, ?, current_timestamp) " +
                        "on conflict (external_id) " +
                        "do " +
                        "update set name = excluded.name, " +
                        "runtime_in_minutes = excluded.runtime_in_minutes, " +
                        "budget_in_millions = excluded.budget_in_millions, " +
                        "box_office_revenue_in_millions = excluded.box_office_revenue_in_millions, " +
                        "academy_award_nominations = excluded.academy_award_nominations, " +
                        "academy_award_wins = excluded.academy_award_wins, " +
                        "rotten_tomatoes_score = excluded.rotten_tomatoes_score, " +
                        "updated_at = excluded.updated_at",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(@NotNull PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, movies.get(i).externalId);
                        ps.setString(2, movies.get(i).name);
                        ps.setObject(3, movies.get(i).runtimeInMinutes);
                        ps.setObject(4, movies.get(i).budgetInMillions);
                        ps.setObject(5, movies.get(i).boxOfficeRevenueInMillions);
                        ps.setObject(6, movies.get(i).academyAwardNominations);
                        ps.setObject(7, movies.get(i).academyAwardWins);
                        ps.setObject(8, movies.get(i).rottenTomatoesScore);
                    }

                    @Override
                    public int getBatchSize() {
                        return movies.size();
                    }
                });
    }
}
