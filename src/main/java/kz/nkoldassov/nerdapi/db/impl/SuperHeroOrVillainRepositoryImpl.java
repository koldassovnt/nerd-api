package kz.nkoldassov.nerdapi.db.impl;

import kz.nkoldassov.nerdapi.db.jdbs.SuperHeroOrVillainRepository;
import kz.nkoldassov.nerdapi.db.model.SuperHeroOrVillain;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("SqlDialectInspection")
public class SuperHeroOrVillainRepositoryImpl implements SuperHeroOrVillainRepository {

    private final JdbcTemplate jdbcTemplate;

    public SuperHeroOrVillainRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void saveAsList(List<SuperHeroOrVillain> superHeroOrVillains) {
        jdbcTemplate.batchUpdate(
                "insert into super_hero_villain (external_id, name, slug, fullname, alteregos, " +
                        "                                aliases, firstappearance, publisher, " +
                        "                                alignment, imageurl, updated_at) " +
                        "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, current_timestamp) " +
                        "on conflict (external_id) " +
                        "do  " +
                        "update set name = excluded.name, slug = excluded.slug, " +
                        "           fullname = excluded.fullname, alteregos = excluded.alteregos, " +
                        "           aliases = excluded.aliases, firstappearance = excluded.firstappearance, " +
                        "           publisher = excluded.publisher, alignment = excluded.alignment, " +
                        "           imageurl = excluded.imageurl, updated_at = excluded.updated_at"
                , new BatchPreparedStatementSetter() {
            @Override
            public void setValues(@NotNull PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1, superHeroOrVillains.get(i).externalId);
                ps.setString(2, superHeroOrVillains.get(i).name);
                ps.setString(3, superHeroOrVillains.get(i).slug);
                ps.setString(4, superHeroOrVillains.get(i).fullName);
                ps.setString(5, superHeroOrVillains.get(i).alterEgos);
                ps.setString(6, superHeroOrVillains.get(i).aliases);
                ps.setString(7, superHeroOrVillains.get(i).firstAppearance);
                ps.setString(8, superHeroOrVillains.get(i).publisher);
                ps.setString(9, superHeroOrVillains.get(i).alignment);
                ps.setString(10, superHeroOrVillains.get(i).imageUrl);
            }

            @Override
            public int getBatchSize() {
                return superHeroOrVillains.size();
            }
        });
    }
}
