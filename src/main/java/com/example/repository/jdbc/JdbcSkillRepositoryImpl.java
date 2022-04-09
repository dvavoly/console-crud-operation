package com.example.repository.jdbc;

import com.example.model.Skill;
import com.example.repository.SkillRepository;
import com.example.utils.Messages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcSkillRepositoryImpl implements SkillRepository {

    private final DatasourceFactory factory = new DatasourceFactory();
    Logger LOGGER = LoggerFactory.getLogger(JdbcSkillRepositoryImpl.class);

    @Override
    public Skill getById(Integer id) {
        String query = "SELECT id, skill_name FROM skill WHERE id = " + id;
        try (var statement = factory.getConnection().createStatement()) {
            var resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                return new Skill(
                        resultSet.getString("skill_name"),
                        resultSet.getInt("id"));
            }
        } catch (SQLException exception) {
            LOGGER.error("SQL Error: ", exception);
        }
        return null;
    }

    @Override
    public List<Skill> getAll() {
        String query = "SELECT id, skill_name FROM skill";
        List<Skill> result = new ArrayList<>();
        try (Statement statement = factory.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                result.add(new Skill(resultSet.getString("skill_name"), resultSet.getInt("id")));
            }
        } catch (SQLException exception) {
            LOGGER.error("SQL Error: ", exception);
        }
        return result;
    }

    @Override
    public Skill save(Skill skill) {
        String queryInsert = "INSERT INTO skill(skill_name) VALUES ('" + skill.getName() + "')";
        String getLastId = "SELECT id, skill_name FROM skill ORDER BY id DESC LIMIT 1";
        try (var statement = factory.getConnection().createStatement()) {
            statement.execute(queryInsert);
            ResultSet resultSet = statement.executeQuery(getLastId);
//             if (resultSet.first()) {
//                return new Skill(
//                        resultSet.getString("skill_name"),
//                        resultSet.getInt("id"));
//            }
            while (resultSet.next()) {
                return new Skill(
                        resultSet.getString("skill_name"),
                        resultSet.getInt("id"));
            }
        } catch (SQLException exception) {
            LOGGER.error("SQL Error", exception);
        }
        return null;
    }

    @Override
    public Skill update(Skill skill) {
        if (skill.getId() == null) {
            throw new IllegalArgumentException(Messages.CANNOT_BE_NULL.toString());
        }
        String query = "UPDATE skill SET skill_name = '" + skill.getName() + "' WHERE id = " + skill.getId();
        try (var statement = factory.getConnection().createStatement()) {
            statement.execute(query);
        } catch (SQLException exception) {
            LOGGER.error("SQL Error: ", exception);
        }
        return getById(skill.getId());
    }

    @Override
    public void deleteById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException(Messages.CANNOT_BE_NULL.toString());
        }
        String query = "DELETE FROM skill WHERE id = " + id;
        try (var statement = factory.getConnection().createStatement()) {
            statement.execute(query);
        } catch (SQLException exception) {
            LOGGER.error("SQL Error: ", exception);
        }
    }
}
